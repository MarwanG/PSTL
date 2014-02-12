package luncher;

import com.beust.jcommander.JCommander;

import struct.Node2;
import tools.Config;
import tools.Generator;
import tools.Parser;

public class Main {

	public static void main(String args[]){
		

		JCommander cmd = new JCommander(new Config(),args);
		
		
		Parser.readFile(Config.file);
		
		Generator.gen();
		Generator.generation(Config.size);
		System.out.println(Generator.mainList.toString());

	}
	
}
