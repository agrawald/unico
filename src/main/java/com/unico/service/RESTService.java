package com.unico.service;

import com.unico.jms.JMSConnection;
import com.unico.jms.JMSMessage;
import com.unico.jms.JMSProducer;
import org.jboss.logging.Logger;

import javax.jms.JMSException;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by krsna on 20/04/15.
 */
@Path("/msg")
public class RESTService {
    private static final Logger log = Logger.getLogger(RESTService.class);
    private JMSConnection jmsConnection;
    private JMSProducer jmsProducer;
    private JMSMessage jmsMessage;
    private static List<JMSMessage> jmsMessages = new ArrayList<>();

    @PUT
    @Path("/push/{i1}/{i2}")
    public String push(@PathParam("i1") int i1, @PathParam("i2") int i2) throws JMSException {
        try {
            log.info("Pushing " + i1 + ", " + i2);
            jmsConnection = new JMSConnection();
            //which returns the status of the request to the caller as a String. The two parameters will be added to a JMS queue.
            jmsMessage = new JMSMessage(i1, i2);
            jmsProducer = new JMSProducer(jmsConnection);
            jmsProducer.send(jmsMessage);
            jmsMessages.add(jmsMessage);
            return "pushed";
        } finally {
            if(jmsConnection!=null) jmsConnection.close();
        }
    }

    @GET
    @Path("/list")
    public List<Integer> list() {
        log.info("Listing ");
        List<Integer> messages = new ArrayList<>();
        //which returns a list of all the elements ever added to the queue in the order added as a JSON structure.
        for(JMSMessage jmsMessage: jmsMessages){
            messages.add(jmsMessage.getI1());
            messages.add(jmsMessage.getI2());
        }
        return messages;
    }
}

