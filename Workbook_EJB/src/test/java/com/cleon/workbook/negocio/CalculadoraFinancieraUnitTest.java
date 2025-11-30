package com.cleon.workbook.negocio;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculadoraFinancieraUnitTest {
    
    // Instancia del Bean sin inyecciones (probando solo la lógica pura)
    private CalculadoraFinancieraBean bean = new CalculadoraFinancieraBean();

    @Test
    public void testCalcularInteresSimple() {
        // I = P * r * t
        double principal = 1000.0;
        double tasa = 0.05; // 5%
        int anios = 2;
        
        // Resultado esperado: 1000 * 0.05 * 2 = 100.0
        double esperado = 100.0;
        
        double resultado = bean.calcularInteres(principal, tasa, anios);
        
        // Usamos una delta pequeña para comparar doubles
        assertEquals(esperado, resultado, 0.001, "El cálculo del interés simple es incorrecto.");
    }
}