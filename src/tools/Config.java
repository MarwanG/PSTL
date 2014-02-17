package tools;

import java.util.ArrayList;
import java.util.HashMap;

import com.beust.jcommander.Parameter;

import struct.Composant;

public class Config {

	public static ArrayList<Composant> list = new ArrayList<Composant>();

	public static HashMap<String, ArrayList<Composant>> hash = new HashMap<String, ArrayList<Composant>>();

	public static ArrayList<String> labels = new ArrayList<String>();

	@Parameter(names = "-n", description = "Size of required trees")
	public static int size = 3;
	@Parameter(names = "-gram", description = "Path for grammer file to be used")
	public static String file = "";
	@Parameter(names = "-out", description = "Name of file to be used for output")
	public static String out = "Tree";
	@Parameter(names = "-format", description = "Format of output: json,dot,png(certain systems),txt")
	public static String format = "dot";
	@Parameter(names = "-verbose", description = "Level of verbosity")
	public static int verbose = 2;
	@Parameter(names = "-all", description = "Generation to return", arity = 1)
	public static boolean all = false;
	@Parameter(names = "-stat", description = "return stat file as well", arity = 1)
	public static boolean stat = false;
	@Parameter(names = "--help", help = true , description = "Shows usage")
	public static boolean help = false;

}
