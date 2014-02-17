package tools;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

import struct.Node;

public class PrintUtils {

	public static void toFile() {
		try {
			if (Config.verbose >= 1) {
				System.out.println("Generating File : " + Config.out + "."
						+ Config.format);
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(
					Config.out + "." + Config.format));
			ArrayList<Node> listAll = new ArrayList<Node>();
			if (Config.all) {
				for (Integer k : Generator.table.keySet()) {
					listAll.addAll(Generator.table.get(k));
				}
			} else {
				listAll.addAll(Generator.table.get(Config.size));
			}
			switch (Config.format) {
			case "dot":
				writer.write(ArbresToDot(listAll, Config.out));
				writer.close();
				break;
			case "txt":
				writer.write(ArbresToText(listAll));
				writer.close();
				break;
			case "json":
				System.out.println("not yet");
				writer.close();
				break;

			case "png":
				writer.write(ArbresToDot(listAll, Config.out));
				writer.close();
				dotGenerator();
				break;
			default:
				System.err
						.println("unsupported format will generate the default format instead");
				writer.write(ArbresToDot(listAll, Config.out));
				writer.close();
				dotGenerator();
				break;
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String ArbresToDot(ArrayList<Node> list, String graphName) {
		String res = "";
		ArrayList<Node> tmp = labelNode(list, 0);
		res = "digraph " + graphName + "{  \n";
		for (int i = 0; i < tmp.size(); i++) {
			res += tmp.get(i).toDot();
		}
		res += "\n }";
		return res;
	}

	public static String ArbreToDot(Node n, String graphName) {
		Node labeled = labelNode(n, 0);
		String res = null;
		res = "digraph " + graphName + "{  \n";
		res += labeled.toDot();
		res += "\n }";
		return res;
	}

	public static String ArbresToText(ArrayList<Node> n) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < n.size(); i++) {
			sb.append("Arbre : " + i);
			sb.append(ArbreToText(n.get(i)));
			sb.append("\n");
		}
		return sb.toString();
	}

	public static String ArbreToText(Node n) {
		Node labeled = labelNode(n, 0);
		StringBuffer sb = new StringBuffer();
		sb.append(labeled.getLabel());
		sb.append("\n");
		for (int i = 0; i < labeled.getFils().size(); i++) {
			ArbreToTextTmp(labeled.getFils().get(i), 1, sb);
		}
		return sb.toString();
	}

	private static void ArbreToTextTmp(Node n, int nb, StringBuffer sb) {
		for (int i = 0; i < nb; i++) {
			sb.append(" ");
		}
		sb.append(n.getLabel());
		sb.append("\n");
		for (int i = 0; i < n.getFils().size(); i++) {
			ArbreToTextTmp(n.getFils().get(i), nb + 1, sb);
		}
	}

	private static Node labelNode(Node n, int start) {
		Node label = Node.clone(n);
		Stack<Node> stack = new Stack<Node>();
		stack.add(label);
		int j = start;
		while (!stack.isEmpty()) {
			Node tmp = stack.pop();
			tmp.setLabel(j + "");
			for (int i = 0; i < tmp.getFils().size(); i++) {
				stack.add(tmp.getFils().get(i));
			}
			j++;
		}
		return label;
	}

	private static ArrayList<Node> labelNode(ArrayList<Node> n, int start) {
		ArrayList<Node> list = new ArrayList<Node>();
		int j = start;
		for (int i = 0; i < n.size(); i++) {
			Node label = Node.clone(n.get(i));
			Stack<Node> stack = new Stack<Node>();
			stack.add(label);
			while (!stack.isEmpty()) {
				Node tmp = stack.pop();
				tmp.setLabel(j + "");
				for (int z = 0; z < tmp.getFils().size(); z++) {
					stack.add(tmp.getFils().get(z));
				}
				j++;
			}
			j++;
			list.add(label);
		}
		return list;
	}

	private static void dotGenerator() {
		try {

			Runtime rt = Runtime.getRuntime();
			Process pr = rt.exec("which dot");

			pr.waitFor();

			boolean canDo = (pr.exitValue() == 0);
			if (!canDo) {
				System.err
						.println("dot is not installed on this machine will not be able to generate png file");
			} else {
				if (Config.verbose >= 1) {
					System.out.print("Generating Trees in format png......");
				}
				pr = Runtime.getRuntime().exec(
						"dot -Tpng -o " + Config.out + ".png " + Config.out
								+ "." + Config.format);
				try {
					pr.waitFor();
				} catch (InterruptedException e) {
					e.printStackTrace();
					throw new RuntimeException();
				}
				System.out.println("OK");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
