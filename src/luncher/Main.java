package luncher;

import struct.Node;
import struct.Node2;
import tools.Config;
import tools.Generator;
import tools.Parser;
import tools.Utils;

public class Main {

	public static void main(String args[]){
	
	
		Parser.readFile("binary.spec");
		Generator.gen();

		Generator.generation();

		System.out.println("mon arbre");
		System.out.println(Generator.mainList.get(4));
		System.out.println("fin d'arbre");
		
		
		System.out.println(Utils.ArbreToDot(Generator.mainList.get(4), "test"));
		
	}
	
}
