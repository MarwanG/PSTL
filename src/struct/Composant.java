package struct;

import java.util.ArrayList;

public class Composant {

	int weight;
	int nbSons;
	ArrayList<String> list;

	/**
	 * 
	 * @param w
	 *            the weight of the composant.
	 * @param n
	 *            the nomber of sons a composant can have.
	 */
	public Composant(int w, int n, ArrayList<String> list) {
		this.weight = w;
		this.nbSons = n;
		this.list = list;
	}

	//GETTERS AND SETTERS.
	
	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getNbSons() {
		return nbSons;
	}

	public void setNbSons(int nbSons) {
		this.nbSons = nbSons;
	}

	public ArrayList<String> getList() {
		return list;
	}

	public void setList(ArrayList<String> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "Composant [weight=" + weight + ", Label= " + list + "]";
	}

}
