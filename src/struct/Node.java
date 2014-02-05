package struct;

import java.util.ArrayList;
import java.util.Stack;

public class Node {

	private String label; //incase needed later.
	private ArrayList<Node> sons;
	private int weight;
	private Node father;
	private int nb;
	
	
	public Node(String label,int weight,int nb) {
		super();
		this.label = label;
		this.weight = weight;
		this.nb = nb;
		sons = new ArrayList<Node>();
	}

	
	public void AddSons(int nb, Node base){
		for(int i = 0 ; i < nb ; i++){
			base.setLabel(i+"");
			sons.add(base);
		}
		weight = weight + (base.getWeight()*nb);
	}
	
	
	@SuppressWarnings("unchecked")
	public static Node clone(Node n){
		Node res  = new Node(n.getLabel(),n.getWeight(),n.getSons().size());
		res.setSons((ArrayList<Node>) n.getSons().clone());
		return res;
	}
	
	
	
	//Add level is missing the trail to get all other combinons only for now i can add only 1 son at time.
	// 2nd loop needed to add the rest while ieme son is actually base.
	
	public ArrayList<Node> AddLevel(Node base){
		ArrayList<Node> list = new ArrayList<Node>();
		for(int i = 0 ; i < sons.size() ; i++){
			if(sons.get(i).getSons().size() == 0){
				Node tmp =  Node.clone(this);
				tmp.setWeight(tmp.getWeight() + base.weight - tmp.getSons().get(i).getWeight());
				tmp.getSons().set(i, base);
				//System.out.println("TMP = " + tmp.toString());
				list.add(tmp);
			}else{
				list.addAll(sons.get(i).AddLevel(base));
			}
		}
		//System.out.println("List = ");
	//	System.out.println(list.toString());
		return list;
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


	public String toString(){
		return "Node : " + label + " weight : " + weight + " SONS : " + sons.toString() + "\n"; 
	}
	

	public String toDot(){
		StringBuffer sb = new StringBuffer();

		sb.append("Node : " + label + " w = " + weight + "\n");
		
		for(int i = 0 ; i < sons.size() ; i++){
			sb.append(label + "->" + sons.get(i).getLabel() + "; \n");
			sb.append(sons.get(i).toDot());
		}		
		return sb.toString();
	}

}
