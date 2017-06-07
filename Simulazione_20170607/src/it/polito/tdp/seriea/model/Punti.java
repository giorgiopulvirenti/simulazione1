package it.polito.tdp.seriea.model;

import java.util.Comparator;

public class Punti implements Comparable <Punti>{
	Team a;
	int punti;
	
	
	public Punti(Team a, int punti) {
		super();
		this.a = a;
		this.punti = punti;
	}
	public Team getA() {
		return a;
	}
	public void setA(Team a) {
		this.a = a;
	}
	public int getPunti() {
		return punti;
	}
	public void setPunti(int punti) {
		this.punti = punti;
	}

	@Override
	public int compareTo(Punti arg0) {
		// TODO Auto-generated method stub
		return arg0.getPunti()-this.punti;
	}
	@Override
	public String toString() {
		return "a=" + a + ", punti=" + punti ;
	}
	

}
