package luncher;

import struct.Node;
import struct.Node2;
import tools.Config;
import tools.Generator;
import tools.Parser;
import tools.Utils;

public class Main {

	public static void main(String args[]){
	
	/*
		Parser.readFile("binary.spec");
		Generator.gen();
		System.out.println(Generator.constructers.toString());
		Generator.generation();
		System.out.println(Generator.mainList.toString());
	
		*/
	
		
		Node f = new Node("a",1);
		Node f2 = new Node("b",1);
		Node l1 = new Node("c",1);

		Node l2 = new Node("d",1);
		Node l3 = new Node("e",1);

		Node l4 = new Node("w",1);
		Node l5 = new Node("p",1);

		f2.addFils(l2);
		f2.addFils(l3);

		f.addFils(f2);
		f.addFils(l1);

		l1.addFils(l4);
		l1.addFils(l5);


		System.out.println(Utils.ArbreToDot(f, "test"));
		
	}
	
}
