package luncher;

import javax.swing.JOptionPane;

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

		
		System.out.println(Utils.ArbresToDot(Generator.mainList, "test"));
		
	}
	
}
