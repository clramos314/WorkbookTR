package com.cleon.workbook.web.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class CalculadoraForm extends ActionForm {
    /**
	 * 
	 */
	private static final long serialVersionUID = -9155621757950734366L;
	private String principal;
    private String tasa;
    private String anios;
    
	public String getPrincipal() {
		return principal;
	}
	public void setPrincipal(String principal) {
		this.principal = principal;
	}
	public String getTasa() {
		return tasa;
	}
	public void setTasa(String tasa) {
		this.tasa = tasa;
	}
	public String getAnios() {
		return anios;
	}
	public void setAnios(String anios) {
		this.anios = anios;
	}
    
    // Incluye el método reset() si es necesario
	@Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        
        ActionErrors errors = new ActionErrors();
        
        // 1. Validar campo 'principal'
        if (this.principal == null || this.principal.trim().isEmpty()) {
            errors.add("principal", new ActionMessage("error.principal.required"));
        } else if (!esDoubleValido(this.principal)) {
            errors.add("principal", new ActionMessage("error.principal.invalido"));
        }

        // 2. Validar campo 'tasa'
        if (this.tasa == null || this.tasa.trim().isEmpty()) {
            errors.add("tasa", new ActionMessage("error.tasa.required"));
        } else if (!esDoubleValido(this.tasa)) {
            errors.add("tasa", new ActionMessage("error.tasa.invalido"));
        }
        
        // 3. Validar campo 'anios'
        if (this.anios == null || this.anios.trim().isEmpty()) {
            errors.add("anios", new ActionMessage("error.anios.required"));
        } else if (!esEnteroValido(this.anios)) {
            errors.add("anios", new ActionMessage("error.anios.invalido"));
        }

        return errors; // Si la lista está vacía, la validación pasa.
    }
    
    // --- Métodos Auxiliares de Validación ---

    private boolean esDoubleValido(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean esEnteroValido(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    @Override
    public void reset(ActionMapping mapping, HttpServletRequest request) {
    	this.principal = null;
    	this.tasa = null;
    	this.anios = null;
    }
}