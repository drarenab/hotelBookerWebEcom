package com.models;

public class JsonResult {

	private long code;
	private Object resultat;
	
	
	public JsonResult(long code, Object resultat) {
		super();
		this.code = code;
		this.resultat = resultat;
	}
	public long getCode() {
		return code;
	}
	public void setCode(long code) {
		this.code = code;
	}
	public Object getResultat() {
		return resultat;
	}
	public void setResultat(Object resultat) {
		this.resultat = resultat;
	}
	
	
	
}
