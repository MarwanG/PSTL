package struct;

import java.util.ArrayList;

public class Node {

	String type;
	int weight;
	String label;
	ArrayList<Node> fils;
	ArrayList<String> filsTypes;
	
	
	public Node(String type,int weight){
		this.type = type;
		this.weight = weight;
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


	public void setFils(ArrayList<Node> fils) {
		this.fils = fils;
	}


	public ArrayList<String> getFilsTypes() {
		return filsTypes;
	}


	public void setFilsTypes(ArrayList<String> filsTypes) {
		this.filsTypes = filsTypes;
	}
	
	
	
}
