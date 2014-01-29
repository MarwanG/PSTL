package struct;

import java.util.ArrayList;

public class Composant {


	int weight;
	int nbSons;

	
	public Composant(int w , int n){
		this.weight = w;
		this.nbSons = n;
	}
	
	
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


	@Override
	public String toString() {
		return "Composant [weight=" + weight + ", nbSons=" + nbSons + "]";
	}


	
	
	
}
