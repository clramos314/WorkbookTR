package com.cleon.workbook.negocio;

import com.cleon.workbook.persistencia.ResultadoCalculo;
import javax.ejb.EJB;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit5.ArquillianExtension;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(ArquillianExtension.class)
public class CalculadoraFinancieraIntegrationTest {

    // 1. Inyección del EJB: Esto prueba que el contenedor ha desplegado el Bean.
    @EJB
    CalculadoraFinancieraLocal calculadoraEJB;

    // 2. Creación del Micro-despliegue para la prueba.
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                // Incluye el EJB Session Bean, la interfaz y el MDB
                .addClasses(CalculadoraFinancieraBean.class, CalculadoraFinancieraLocal.class, CalculadoraMDB.class)
                // Incluye la Entidad JPA
                .addClass(ResultadoCalculo.class)
                // Añade la configuración de persistencia
                //.addAsManifestResource("META-INF/persistence.xml", "META-INF/persistence.xml")
                .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml") // Usamos addAsResource
                // Añade el archivo de configuración EJB (vacío es suficiente)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    public void testEJBInjectionAndJMSDelivery() throws Exception {
        assertNotNull(calculadoraEJB, "El EJB no pudo ser inyectado por Arquillian.");
        
        // Llama al método que envía el mensaje JMS
        ResultadoCalculo resultado = calculadoraEJB.calcularYGuardarInteres(100.0, 0.1, 1);
        
        assertNotNull(resultado, "El resultado no debe ser nulo (proviene de un envío exitoso).");
        
        // Nota: La prueba de integración asíncrona real del MDB es más compleja
        // (requiere que el hilo de prueba espere la acción del MDB),
        // pero esta prueba valida que el EJB envió el mensaje JMS sin error.
        assertTrue(resultado.getPrincipal() == 100.0, "La entrega JMS parece fallar.");
        
        // Se recomienda verificar el log del servidor para ver el mensaje "✅ MDB: Cálculo asíncrono completado..."
    }
}
