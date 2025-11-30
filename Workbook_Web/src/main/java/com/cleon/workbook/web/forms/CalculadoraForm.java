package com.cleon.workbook.web.forms;

import org.apache.struts.action.ActionForm;

public class CalculadoraForm extends ActionForm {
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
    
    // Incluye el método reset() si es necesario
    // Incluye el método validate() si necesitas validación en el cliente
}