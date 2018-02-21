package com.nime.ezdisco.boot;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.nime.ezdisco.rest.Service;

/**
 * This component will enable all of the JAX-RS endpoints in the system
 * 
 * @author tamer
 *
 */

@ApplicationPath("/rest")
@Component
public class JerseryResourceConfig extends ResourceConfig {
	
    public JerseryResourceConfig() {
        registerEndpoints();
    }

    private void registerEndpoints() {   	
    	register(Service.class);
    }

}

