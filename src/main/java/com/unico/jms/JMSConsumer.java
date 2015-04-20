package com.unico.jms;

import com.unico.jndi.JNDILookup;
import org.jboss.logging.Logger;

import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.ObjectMessage;
import javax.xml.soap.SOAPFault;
import javax.xml.ws.soap.SOAPFaultException;

/**
 * Created by krsna on 20/04/15.
 */
public class JMSConsumer {
    private static final Logger log = Logger.getLogger(JMSConsumer.class);
    private MessageConsumer messageConsumer;

    public JMSConsumer(JMSConnection jmsConnection) throws JMSException {
        log.info("Inti message consumer");
        messageConsumer = jmsConnection.getSession().createConsumer(JNDILookup.DESTINATION);
        jmsConnection.start();
    }

    public JMSMessage recieve() throws JMSException {
        log.info("Reading the message");
        ObjectMessage objectMessage = (ObjectMessage) messageConsumer.receive(5000);
        if(objectMessage!=null) {
            log.info("Got the message: " + objectMessage.getObject());
            return (JMSMessage) objectMessage.getObject();
        }
        return null;
    }
}
