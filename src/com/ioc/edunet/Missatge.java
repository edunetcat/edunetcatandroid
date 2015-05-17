package com.ioc.edunet;

public class Missatge {
	private int id;
	private String titol;
	private String contingut;
	private String emisor;
	private boolean esNou;

	public Missatge (int numId, String t, String c, String e) {
		id = numId;
		titol = t;
		contingut = c;
		emisor = e;
	}
	
	public int getId () {
		return id;
	}
	
	public String getTitol() {
		return titol;
	}
	
	public String getContingut() {
		return contingut;
	}
	
	public String getEmisor() {
		return emisor;
	}
	
	public void setNou() {
		esNou = true;
	}
	
	public void setAntic() {
		esNou = false;
	}
	
	//retorna si el missatge s'ha llegit o no
	public boolean esNou() {
		return esNou;
	}
}
