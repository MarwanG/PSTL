package luncher;



import java.util.ArrayList;
import java.util.Collections;

import com.beust.jcommander.JCommander;

import struct.Node;
import tools.Config;
import tools.Generator;
import tools.NodeCompartor;
import tools.Parser;
import tools.Utils;

public class Main {

	public static void main(String args[]){

		JCommander cmd = new JCommander(new Config(),args);
		
		
		Parser.readFile(Config.file);
		
		Generator.gen();

		
		
		Generator.generation(Config.size);
		
		
		
		for(Integer i: Generator.table.keySet()){
			ArrayList<Node> l = Generator.table.get(i);
			for(int k = 0 ; k < l.size() ; k++)
				System.out.println(l.get(k).getWeight());
		}
	
	}
	
}
