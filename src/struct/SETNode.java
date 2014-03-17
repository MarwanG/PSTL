package struct;

public class SETNode extends Node {

	public SETNode(String type, int weight) {
		super(type, weight);
	}

	@Override
	public String toString() {
		return "Node [type=" + type + " , SETNODE , weight=" + weight + ", nbFils="
				+ fils.size() + ", weightAlone=" + weightAlone + ", label=" + label
				+ ", fils=" + fils + "]";
	}

}
