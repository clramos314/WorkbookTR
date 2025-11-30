package com.cleon.workbook.web;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB; // Importante: el decorador para inyección
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cleon.workbook.negocio.CalculadoraFinancieraLocal; // La interfaz de negocio

@WebServlet("/pruebaEJB_MDB")
public class PruebaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // *** Inyección de Dependencias EJB ***
    // El contenedor Java EE se encarga de crear e inyectar una instancia del EJB
    @EJB
    private CalculadoraFinancieraLocal calculadora; // Accede al EJB via su interfaz local

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
                         throws ServletException, IOException {
        
        // 1. Uso de los parámetros.
        double principal = 1000.0;
        double tasa = 0.05; // 5%
        int anios = 3;

        // 2. Llamada al método de negocio del EJB.
        double interesCalculado = calculadora.calcularInteres(principal, tasa, anios);

        // 3. Devolver la respuesta al cliente.
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        out.println("<html><head><title>Prueba EJB 3.1</title></head><body>");
        out.println("<h1>Resultado del EJB (Session Bean)</h1>");
        out.println("<p>Principal: " + principal + "</p>");
        out.println("<p>Tasa de interés: " + (tasa * 100) + "%</p>");
        out.println("<p>Años: " + anios + "</p>");
        out.println("<h2>Interés Simple Calculado: " + interesCalculado + "</h2>");
        out.println("</body></html>");
    }
}