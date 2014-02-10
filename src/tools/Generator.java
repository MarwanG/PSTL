package tools;

import java.util.ArrayList;

import struct.Composant;
import struct.Node;
import struct.Node2;

public class Generator {

	public static Node base;
	
	public static ArrayList<Node2> constructers = new ArrayList<Node2>();
	public static ArrayList<Node2> mainList = new ArrayList<Node2>();
	public static ArrayList<Node2> list = new ArrayList<Node2>();
	
	
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
			}
		}
		
		for(int i = 0 ; i < Config.list.size() ; i++){
			if(Config.list.get(i).getNbSons() > 0){
				Node2 n = new Node2("1",Config.list.get(i).getWeight(),Config.list.get(i).getNbSons());
				//n.AddSons(Config.list.get(i).getNbSons(),base);
				mainList.add(n);
				list.add(n);
			}
		}
		//System.out.println(mainList.toString());
		
		mainList.addAll(list.get(0).AddLevel(list.get(0)));
		
		System.out.println(mainList.toString());
	}
}
