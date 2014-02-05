package luncher;

import struct.Node;
import tools.Config;
import tools.Generator;
import tools.Parser;

public class Main {

	public static void main(String args[]){
		Parser.readFile("binary.spec");
		//System.out.println(Config.list.toString());
		//Generator.gen();
		
		
	/*	
		
		Node f = new Node("a",1);
		Node f2 = new Node("b",1);
		Node l1 = new Node("c",1);
		
		Node l2 = new Node("d",1);
		Node l3 = new Node("e",1);
		
		Node l4 = new Node("w",1);
		Node l5 = new Node("p",1);
		
		f2.addSon(l2);
		f2.addSon(l3);
		
		f.addSon(f2);
		f.addSon(l1);
		
		l1.addSon(l4);
		l1.addSon(l5);
		
		
		String dot = "digraph graphname { \n";
		dot+=f.toDot();
		dot+="\n }";
		System.out.println(dot);
		*/
		
	}
	
}
