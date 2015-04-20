package com.unico.jndi;

import com.unico.common.Constants;
import com.unico.common.Props;
import org.jboss.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

/**
 * Created by krsna on 20/04/15.
 */
public final class JNDIContext {
    private static final Logger log = Logger.getLogger(JNDIContext.class);

    // Set up the context for the JNDI lookup
    private static final Properties env = new Properties();
    public static final InitialContext INSTANCE = getInstance();

    private JNDIContext(){

    }

    private static InitialContext getInstance(){
        try {
            log.info("Init the context");
            env.put(Context.INITIAL_CONTEXT_FACTORY, Props.getValue(Constants.INITIAL_CONTEXT_FACTORY));
            env.put(Context.PROVIDER_URL, System.getProperty(Context.PROVIDER_URL, Props.getValue(Constants.PROVIDER_URL)));
            env.put(Context.SECURITY_PRINCIPAL, System.getProperty("username", Props.getValue(Constants.DEFAULT_USERNAME)));
            env.put(Context.SECURITY_CREDENTIALS, System.getProperty("password", Props.getValue(Constants.DEFAULT_PASSWORD)));
            return new InitialContext(env);
        } catch (NamingException e) {
            log.error("Unable to initialize the context", e);
            return null;
        }
    }
}
