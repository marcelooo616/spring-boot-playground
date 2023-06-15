package marcelooo616;



import marcelooo616.domain.entity.Cliente;
import marcelooo616.domain.repositorio.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class VendasApplication {

    @Bean
    public CommandLineRunner init(@Autowired Clientes clientes){
        return  args -> {
            clientes.salvar(new Cliente("Marcelo"));
            clientes.salvar(new Cliente("Pedro"));
            List<Cliente> getAll = clientes.obterTodos();
            getAll.forEach(System.out::println);

        };
    }




    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }


}
