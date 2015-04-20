package com.unico.jndi;

import com.unico.common.Constants;
import com.unico.common.Props;
import org.jboss.logging.Logger;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.naming.NamingException;

/**
 * Created by krsna on 20/04/15.
 */
public class JNDILookup {
    private static final Logger log = Logger.getLogger(JNDILookup.class.getName());
    public static final ConnectionFactory CONNECTION_FACTORY = connectionFactory();
    public static final Destination DESTINATION = destination();

    private JNDILookup(){

    }

    private static ConnectionFactory connectionFactory() {
        // Perform the JNDI lookups
        String connectionFactoryString = System.getProperty("connection.factory", Props.getValue(Constants.DEFAULT_CONNECTION_FACTORY));
        log.info("Attempting to acquire connection factory \"" + connectionFactoryString + "\"");
        try {
            return (ConnectionFactory) JNDIContext.INSTANCE.lookup(connectionFactoryString);
        } catch (NamingException e) {
            log.error("Unable to get the connection factory", e);
            return null;
        }
    }

    public static Destination destination() {
        String destinationString = System.getProperty("destination", Props.getValue(Constants.DEFAULT_DESTINATION));
        log.info("Attempting to acquire destination \"" + destinationString + "\"");
        try {
            return (Destination) JNDIContext.INSTANCE.lookup(destinationString);
        } catch (NamingException e) {
            log.error("Unable to get the destination", e);
            return null;
        }
    }
}
