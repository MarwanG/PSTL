package luncher;

import tools.Config;
import tools.Parser;

public class Main {

	public static void main(String args[]){
		Parser.readFile("binary.spec");
		System.out.println(Config.list.toString());
	}
	
}
