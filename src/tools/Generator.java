package tools;

import java.util.ArrayList;

import struct.Composant;
import struct.Node;
import struct.Node2;

public class Generator {

	public static Node terminal;
	
	public static ArrayList<Node> terminaux = new ArrayList<Node>();
	
	
	public static ArrayList<Node> constructers = new ArrayList<Node>();
	public static ArrayList<Node> mainList = new ArrayList<Node>();
	public static ArrayList<Node> list = new ArrayList<Node>();
	
	
	public static void gen() {
		for(int i = 0 ; i < Config.labels.size() ; i++){
			ArrayList<Composant> list = Config.hash.get(Config.labels.get(i));
			for(int j = 0 ; j < list.size() ;j++){
				Composant c = list.get(j);
				System.out.println(c.toString());
				int nbFils = 0;
				for(int z = 0 ; z < c.getList().size() ; z++){
					if(Config.labels.contains(c.getList().get(i))){
						nbFils++;
					}
				}
				if(nbFils == 0){		//considers only the first one met.
					terminal = new Node(Config.labels.get(i),c.getWeight());
					terminaux.add(terminal);
					//Config.hash.get(Config.labels.get(i)).remove(c);
				}
			}
		}
		for(int i = 0 ; i < Config.labels.size() ; i++){
			ArrayList<Composant> list = Config.hash.get(Config.labels.get(i));
			for(int j = 0 ; j < list.size() ; j++){
				Composant c = list.get(j);
				Node tmp = new Node(Config.labels.get(i), c.getWeight());
				ArrayList<Node> listNode = new ArrayList<Node>();
				listNode.add(tmp);
				for(int z = 0 ; z < c.getList().size() ; z++){
					for(int x = 0 ; x < listNode.size() ; x++){
						for(int y = 0 ; y < terminaux.size() ; y++){
							Node terminal = terminaux.get(y);
							if(c.getList().get(z).equals(terminal.getType())){
								tmp.addFils(Node.clone(terminal));
							}else{
								//appel le fonction qui retour un liste et apres parcours le liste clone et ajoute le fils et ajoute au list.
							}
						}
					}					
				}
				constructers.addAll(listNode);
				mainList.addAll(listNode);
			}
		}
		
	}
	
	public static void generation(){
		int taille = mainList.size();
		for(int i = 0 ; i < taille ; i++){
			System.out.println(i);
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
