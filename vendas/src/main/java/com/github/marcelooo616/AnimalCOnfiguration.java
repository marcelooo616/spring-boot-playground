package com.github.marcelooo616;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AnimalCOnfiguration {

    @Bean(name = "cachorro")
    public Animal cachorro(){
        return new Animal(){
            @Override
            public void fazerBarulho(){
                System.out.println("Au  au aiu");
            }
        };
    }

    @Bean(name = "gato")
    public Animal gato(){
        return new Animal(){
            @Override
            public void fazerBarulho(){
                System.out.println("miaaaaau");
            }
        };
    }
}
