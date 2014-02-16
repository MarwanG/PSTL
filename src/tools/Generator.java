package tools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import struct.Composant;
import struct.Node;


public class Generator {

	public static ArrayList<Node> leaf = new ArrayList<Node>();
	
	public static HashMap<Integer,ArrayList<Node>>table;
	
	public static ArrayList<Node> constructers = new ArrayList<Node>();
	public static ArrayList<ArrayList<Node>> mainList = new ArrayList<ArrayList<Node>>();
	public static ArrayList<Node> list = new ArrayList<Node>();
	
	
	public static void gen() {
		
		if(Config.verbose >= 2 )
			System.out.println("Generating possible composants");
		for(int i = 0 ; i < Config.labels.size() ; i++){
			ArrayList<Composant> list = Config.hash.get(Config.labels.get(i));
			if(Config.verbose >= 1){
				System.out.println("List of Composants :");
			}
			for(int j = 0 ; j < list.size() ;j++){
				Composant c = list.get(j);

				if(Config.verbose >= 1){
					System.out.println(c.toString());
				}
				int nbFils = 0;
				for(int z = 0 ; z < c.getList().size() ; z++){
					if(Config.labels.contains(c.getList().get(i))){
						nbFils++;
					}
				}
				if(nbFils == 0){		//considers only the first one met.
					leaf.add(new Node(Config.labels.get(i),c.getWeight()));
				}
			}
		}

		if(Config.verbose >= 2 )
			System.out.println("DONE");
		
	
		if(Config.verbose >= 2 )
			System.out.print("Generating possible Constructors");
		
		mainList.add(new ArrayList<Node>());
		
		for(int i = 0 ; i < Config.labels.size() ; i++){
			ArrayList<Composant> list = Config.hash.get(Config.labels.get(i));
			for(int j = 0 ; j < list.size() ; j++){								
				Composant c = list.get(j);		
				generateConstructers(c,Config.labels.get(i));			//ici on avait un if. soit dans le method generateConstructers on fait un truc si le composant n'est pas bon.
			}															// un TreeNode par exemple.
		}
		if(Config.verbose >= 2 )
			System.out.println("\t OK");
		
		Collections.sort(Generator.constructers, new NodeCompartor());		
	}
	
	
	private static void generateConstructers(Composant c,String type){
		ArrayList<Node> list = new ArrayList<Node>();
		ArrayList<String> sons = c.getList();
		String son = sons.get(0);

		for(int i = 0 ; i < leaf.size() ; i++){
			if(leaf.get(i).getType().equals(son)){
				Node n = new Node(type,c.getWeight());
				n.addFils(Node.clone(leaf.get(i)));
				list.add(n);
			}
		}
		for(int j = 1 ; j < sons.size() ; j++){
			son = sons.get(j);
			int taille = list.size();
			for(int i = 0 ; i < taille ; i++){
				Node n = list.get(i);							
				for(int z = 0 ; z < leaf.size() ; z++){
					if(leaf.get(z).getType().equals(son)){
						Node n2 = Node.clone(n);
						n2.addFils(Node.clone(leaf.get(z)));
						if(j == sons.size()-1){
							constructers.add(n2);
							mainList.get(0).add(n2);
						}else{
							list.add(n2);
						}						
					}
				}
			}
		}
	}
	
	
	
	//PROBLEM ICI
	
	//@SuppressWarnings("unchecked")
	public static void generation(int g){
		
		table = new HashMap<Integer,ArrayList<Node>>();
		int start = 0 ;
		
		while(start < g){
			System.out.println("GEN = " + start);
			ArrayList<Node> newList = new ArrayList<Node>();
			ArrayList<Node> list = mainList.get(start);
			int taille = list.size();
			int i = 0;
			System.out.println("SIZE = " + taille);
			while(i < taille){
				Node tmp = list.get(i);
				//System.out.println("i = " + i);
				for(int j = 0 ; j < tmp.getFils().size() ; j++){
					for(int z = 0 ; z < constructers.size() ; z++){
						addList(tmp.AddLevel(constructers.get(z)),newList);
					}
				}
				i++;
			}
			mainList.add(newList);
			start++;
		}
	}
	
	private static void addList(ArrayList<Node> list,ArrayList<Node> newList){
		for(int i = 0 ; i < list.size() ;i++){
			if(!newList.contains(list.get(i))){
				newList.add(list.get(i));
				if(table.containsKey(list.get(i).getWeight())){
					if(!table.get(list.get(i).getWeight()).contains(list.get(i)))
						table.get(list.get(i).getWeight()).add(list.get(i));
				}else{
					ArrayList<Node> list2 = new ArrayList<Node>();
					list2.add(list.get(i));
					table.put(list.get(i).getWeight(), list2);
				}
			}
		}
	} 
	
	

}
