package tools;

import java.util.ArrayList;

import struct.Composant;
import struct.Node;
import struct.Node2;

public class Generator {

	public static Node terminal;
	
	public static ArrayList<Node> terminaux = new ArrayList<Node>();
	
	public static ArrayList<Node> [] table;
	
	public static ArrayList<Node> constructers = new ArrayList<Node>();
	public static ArrayList<Node> mainList = new ArrayList<Node>();
	public static ArrayList<Node> list = new ArrayList<Node>();
	
	
	public static void gen() {
		for(int i = 0 ; i < Config.labels.size() ; i++){
			ArrayList<Composant> list = Config.hash.get(Config.labels.get(i));
			for(int j = 0 ; j < list.size() ;j++){
				Composant c = list.get(j);
				int nbFils = 0;
				for(int z = 0 ; z < c.getList().size() ; z++){
					if(Config.labels.contains(c.getList().get(i))){
						nbFils++;
					}
				}
				if(nbFils == 0){		//considers only the first one met.
					terminal = new Node(Config.labels.get(i),c.getWeight());
					terminaux.add(terminal);
				}
			}
		}
		for(int i = 0 ; i < Config.labels.size() ; i++){
			ArrayList<Composant> list = Config.hash.get(Config.labels.get(i));
			for(int j = 0 ; j < list.size() ; j++){
				Composant c = list.get(j);
				generateConstructers(c,Config.labels.get(i));
			}			
		}
	}
	
	
	private static void generateConstructers(Composant c,String type){
		ArrayList<Node> list = new ArrayList<Node>();
		ArrayList<String> sons = c.getList();
		String son = sons.get(0);
		for(int i = 0 ; i < terminaux.size() ; i++){
			if(terminaux.get(i).getType().equals(son)){
				Node n = new Node(type,c.getWeight());
				n.addFils(Node.clone(terminaux.get(i)));
				list.add(n);
			}
		}
		for(int j = 1 ; j < sons.size() ; j++){
			son = sons.get(j);
			int taille = list.size();
			for(int i = 0 ; i < taille ; i++){
				Node n = list.get(i);
				for(int z = 0 ; z < terminaux.size() ; z++){
					if(terminaux.get(z).getType().equals(son)){
						Node n2 = Node.clone(n);
						n2.addFils(Node.clone(terminaux.get(z)));
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
	
	
	
	public static void generation(){
		int taille = mainList.size();
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
