package tools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import struct.Composant;
import struct.Node;

public class Generator {

	public static ArrayList<Node> leaf = new ArrayList<Node>();

	public static HashMap<Integer, ArrayList<Node>> table;

	public static ArrayList<Node> constructers = new ArrayList<Node>();
	public static ArrayList<ArrayList<Node>> mainList = new ArrayList<ArrayList<Node>>();
	public static ArrayList<Node> list = new ArrayList<Node>();
	public static ArrayList<String> nameLeaf = new ArrayList<String>();

	public static void gen() {
		table = new HashMap<Integer, ArrayList<Node>>();
		if (Config.verbose >= 2){
			System.out.println("Generating possible composants");
		}
		if (Config.verbose >= 1){
			System.out.println("List of Composants :");
		}
		generateLeafs();
	
		if (Config.verbose >= 2){
			System.out.println("DONE");
		}
		if (Config.verbose >= 2){
			System.out.print("Generating possible Constructors.....\n");
		}
		
		mainList.add(new ArrayList<Node>());
		ArrayList<Composant> list = Config.hash.get(Config.labels.get(0));
		for (int j = 0; j < list.size(); j++) {
			Composant c = list.get(j);
			ArrayList<Node> x = generateConstructers(c, Config.labels.get(0));
			mainList.get(0).addAll(x);
		}

		if (Config.verbose >= 2){
			System.out.println("OK");
		}
		Collections.sort(Generator.constructers, new NodeCompartor());

	}

	private static ArrayList<Node> generateConstructers(Composant c, String type) {
		ArrayList<Node> list = new ArrayList<Node>();
		ArrayList<String> sons = c.getList();
		ArrayList<Node> tmp = new ArrayList<Node>();
		
		//First Son
		String son = sons.get(0);
		for (int i = 0; i < leaf.size(); i++) {
			if (leaf.get(i).getType().equals(son)) {
				Node n = new Node(type, c.getWeight());
				n.addFils(Node.clone(leaf.get(i)));
				list.add(n);
			}else{
				if (Config.labels.contains(son)) {
					test(son);
					for(int i1 = 0 ; i < mainList.get(0).size() ; i++){
						if(mainList.get(0).get(i1).getType().equals(son)){
							Node n = new Node(type,c.getWeight());
							n.addFils(Node.clone(mainList.get(0).get(i1)));
							list.add(n);
						}
					}
				}
			}
		}
		
		if (sons.size() == 1) {
			constructers.addAll(list);
			tmp.addAll(list);
		}
		
		//Rest of the sons
		for (int j = 1; j < sons.size(); j++) {
			son = sons.get(j);
			int taille = list.size();
			for (int i = 0; i < taille; i++) {
				Node n = list.get(i);
				for (int z = 0; z < leaf.size(); z++) {
					if (leaf.get(z).getType().equals(son)) {
						Node n2 = Node.clone(n);
						n2.addFils(Node.clone(leaf.get(z)));
						if (j == sons.size() - 1) {
							constructers.add(n2);
							tmp.add(n2);
						} else {
							list.add(n2);
						}
					} else if (nameLeaf.contains(son)) {
						Node n2 = Node.clone(n);
						Node term = Node.clone(leaf.get(z));
						term.setType(son);
						n2.addFils(term);
						if (j == sons.size() - 1) {
							constructers.add(n2);
							tmp.add(n2);
						} else {
							list.add(n2);
						}
					} else if (Config.labels.contains(son)) {
						for(int i1 = 0 ; i1 < mainList.get(0).size() ; i1++){
							if(mainList.get(0).get(i).getType().equals(son)){
								Node n2 = Node.clone(n);
								n2.addFils(Node.clone(mainList.get(0).get(i)));
								if (j == sons.size() - 1) {
									constructers.add(n2);
									tmp.add(n2);
								} else {
									list.add(n2);
								}
							}
						}
					}
				}
			}
		}
		return tmp;
	}

	
	
	private static void test(String c){
		System.out.println("test");
		ArrayList<Composant> list = Config.hash.get(c);
		for(int i = 0 ; i < list.size() ; i++){
			Composant comp = list.get(i);
			mainList.get(0).addAll(generateConstructers(comp,c));
		}
		
	}
	
	
	/**
	 * Function that generates G generation.
	 * @param g
	 */
	public static void generation(int g) {
		addToTable(constructers);
		int start = 0;
		while (true) {
			if (Config.verbose >= 1) {
				System.out.println("Generation : " + (start + 1));
			}
			System.out.println("start : " + start);
			ArrayList<Node> newList = new ArrayList<Node>();
			ArrayList<Node> list = mainList.get(start);
			int taille = list.size();
			int i = 0;
			Collections.sort(list, new NodeCompartor());
			if (start > g) { // check				
				break;
			}
			while (i < taille) {
				Node tmp = list.get(i);
				for (int j = 0; j < tmp.getFils().size(); j++) {
					for (int z = 0; z < constructers.size(); z++) {
						addList(tmp.AddLevel(constructers.get(z)), newList);
					}
				}
				i++;
			}
			mainList.add(newList);
			start++;
		}
	}
	
	
	private static void generateLeafs(){
		for (int i = 0; i < Config.labels.size(); i++) {
			ArrayList<Composant> list = Config.hash.get(Config.labels.get(i));
			for (int j = 0; j < list.size(); j++) {
				Composant c = list.get(j);
				if (Config.verbose >= 1) {
					System.out.println("\t" + c.toString());
				}
				int nbFils = 0;
				for (int z = 0; z < c.getList().size(); z++) {
					if (Config.labels.contains(c.getList().get(z))) {
						nbFils++;
					}
				}
				if (nbFils == 0 && !c.getList().get(i).contains("SEQ")) {
					leaf.add(new Node(Config.labels.get(i), c.getWeight()));
					nameLeaf.add(Config.labels.get(i));
				}
			}
		}

	}
	
	
	private static void addList(ArrayList<Node> list, ArrayList<Node> newList) {
		for (int i = 0; i < list.size(); i++) {
			if (!newList.contains(list.get(i))) {
				newList.add(list.get(i));
				if (table.containsKey(list.get(i).getWeight())) {
					if (!table.get(list.get(i).getWeight()).contains(
							list.get(i)))
						table.get(list.get(i).getWeight()).add(list.get(i));
				} else {
					ArrayList<Node> list2 = new ArrayList<Node>();
					list2.add(list.get(i));
					table.put(list.get(i).getWeight(), list2);
				}
			}
		}
	}

  
	private static void addToTable(ArrayList<Node> n){
		for(int i = 0 ; i < n.size() ; i++){
			addToTable(n.get(i));
		}
	}
	
	 private static void addToTable(Node n) {
			if (table.containsKey(n.getWeight())) {
				if (!table.get(n.getWeight()).contains(n))
					table.get(n.getWeight()).add(n);
			} else {
				ArrayList<Node> list2 = new ArrayList<Node>();
				list2.add(n);
				table.put(n.getWeight(), list2);
			}
		}
		

}
