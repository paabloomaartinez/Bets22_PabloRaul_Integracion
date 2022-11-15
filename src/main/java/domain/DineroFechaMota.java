package domain;

import java.util.Date;

public class DineroFechaMota {

	private double dirua;
	private Date data;
	private String mota;
	
	public DineroFechaMota(double dirua, Date data2, String mota) {
		this.dirua = dirua;
		this.data = data2;
		this.mota = mota;
	}

	public double getDirua() {
		return dirua;
	}


	public Date getData() {
		return data;
	}


	public String getMota() {
		return mota;
	}
}
