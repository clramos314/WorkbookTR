package com.cleon.workbook.web.actions;

import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.cleon.workbook.negocio.CalculadoraFinancieraLocal;
import com.cleon.workbook.persistencia.ResultadoCalculo;
import com.cleon.workbook.web.forms.CalculadoraForm;

public class CalculadoraAction extends Action {
	
	// Instancia del Logger para el MDB
    private static final Logger LOGGER = Logger.getLogger(CalculadoraAction.class.getName());

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, 
                                 HttpServletRequest request, HttpServletResponse response) 
                                 throws Exception {
    	
    	LOGGER.info("Ejecutando CalculadoraAction...");

        CalculadoraForm calcForm = (CalculadoraForm) form;
        Context ctx = null;
        
        try {
            // 1. Obtener la referencia al EJB usando JNDI (Necesario en Struts 1.x)
            ctx = new InitialContext();
            
            // JNDI global name for an EJB in JBoss EAP
            String jndiName = "java:global/Workbook_Web-1.0-SNAPSHOT/CalculadoraFinancieraBean!com.cleon.workbook.negocio.CalculadoraFinancieraLocal";
            
            CalculadoraFinancieraLocal calculadoraEJB = (CalculadoraFinancieraLocal) ctx.lookup(jndiName);

         // LLAMADA AL NUEVO MÉTODO
            ResultadoCalculo resultadoGuardado = calculadoraEJB.calcularYGuardarInteres(
                Double.parseDouble(calcForm.getPrincipal()), 
                Double.parseDouble(calcForm.getTasa()), 
                Integer.parseInt(calcForm.getAnios())
            );

            // Almacenar el resultado y el ID para la vista
            request.setAttribute("interesCalculado", resultadoGuardado.getInteresCalculado());
            request.setAttribute("resultadoId", resultadoGuardado.getId()); // Añadir el ID

            // Redirigir a la vista de éxito
            return mapping.findForward("success");
            
        } catch (Exception e) {
            e.printStackTrace();
            return mapping.findForward("failure"); 
        } finally {
            if (ctx != null) {
                ctx.close();
            }
        }
    }
    
}