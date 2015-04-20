package com.unico.service;

import com.unico.jms.JMSConnection;
import com.unico.jms.JMSConsumer;
import com.unico.jms.JMSMessage;
import org.jboss.logging.Logger;

import javax.jms.JMSException;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by krsna on 20/04/15.
 */
@WebService(name = "SoapWSService", targetNamespace = "http://ws.unico.org/SoapWSService")
public class SoapWSService {
    private static final Logger log = Logger.getLogger(SoapWSService.class);
    private JMSConnection jmsConnection;
    private JMSConsumer jmsConsumer;
    private static List<Integer> allGcds = new ArrayList<>();

    @WebMethod(action = "http://ws.unico.org/SoapWSService/gcd")
    @WebResult(name = "gcdResponse", partName = "gcdResponse")
    public int gcd() throws JMSException {
        log.info("gcd is called");
        try {
            jmsConnection = new JMSConnection();
            jmsConsumer = new JMSConsumer(jmsConnection);
            JMSMessage jmsMessage = jmsConsumer.recieve();
            BigInteger i1 = BigInteger.valueOf(jmsMessage.getI1());
            BigInteger i2 = BigInteger.valueOf(jmsMessage.getI2());
            BigInteger gcd = i1.gcd(i2);
            allGcds.add(gcd.intValue());
            return gcd.intValue();
        } finally {
            if(jmsConnection!=null) jmsConnection.close();
        }

    }

    @WebMethod(action = "http://ws.unico.org/SoapWSService/gcdList")
    @WebResult(name = "gcdListResponse", partName = "gcdListResponse")
    public List<Integer> gcdList() {
        log.info("gcdList is called");
        return allGcds;
    }

    @WebMethod(action = "http://ws.unico.org/SoapWSService/gcdSum")
    @WebResult(name = "gcdSumResponse", partName = "gcdSumResponse")
    public int gcdSum(){
        log.info("gcdSum is called");
        int sum = 0;
        for(Integer gcd: allGcds){
            sum += gcd;
        }

        return sum;
    }
}
