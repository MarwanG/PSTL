package struct;


import java.util.ArrayList;
import java.util.Collections;

import tools.ToolNode;


public class Node {

	String type;
	int weight;
	String label;
	ArrayList<Node> fils;
	int weightAlone;
	int nbFils;
	
	
	/**
	 * Constructer 
	 * @param type String correponds to weight
	 * @param weight correponds to weight of the node as well as weight alone.
	 */
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

	
	/**
	 * Creates Node exactly like the Node passed in parameter.
	 * @param n
	 */
	public Node(Node n){
		this.type = n.getType();
		this.weight = n.getWeight();
		this.fils = new ArrayList<Node>();
		weightAlone = n.getWeightAlone();
		nbFils = n.getNbFils();
	}



	/**
	 * Adds Node n as a son to its list of sons.
	 * @param n
	 */
	public void addFils(Node n){
		this.fils.add(n);
		if(n.getWeight() != -1){
			this.weight = this.weight + n.getWeight();
		}else{
			System.out.println("i wont add " + n.getWeight());
		}
		nbFils = fils.size();
	}

	
	/**
	 * replaces all possible leafs with base node
	 * @param base
	 * @return ArrayList<Node> corresponds to all possible options.
	 */
	public ArrayList<Node> AddLevel(Node base){
		ArrayList<Node> list = new ArrayList<Node>();
		for(int i = 0 ; i < fils.size() ; i++){
			if(fils.get(i).getFils().size() == 0  && !fils.get(i).getType().equals("non") /* && fils.get(i).getType().equals(base.getType())*/){
				Node tmp =  ToolNode.clone(this);
				tmp.setWeight(tmp.getWeight() + base.weight - tmp.getFils().get(i).getWeight());
				tmp.getFils().set(i, base);
				if(tmp.getWeight() > this.weight)
					list.add(tmp);
			}else{
				ArrayList<Node> tmp = fils.get(i).AddLevel(base);
				for(int j = 0 ; j < tmp.size() ; j++){
					Node n = ToolNode.clone(this);
					n.setWeight(n.getWeight() - n.getFils().get(i).getWeight() + tmp.get(j).getWeight());
					n.getFils().set(i, tmp.get(j));
					if(n.getWeight() > this.weight)
						list.add(n);
				}
			}
		}
		return list;
	}
	
	

	/**
	 * Returns normalized label of tree.
	 * @return
	 */
	
	public String toNormalized() {
		return ToolNode.removeZeros(this).toNormalized2();
	}
	public String toNormalized2() {
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
			StringBuffer sb;
			sb = new StringBuffer("0");	
		
			for (int i = 0; i<normalizedFilsNames.size();i++){
				sb.append(normalizedFilsNames.get(i));
			}
			sb.append("1");
			return sb.toString();
		}
	}
	
	public boolean containsSET(){
		if(this.getFils().isEmpty())
			return false;
		if(this instanceof SETNode){
			return true;
		}else{
			for(int i = 0 ; i < this.getFils().size() ; i++){
				if(this.getFils().get(i).containsSET())
					return true;
			}
			return false;
		}
	}
		
	//STATIC METHODS

	
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
			return ((this.getWeightAlone() == tmp.getWeightAlone()) && (this.fils.size() == tmp.getFils().size()) &&(this.weight == tmp.getWeight())&&(this.fils.equals(tmp.getFils())));
		}else{
			return false;
		}
	}
	
	
	public String toDot(){
		StringBuffer sb = new StringBuffer();
		if(fils.size() == 0){
			sb.append(label);
		}
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
	
	
}
