package struct;

import java.util.ArrayList;

public class Node {

	String type;
	int weight;
	String label;
	ArrayList<Node> fils;
	//ArrayList<String> filsTypes;
	
	
	public Node(String type,int weight){
		this.type = type;
		this.weight = weight;
		fils = new ArrayList<Node>();
	}

	
	public Node(Node n){
		this.type = n.getType();
		this.weight = n.getWeight();
		this.fils = new ArrayList<Node>();

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
		this.weight = this.weight + n.getWeight();
	}

	public void setFils(ArrayList<Node> fils) {
		this.fils = fils;
	}


	//public ArrayList<String> getFilsTypes() {
		//return filsTypes;
	//}


	/*public void setFilsTypes(ArrayList<String> filsTypes) {
		this.filsTypes = filsTypes;
	}*/

	
	@SuppressWarnings("unchecked")
	public static Node clone(Node n){
		if(n instanceof Node){
			Node res  = new Node(n.getLabel(),n.getWeight());
			res.setType(n.getType());
			for(int i = 0 ; i < n.getFils().size() ; i++){
				res.getFils().add(Node.clone(n.getFils().get(i)));
			}
			return res;
		}else{
			return null;
		}
	}
	
	
	public ArrayList<Node> AddLevel(Node base){
		ArrayList<Node> list = new ArrayList<Node>();
		for(int i = 0 ; i < fils.size() ; i++){
			if(fils.get(i).getFils().size() == 0){
				Node tmp =  Node.clone(this);
				tmp.setWeight(tmp.getWeight() + base.weight - tmp.getFils().get(i).getWeight());
				tmp.getFils().set(i, base);
				list.add(tmp);
			}else{
				ArrayList<Node> tmp = fils.get(i).AddLevel(base);
				for(int j = 0 ; j < tmp.size() ; j++){
					Node n = Node.clone(this);
					n.setWeight(n.getWeight() - n.getFils().get(i).getWeight() + tmp.get(j).getWeight());
					n.getFils().set(i, tmp.get(j));
					list.add(n);
				}
			}
		}
		return list;
	}

	


	@Override
	public String toString() {
		return "Node [type=" + type + ", weight=" + weight + ", label=" + label
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
