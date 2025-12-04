package com.cleon.workbook.rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

// IMPORTANTE: Definimos un prefijo base que NO ES "/" y NO es "*.do"
@ApplicationPath("/api") 
public class RestApplication extends Application {
    // Activación de JAX-RS
}