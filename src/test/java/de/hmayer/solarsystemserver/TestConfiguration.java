package de.hmayer.solarsystemserver;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"de.hmayer.solarsystemserver.model", 
"de.hmayer.solarsystemserver.model.dao",
"de.hmayer.solarsystemserver.controller"})
public class TestConfiguration {
    
}
