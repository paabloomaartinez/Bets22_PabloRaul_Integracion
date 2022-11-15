package domain;

import java.util.Vector;

public class DatosApuesta {

	private Registered u;
	private Vector<Quote> q;
	private double balioa;
	private int apustuaGalarazi;
	
	
	public DatosApuesta(Registered u, Vector<Quote> q, double balioa, int apustuaGalarazi) {
		super();
		this.u = u;
		this.q = q;
		this.balioa = balioa;
		this.apustuaGalarazi = apustuaGalarazi;
	}


	public Registered getU() {
		return u;
	}


	public void setU(Registered u) {
		this.u = u;
	}


	public Vector<Quote> getQ() {
		return q;
	}


	public void setQ(Vector<Quote> q) {
		this.q = q;
	}


	public double getBalioa() {
		return balioa;
	}


	public void setBalioa(double balioa) {
		this.balioa = balioa;
	}


	public int getApustuaGalarazi() {
		return apustuaGalarazi;
	}


	public void setApustuaGalarazi(int apustuaGalarazi) {
		this.apustuaGalarazi = apustuaGalarazi;
	}
	
	
}
