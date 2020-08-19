package com.mortgages;

import com.mortgages.dslayer.engine.EngineFactory;
import com.mortgages.dslayer.engine.ProcessEngine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Created by rameshkale on 08/08/20.
 */
@SpringBootApplication
public class BootApplication {

   /*public static void main1(String[] args) {
        ProcessEngine processEngine = EngineFactory.getBasicEngine();
        Application app = new Application("M1",1);
        Application app1 = new Application("M1",2);
        Application app2 = new Application("M2",1);


        processEngine.insertApplication(app);
        processEngine.insertApplication(app1);
        processEngine.insertApplication(app2);

        processEngine.displayStore();
    }*/

    @Bean
    public ProcessEngine getProcessEngine(){
        return EngineFactory.getBasicEngine();
    }

    public static void main(String[] args) {
        SpringApplication.run( BootApplication.class, args );
    }
}
