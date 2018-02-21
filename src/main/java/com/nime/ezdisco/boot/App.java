package com.nime.ezdisco.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * Component scan attribute required to make spring look for beans anywhere in the specified package and below
 * 
 * @author tamer
 *
 */
@SpringBootApplication
@ComponentScan(basePackages="com.nime.ezdisco")
@EntityScan("com.nime.ezdisco.data")
public class App {
	
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}
