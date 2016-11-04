package de.csgt.domain;

public class Statistic {
	private String time = "Alles";
	private Double in =0.0;
	private Double out=0.0;
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Double getIn() {
		return in;
	}
	public void setIn(Double in) {
		this.in = in;
	}
	public Double getOut() {
		return out;
	}
	public void setOut(Double out) {
		this.out = out;
	}
	
	public Double getWin() {
		return in - out;
	}
}
