package com.cleon.workbook.rest;

// POJO simple para recibir datos JSON
public class CalculoRequest {
    private double principal;
    private double tasa;
    private int anios;
    
	public double getPrincipal() {
		return principal;
	}
	public void setPrincipal(double principal) {
		this.principal = principal;
	}
	public double getTasa() {
		return tasa;
	}
	public void setTasa(double tasa) {
		this.tasa = tasa;
	}
	public int getAnios() {
		return anios;
	}
	public void setAnios(int anios) {
		this.anios = anios;
	}
    
}