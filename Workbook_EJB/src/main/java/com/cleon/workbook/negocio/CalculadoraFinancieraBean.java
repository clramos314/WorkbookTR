package com.cleon.workbook.negocio;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.cleon.workbook.persistencia.ResultadoCalculo;

// @Stateless indica que es un EJB de Sesión sin estado, ideal para servicios de negocio.
@Stateless
public class CalculadoraFinancieraBean implements CalculadoraFinancieraLocal {
	
	// Instancia del Logger para el MDB
    private static final Logger LOGGER = Logger.getLogger(CalculadoraFinancieraBean.class.getName());
	
	// Inyección del EntityManager. El contenedor gestiona la unidad "EjemploPU".
    @PersistenceContext(unitName = "OraclePU") 
    private EntityManager em; // <-- CAMBIO CLAVE

    // Implementación del método definido en la interfaz.
    @Override
    public double calcularInteres(double principal, double tasa, int anios) {
        // Fórmula simple de interés (I = P * r * t)
        return principal * tasa * anios;
    }
    
    @Override
    public ResultadoCalculo calcularYGuardarInteres(double principal, double tasa, int anios) {
        
        double interesCalculado = calcularInteres(principal, tasa, anios);
        
        // 1. Crear la nueva entidad
        ResultadoCalculo resultado = new ResultadoCalculo(principal, tasa, anios, interesCalculado);
        
        // 2. Persistir (guardar) la entidad. 
        // El contenedor maneja automáticamente la transacción.
        em.persist(resultado); // <-- CAMBIO CLAVE
        
        LOGGER.info("Interés calculado con exito!");
        
        return resultado;
    }
}