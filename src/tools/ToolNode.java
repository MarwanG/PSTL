package tools;

import java.util.ArrayList;

import struct.Node;
import struct.SETNode;

public class ToolNode {


	public static ArrayList<Node> SETTreatement(ArrayList<Node> l){
		ArrayList<Node> newList = new ArrayList<Node>();
		ArrayList<String> labels = new ArrayList<String>();
		for(int i = 0 ; i < l.size() ; i++){
			if(l.get(i).containsSET()){
				if(!labels.contains(l.get(i).toNormalized())){
					Node tmp = fix(l.get(i));
					labels.add(tmp.toNormalized());
					newList.add(tmp);
				}
			}else{
				newList.add(l.get(i));
			}
		}
		return newList;
	}

	public static Node fix(Node n){
		Node tmp = n;
		if(n.getFils().size() == 0){
			return tmp;
		}else{
			ArrayList<Node> newL = new ArrayList<Node>();
			for(int i = 0 ; i < tmp.getFils().size() ; i++){
				newL.add(fix(n.getFils().get(i)));
			}
			if(tmp instanceof SETNode){
				String label = tmp.toNormalized();
				int weightAlone = tmp.getWeightAlone();
				int weight = tmp.getWeight();
				String type = tmp.getType();
				tmp = getNormalizedNode(label);
				tmp.setWeightAlone(weightAlone);
				tmp.setWeight(weight);
				tmp.setType(type);
				tmp.setFils(newL);
			}else{
				tmp.setFils(newL);
			}
			return tmp;
		}
	}

	
	/**
	 * Clones N (deep cloning)
	 * @param n	Node to be cloned
	 * @return
	 */
	public static Node clone(Node n){
		if(n instanceof SETNode){
			SETNode res  = new SETNode(n.getLabel(),n.getWeight());
			res.setWeightAlone(n.getWeightAlone());
			res.setType(n.getType());
			for(int i = 0 ; i < n.getFils().size() ; i++){
				res.getFils().add(ToolNode.clone(n.getFils().get(i)));
			}			
			return res;
		}else if(n instanceof Node){
			Node res  = new Node(n.getLabel(),n.getWeight());
			res.setWeightAlone(n.getWeightAlone());
			res.setType(n.getType());
			for(int i = 0 ; i < n.getFils().size() ; i++){
				res.getFils().add(ToolNode.clone(n.getFils().get(i)));
			}			
			return res;
		}else{
			return null;
		}
	}
	
	/**
	 * Goes around the tree removing zero-zero connection.
	 * @param n
	 * @return
	 */
	public static Node removeZeros(Node n){
		Node tmp = clone(n);
		int i = 0 ;
		while(i < tmp.getFils().size()){
			if(tmp.getFils().get(i).getWeightAlone() == -1){
				Node fils = tmp.getFils().remove(i);
				for(int i1 = fils.getFils().size() -1 ; i1 >= 0  ; i1--){
					tmp.getFils().add(i, fils.getFils().get(i1));
				}
			}else{
				tmp.getFils().set(i, ToolNode.removeZeros(tmp.getFils().get(i)));
				i++;
			}
		}
		return tmp;
	}
	
	/**
	 * Applies removeZeros to a list of trees.
	 * @param n
	 * @return
	 */
	public static ArrayList<Node> removeZeros(ArrayList<Node> n){
		ArrayList<Node> newList = new ArrayList<Node>();
		
		for(int i = 0 ; i < n.size() ; i++){
			Node tmp = ToolNode.removeZeros(n.get(i));
			if(!newList.contains(tmp)){
				newList.add(tmp);
			}
		}		
		return newList;
	}
	
	
	/**
	 * Returns a Tree from label passed as a parameter
	 * @param normalized String containing only 0 and 1s
	 * @return
	 */
	
	public static Node getNormalizedNode(String normalized){
		if (normalized == "01"){
				return new Node("NOTYPE",0);
		}
		else {
			Node resultat = new Node("NOTYPE",0);
			ArrayList<String> filsName = new ArrayList<String>();
			int cut = 0;
			int debutCut = 0, finCut = 0;
			for (int i = 1; i<normalized.length(); i++){
				int cutBefore = cut;
				if (normalized.charAt(i) == '0')
					cut++;
				else
					cut--;
				if (cut == 1 && cutBefore == 0)
					debutCut = i;
				else if (cut == 0 && cutBefore == 1){
					finCut = i;
					filsName.add(normalized.substring(debutCut, finCut+1));
				}
			}
			for (String son : filsName){
				resultat.addFils(ToolNode.getNormalizedNode(son));
			}
			return resultat;
		}
	}


	
	
}
