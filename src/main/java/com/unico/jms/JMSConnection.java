package com.unico.jms;

import com.unico.common.Constants;
import com.unico.common.Props;
import com.unico.jndi.JNDILookup;
import org.jboss.logging.Logger;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Session;

/**
 * Created by krsna on 20/04/15.
 */
public class JMSConnection {
    private static final Logger log = Logger.getLogger(JMSConnection.class);
    private Connection connection;
    private Session session;

    public JMSConnection() throws JMSException {
        log.info("Creating a connection");
        connection = JNDILookup.CONNECTION_FACTORY.createConnection(System.getProperty("username", Props.getValue(Constants.DEFAULT_USERNAME))
                , System.getProperty("password", Props.getValue(Constants.DEFAULT_PASSWORD)));
        log.info("Creating a session");
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    }

    public Session getSession() throws JMSException {
        return session;
    }

    public void close() throws JMSException {
        log.info("Closing the connection");
        if(connection!=null)
            connection.close();
    }

    public void start() throws JMSException {
        log.info("Starting the connection");
        if(connection!=null)
            connection.start();
    }
}
