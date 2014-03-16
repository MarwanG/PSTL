package struct;

import java.util.ArrayList;
import java.util.Collections;


public class Node {

	String type;
	int weight;
	String label;
	ArrayList<Node> fils;
	int weightAlone;
	int nbFils;
	
	
	public Node(String type,int weight){
		this.type = type;
		if(weight == -1){
			this.weight = 0;
		}else{
			this.weight = weight;
		}
		fils = new ArrayList<Node>();
		weightAlone = weight;
		nbFils = fils.size();
	}

	
	public Node(Node n){
		this.type = n.getType();
		this.weight = n.getWeight();
		this.fils = new ArrayList<Node>();
		weightAlone = n.getWeightAlone();
		nbFils = n.getNbFils();
	}

	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public int getWeight() {
		return weight;
	}


	public void setWeight(int weight) {
		this.weight = weight;
	}


	public String getLabel() {
		return label;
	}


	public void setLabel(String label) {
		this.label = label;
	}


	public ArrayList<Node> getFils() {
		return fils;
	}


	public void addFils(Node n){
		this.fils.add(n);
		if(n.getWeight() != -1){
			this.weight = this.weight + n.getWeight();
		}else{
			System.out.println("i wont add " + n.getWeight());
		}
		nbFils = fils.size();
	}

	public void setFils(ArrayList<Node> fils) {
		this.fils = fils;
		nbFils = fils.size();
	}
	
	


	public int getWeightAlone() {
		return weightAlone;
	}


	public void setWeightAlone(int weightAlone) {
		this.weightAlone = weightAlone;
	}


	public int getNbFils() {
		return fils.size();
	}


	public void setNbFils(int nbFils) {
		this.nbFils = nbFils;
	}


	public static Node clone(Node n){
		if(n instanceof SETNode){
			SETNode res  = new SETNode(n.getLabel(),n.getWeight());
			res.setWeightAlone(n.getWeightAlone());
			res.setType(n.getType());
			for(int i = 0 ; i < n.getFils().size() ; i++){
				res.getFils().add(Node.clone(n.getFils().get(i)));
			}			
			return res;
		}else if(n instanceof Node){
			Node res  = new Node(n.getLabel(),n.getWeight());
			res.setWeightAlone(n.getWeightAlone());
			res.setType(n.getType());
			for(int i = 0 ; i < n.getFils().size() ; i++){
				res.getFils().add(Node.clone(n.getFils().get(i)));
			}			
			return res;
		}else{
			return null;
		}
	}
	
	
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
				tmp.getFils().set(i, Node.removeZeros(tmp.getFils().get(i)));
				i++;
			}
		}
		return tmp;
	}
	
	
	public static ArrayList<Node> removeZeros(ArrayList<Node> n){
		ArrayList<Node> newList = new ArrayList<Node>();
		
		for(int i = 0 ; i < n.size() ; i++){
			Node tmp = Node.removeZeros(n.get(i));
			if(!newList.contains(tmp)){
				newList.add(tmp);
			}
		}		
		return newList;
	}
	
	
	public String toNormalized() {
		if (this.fils.size() == 0){
			return "01";
		}
		else {
			ArrayList<String> normalizedFilsNames = new ArrayList<String>();
			for (int i = 0; i< this.fils.size(); i++){
				normalizedFilsNames.add(this.fils.get(i).toNormalized());
			}
			Collections.sort(normalizedFilsNames);
			Collections.reverse(normalizedFilsNames);
			StringBuffer sb = new StringBuffer("0");
			for (int i = 0; i<normalizedFilsNames.size();i++){
				sb.append(normalizedFilsNames.get(i));
			}
			sb.append("1");
			return sb.toString();
		}
	}
	
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
				resultat.addFils(Node.getNormalizedNode(son));
			}
			return resultat;
		}
	}

	
	
	
	
	
	public ArrayList<Node> AddLevel(Node base){
		ArrayList<Node> list = new ArrayList<Node>();
		for(int i = 0 ; i < fils.size() ; i++){
			if(fils.get(i).getFils().size() == 0){
				Node tmp =  Node.clone(this);
				tmp.setWeight(tmp.getWeight() + base.weight - tmp.getFils().get(i).getWeight());
				tmp.getFils().set(i, base);
				if(tmp.getWeight() > this.weight)
					list.add(tmp);
			}else{
				ArrayList<Node> tmp = fils.get(i).AddLevel(base);
				for(int j = 0 ; j < tmp.size() ; j++){
					Node n = Node.clone(this);
					n.setWeight(n.getWeight() - n.getFils().get(i).getWeight() + tmp.get(j).getWeight());
					n.getFils().set(i, tmp.get(j));
					if(n.getWeight() > this.weight)
						list.add(n);
				}
			}
		}
		return list;
	}

	


	
	
	
	


	@Override
	public String toString() {
		return "Node [type=" + type + " , NODE , weight=" + weight + ", nbFils="
				+ fils.size() + ", weightAlone=" + weightAlone + ", label=" + label
				+ ", fils=" + fils + "]";
	}


	public String shortString(){
		return "Node [type=" + type + ", weight=" + weight + ", label=" + label+"]";
	}


	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Node){
			Node tmp = (Node) obj;
			return ((this.type.equals(tmp.getType())&&(this.weight == tmp.getWeight())&&(this.fils.equals(tmp.getFils()))));
		}else{
			return false;
		}
	}
	
	
	public String toDot(){
		StringBuffer sb = new StringBuffer();
		for(int i = 0 ; i < fils.size() ; i++){
			sb.append(label + "->" + fils.get(i).getLabel() + "; \n");	
			sb.append(fils.get(i).toDotTmp());
		}		
		return sb.toString();
	}
	
	private String toDotTmp(){
		StringBuffer sb = new StringBuffer();

		for(int i = 0 ; i < fils.size() ; i++){
			sb.append(label + "->" + fils.get(i).getLabel() + "; \n");
			sb.append(fils.get(i).toDotTmp());
		}		
		return sb.toString();
	}
	
	
	
}
