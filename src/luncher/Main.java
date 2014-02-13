package luncher;



import com.beust.jcommander.JCommander;

import tools.Config;
import tools.Generator;
import tools.Parser;
import tools.Utils;

public class Main {

	public static void main(String args[]){

		JCommander cmd = new JCommander(new Config(),args);
		
		
		Parser.readFile(Config.file);
		
		Generator.gen();
		Generator.generation(Config.size);
		System.out.println(Utils.ArbreToDot(Generator.mainList.get(4),"test"));

	}
	
}
