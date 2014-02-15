package luncher;



import java.util.Collections;

import com.beust.jcommander.JCommander;

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
		//System.out.println(Utils.ArbresToDot(Generator.mainList,"test"));
		
		for(int i = 0  ; i < Generator.mainList.size() ; i++){
			System.out.println("i = " + Generator.mainList.get(i).getWeight());
		}
		
		for(int i = 0 ; i < Generator.table.get(Config.size).size(); i++){
			System.out.println(Generator.table.get(Config.size).get(i));
		}
		
	}
	
}
