package struct;

import java.util.ArrayList;
import java.util.Stack;

public class Node {

	private String label; //incase needed later.
	private ArrayList<Node> sons;
	private int weight;
	private Node father;
	
	
	public Node(String label,int weight) {
		super();
		this.label = label;
		this.weight = weight;
		sons = new ArrayList<Node>();
	}

	
	
	public void addSon(Node son){
		son.setFather(this);
		sons.add(son);
	}

	public String getLabel() {
		return label;
	}


	public void setLabel(String label) {
		this.label = label;
	}


	public ArrayList<Node> getSons() {
		return sons;
	}


	public void setSons(ArrayList<Node> sons) {
		this.sons = sons;
	}


	public int getWeight() {
		return weight;
	}


	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	public Node getFather() {
		return father;
	}



	public void setFather(Node father) {
		this.father = father;
	}



	public String toDot(){
		StringBuffer sb = new StringBuffer();
		for(int i = 0 ; i < sons.size() ; i++){
			sb.append(label + "->" + sons.get(i).getLabel() + "; \n");
			sb.append(sons.get(i).toDot());
		}		
		return sb.toString();
	}

}
