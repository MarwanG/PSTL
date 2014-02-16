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
	
		//System.out.println(Generator.constructers.toString());
		System.out.println(Generator.mainList.get(0));
		Generator.generation(Config.size);
		System.out.println(Generator.mainList.get(1));
	}
	
}
