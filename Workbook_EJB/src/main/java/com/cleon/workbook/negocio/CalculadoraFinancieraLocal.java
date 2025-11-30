package com.cleon.workbook.negocio;

import javax.ejb.Local;

import com.cleon.workbook.persistencia.ResultadoCalculo;

@Local
public interface CalculadoraFinancieraLocal {
    double calcularInteres(double principal, double tasa, int anios);
    
    ResultadoCalculo calcularYGuardarInteres(double principal, double tasa, int anios);
}