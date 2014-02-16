package luncher;



import java.util.ArrayList;
import java.util.Collections;

import com.beust.jcommander.JCommander;

import struct.Node;
import tools.Config;
import tools.Generator;
import tools.NodeCompartor;
import tools.Parser;
import tools.PrintUtils;
import tools.Stat;

public class Main {

	public static void main(String args[]){

		JCommander cmd = new JCommander(new Config(),args);
		Parser.readFile(Config.file);		
		Generator.gen();
		Generator.generation(Config.size);		
		PrintUtils.toFile();
		Stat.writeStat();
	}
	
}
