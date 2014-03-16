package tools;

import java.util.ArrayList;

import struct.Node;
import struct.SETNode;

public class ToolNode {

	
	public static ArrayList<Node> SETTreatement(ArrayList<Node> l){
		ArrayList<Node> newList = new ArrayList<Node>();
		for(int i = 0 ; i < l.size() ; i++){
			Node tmp = fix(l.get(i));
			System.out.println("TMP = " + tmp);
			if(!newList.contains(tmp)){
				newList.add(tmp);
			}
		}
		return newList;
	}
	
	public static Node fix(Node n){
		Node tmp = n;
		if(n instanceof SETNode){
			String label = n.toNormalized();		
			tmp = Node.getNormalizedNode(label);
		}
		for(int i = 0 ; i < tmp.getFils().size() ; i++ ){
			tmp.getFils().set(i, fix(tmp.getFils().get(i)));
		}		
		return tmp;
	}
	
	
	public static ArrayList<Node> tmp2(ArrayList<Node> l){
		ArrayList<String> list = new ArrayList<String>();
		for(int i = 0 ; i < l.size() ; i++){
			String label = l.get(i).toNormalized();
			if(!list.contains(label))
				list.add(label);
		}
		ArrayList<Node> tmp = new ArrayList<Node>();
		for(int i = 0 ; i < list.size() ; i++){
			tmp.add(Node.getNormalizedNode(list.get(i)));
		}
		return tmp;	
	}
}
