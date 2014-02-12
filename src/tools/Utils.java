package tools;

import java.util.Stack;

import struct.Node;

	public class Utils {

		
		
		public static String ArbreToDot(Node n,String graphName){
			Node labeled = labelNode(n,0);	
			System.out.println(labeled.toString());
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
				System.out.println("Label = " + tmp.getLabel() + " weight = " + tmp.getWeight());
				
				for(int i = 0 ; i < tmp.getFils().size() ; i++){
					stack.add(tmp.getFils().get(i));
				}
				j++;
			}
			return label;
		}
	}

