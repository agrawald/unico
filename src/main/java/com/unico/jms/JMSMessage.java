package com.unico.jms;

import javax.jms.Message;
import java.io.Serializable;

/**
 * Created by krsna on 20/04/15.
 */
public class JMSMessage implements Serializable {
    private Integer i1;
    private Integer i2;

    public JMSMessage(Integer i1, Integer i2) {
        this.i1 = i1;
        this.i2 = i2;
    }

    public Integer getI1() {
        return i1;
    }

    public Integer getI2() {
        return i2;
    }

    @Override
    public String toString() {
        return "JMSMessage{" +
                "i1=" + i1 +
                ", i2=" + i2 +
                '}';
    }
}
