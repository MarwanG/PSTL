package tools;

import java.util.ArrayList;

import struct.Node;

public class Generator {

	public static Node base;
	public static ArrayList<Node> mainList = new ArrayList<Node>();
	public static ArrayList<Node> list = new ArrayList<Node>();
	
	
	public static void gen() {
		for(int i = 0 ; i < Config.list.size() ; i++){
			if(Config.list.get(i).getNbSons() == 0){
				base = new Node("0",Config.list.get(i).getWeight(),0);
			}
		}
		
		for(int i = 0 ; i < Config.list.size() ; i++){
			if(Config.list.get(i).getNbSons() > 0){
				Node n = new Node("1",Config.list.get(i).getWeight(),Config.list.get(i).getNbSons());
				n.AddSons(Config.list.get(i).getNbSons(),base);
				mainList.add(n);
				list.add(n);
			}
		}
		//System.out.println(mainList.toString());
		
		mainList.addAll(list.get(0).AddLevel(list.get(0)));
		
		System.out.println(mainList.toString());
	}
}
