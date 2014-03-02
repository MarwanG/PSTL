package luncher;

import java.io.File;
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

	public static void main(String args[]) {

		JCommander cmd = new JCommander(new Config(), args);

		if(Config.help)
			cmd.usage();
		else
			if (new File(Config.file).exists()) {
				Parser.readFile(Config.file);
				Generator.gen();
				System.out.println(Generator.constructers);
			/*	Generator.generation(Config.size);
				PrintUtils.toFile();
				Stat.writeStat();*/
			} else {
				System.err.println("The File passed as parameter doesnt exisit");
			}
	}

}
