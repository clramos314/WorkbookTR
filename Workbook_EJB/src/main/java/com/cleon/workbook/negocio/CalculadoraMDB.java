package com.cleon.workbook.negocio;

import com.cleon.workbook.persistencia.ResultadoCalculo;

import java.util.logging.Logger; // Usamos el logger estándar

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

// El MDB se enlaza a la cola (Queue) que creamos
@MessageDriven(name = "CalculadoraMDB", 
activationConfig = {
    // Enlaza la cola al nombre JNDI
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:jboss/jms/queue/CalculosQueue"),
    
    // ¡CAMBIO CLAVE: Enlazar al adaptador de recursos JMS de JBoss!
    @ActivationConfigProperty(propertyName = "resourceAdapter", propertyValue = "activemq-ra") 
    
    // Es posible que también necesites la propiedad de fábrica de conexión:
    // @ActivationConfigProperty(propertyName = "connectionFactoryLookup", propertyValue = "java:/JmsXA")
}
)
public class CalculadoraMDB implements MessageListener {
	
	// Instancia del Logger para el MDB
    private static final Logger LOGGER = Logger.getLogger(CalculadoraMDB.class.getName());

    @PersistenceContext(unitName = "OraclePU")
    private EntityManager em;

    @Override
    public void onMessage(Message message) {
    	// Mensaje recibido de la cola
        LOGGER.info("MDB Recibido: Iniciando procesamiento asíncrono de un nuevo cálculo.");
        
        try {
            // 1. Verificar y Castear el mensaje
            if (message instanceof MapMessage) {
                MapMessage mapMessage = (MapMessage) message;

                // 2. Extraer los parámetros
                double principal = mapMessage.getDouble("principal");
                double tasa = mapMessage.getDouble("tasa");
                int anios = mapMessage.getInt("anios");
                
                // Parámetros extraídos
                LOGGER.info(String.format("MDB Datos: P=%f, T=%f, A=%d.", principal, tasa, anios));

                // 3. Realizar la lógica de negocio y persistencia
                
                // (Nota: la lógica del cálculo debe estar aquí o ser delegada a un Session Bean inyectado)
                double interesCalculado = principal * tasa * anios; 

                ResultadoCalculo resultado = new ResultadoCalculo(principal, tasa, anios, interesCalculado);
                
                // El MDB se ejecuta en su propia transacción JTA, em.persist() funciona aquí.
                em.persist(resultado);

                // Éxito y Persistencia
                LOGGER.info("✅ ÉXITO: Cálculo completado y persistido. Interés: " + interesCalculado);
            }
        } catch (Exception e) {
        	// Error en Persistencia
            LOGGER.severe("❌ ERROR JPA: Falló la persistencia en el MDB. Causa: " + e.getMessage());
            // Se recomienda lanzar la RuntimeException para que la transacción JTA se deshaga.
            throw new RuntimeException("Fallo en la persistencia del MDB", e);
        }
    }
}