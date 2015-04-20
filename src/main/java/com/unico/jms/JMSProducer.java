package com.unico.jms;

import com.unico.jndi.JNDILookup;
import org.jboss.logging.Logger;

import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

/**
 * Created by krsna on 20/04/15.
 */
public class JMSProducer {
    private static final Logger log = Logger.getLogger(JMSProducer.class);

    private MessageProducer messageProducer;
    private JMSConnection jmsConnection;

    public JMSProducer(JMSConnection jmsConnection) throws JMSException {
        log.info("Init the message producer");
        this.jmsConnection = jmsConnection;
        messageProducer = jmsConnection.getSession().createProducer(JNDILookup.DESTINATION);
        this.jmsConnection.start();
    }

    public void send(JMSMessage message) throws JMSException {
        log.info("Sending the message: " + message);
        ObjectMessage objectMessage = this.jmsConnection.getSession().createObjectMessage(message);
        messageProducer.send(objectMessage);
    }
}
