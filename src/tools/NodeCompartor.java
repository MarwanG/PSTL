package tools;

import java.util.Comparator;

import struct.Node;

public class NodeCompartor implements Comparator<Node> {

	@Override
	public int compare(Node o1, Node o2) {
		return o1.getWeight() - o2.getWeight();
	}

	

}
