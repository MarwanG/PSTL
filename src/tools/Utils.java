package tools;

import java.util.ArrayList;
import java.util.Stack;

import struct.Node;

	public class Utils {
		
		public static String ArbresToDot(ArrayList<Node> list,String graphName){
			String res ="";
			ArrayList<Node> tmp = labelNode(list,0);
			res = "digraph " +graphName+ "{  \n";		
			for(int i = 0 ; i < tmp.size() ;i++){
				res += tmp.get(i).toDot();
			}
			res +="\n }";
			return res;
		}
		
		public static String ArbreToDot(Node n,String graphName){
			Node labeled = labelNode(n,0);	
			String res = null;
			res = "digraph " +graphName+ "{  \n";
			res += labeled.toDot();
			res +="\n }";
			return res;
		}
		
		
		public static String ArbreToText(Node n){
			Node labeled = labelNode(n,0);	
			StringBuffer sb = new StringBuffer();
			sb.append(labeled.getLabel());
			sb.append("\n");
			for(int i = 0 ; i < labeled.getFils().size() ; i++){
				ArbreToTextTmp(labeled.getFils().get(i),1,sb);
			}
			return sb.toString();
		}
		
		
		private static void ArbreToTextTmp(Node n , int nb , StringBuffer sb){
			for(int i = 0 ; i < nb ; i++){
				sb.append(" ");
			}
			sb.append(n.getLabel());
			sb.append("\n");
			for(int i = 0 ; i < n.getFils().size() ; i++){
				ArbreToTextTmp(n.getFils().get(i),nb+1,sb);
			}
		}
		
		
		private static Node labelNode(Node n,int start){
			Node label = Node.clone(n);
			Stack<Node> stack = new Stack<Node>();
			stack.add(label);		
			int j = start;
			while(!stack.isEmpty()){
				Node tmp = stack.pop();
				tmp.setLabel(j+"");	
				for(int i = 0 ; i < tmp.getFils().size() ; i++){
					stack.add(tmp.getFils().get(i));
				}
				j++;
			}
			return label;
		}
		
		private static ArrayList<Node> labelNode(ArrayList<Node> n,int start){
			ArrayList<Node> list = new ArrayList<Node>();
			int j = start;
			for(int i = 0 ; i < n.size() ; i++){
				Node label = Node.clone(n.get(i));
				Stack<Node> stack = new Stack<Node>();
				stack.add(label);		
				while(!stack.isEmpty()){
					Node tmp = stack.pop();
					tmp.setLabel(j+"");	
					for(int z = 0 ; z < tmp.getFils().size() ; z++){
						stack.add(tmp.getFils().get(z));
					}
					j++;
				}
				j++;
				list.add(label);
			}
			return list;
		}
}

