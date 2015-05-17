package com.ioc.edunet;

import java.util.ArrayList;
import java.util.List;

public class Alumne {
	private String idUsuari;
	private String nom;
	private String cognom1;
	private String cognom2;
	private List<Missatge> llistaMissatgesNous = new ArrayList<Missatge>();
	
	public Alumne () {
		
	}
	
	public void setUsuari (String usuari) {
		idUsuari = usuari;
	}
	
	public void setNom (String n) {
		nom = n;
	}
	
	public void setCognom1 (String c1) {
		cognom1 = c1;
	}
	
	public void setCognom2 (String c2) {
		cognom2 = c2;
	} 
	
	public String getUsuari () {
		return idUsuari;
	}
	
	public String getNom () {
		return nom;
	}
	
	public String getCognom1() {
		return cognom1;
	}
	
	public String getCognom2() {
		return cognom2;
	}
	
	public int numMissatgesNous() {
		return llistaMissatgesNous.size();
	}
	
	public void afegirMissatgeNou(Missatge m) {
		llistaMissatgesNous.add(m);
	}
	
	public Missatge obtenirMissatgeNou(int i) {
		return llistaMissatgesNous.get(i);
	}
	
	public void esborrarMissatges () {
		llistaMissatgesNous.clear();
	}
}
