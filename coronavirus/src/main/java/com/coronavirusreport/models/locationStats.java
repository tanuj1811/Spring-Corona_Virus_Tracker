
package com.coronavirusreport.models;

public class locationStats {

	private String COUNTRY;
	private String STATE;
	private int Total_Cases;
	private int New_Cases =0;
	
	
	
	public int getNew_Cases() {
		return New_Cases;
	}
	public void setNew_Cases(int new_Cases) {
		New_Cases = new_Cases;
	}
	public String getCOUNTRY() {
		return COUNTRY;
	}
	public void setCOUNTRY(String cOUNTRY) {
		COUNTRY = cOUNTRY;
	}
	public int getTotal_Cases() {
		return Total_Cases;
	}
	public void setTotal_Cases(int total_Cases) {
		Total_Cases = total_Cases;
	}
	public String getSTATE() {
		return STATE;
	}
	public void setSTATE(String sTATE) {
		STATE = sTATE;
	}
	@Override
	public String toString() {
		return "["+COUNTRY +", " +STATE + ", " + Total_Cases + ", " + New_Cases + "]";
	}
	
	
	
	
	
	
}
