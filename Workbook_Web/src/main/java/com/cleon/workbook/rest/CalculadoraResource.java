package com.cleon.workbook.rest;

import com.cleon.workbook.negocio.CalculadoraFinancieraLocal;
import com.cleon.workbook.persistencia.ResultadoCalculo;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/calculos") 
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CalculadoraResource {

    @EJB
    private CalculadoraFinancieraLocal calculadoraEJB;

    @POST
    public Response iniciarCalculo(CalculoRequest request) { 
        
        ResultadoCalculo resultadoProvisional = calculadoraEJB.calcularYGuardarInteres(
            request.getPrincipal(), 
            request.getTasa(), 
            request.getAnios()
        );

        return Response.status(Response.Status.ACCEPTED) // Código 202: Aceptado (asíncrono)
                       .entity("{\"status\": \"Proceso JMS iniciado. ID provisional: " 
                               + resultadoProvisional.getPrincipal() + "\"}")
                       .build();
    }
}