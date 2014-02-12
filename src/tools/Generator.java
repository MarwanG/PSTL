package tools;

import java.util.ArrayList;

import struct.Composant;
import struct.Node;
import struct.Node2;

public class Generator {

	public static ArrayList<Node> leaf = new ArrayList<Node>();
	
	
	public static ArrayList<Node> constructers = new ArrayList<Node>();
	public static ArrayList<Node> mainList = new ArrayList<Node>();
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
		System.out.println("====================");
		if(Config.verbose >= 2 )
			System.out.println("DONE");
		
	
		if(Config.verbose >= 2 )
			System.out.print("Generating possible Constructors");
		for(int i = 0 ; i < Config.labels.size() ; i++){
			ArrayList<Composant> list = Config.hash.get(Config.labels.get(i));
			for(int j = 0 ; j < list.size() ; j++){
				Composant c = list.get(j);
				generateConstructers(c,Config.labels.get(i));
			}			
		}
		if(Config.verbose >= 2 )
			System.out.println("\t OK");
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
							mainList.add(n2);
						}else{
							list.add(n2);
						}
						
					}
				}
			}
		}
	}
	
	
	
	public static void generation(int g){
		int taille = mainList.size();
		for(int x = 0 ; x < g ; x++)
			if(Config.verbose >= 2 )
				System.out.println("Generation #"+x);
			for(int i = 0 ; i < taille ; i++){
				Node tmp = mainList.get(i);
				for(int j = 0 ; j < tmp.getFils().size() ; j++){
					for(int z = 0 ; z < constructers.size() ; z++)
						addList(tmp.AddLevel(constructers.get(z)));
				}
			}
	}
	
	private static void addList(ArrayList<Node> list){
		for(int i = 0 ; i < list.size() ;i++){
			if(!mainList.contains(list.get(i))){
				mainList.add(list.get(i));
			}
		}
	}
}
