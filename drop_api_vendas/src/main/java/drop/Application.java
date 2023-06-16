package drop;

import drop.model.entities.Cliente;
import drop.model.repository.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@SpringBootApplication
@RestController
public class Application {



    @Bean

    public CommandLineRunner init(@Autowired ClientesRepository repository){
        return args -> {
            repository.salvar(new Cliente("Marcelo","marcelo@email.com","Rua Jõao Tavares n° 810"));
            repository.salvar(new Cliente("Pedro","pedrão@email.com","Rua santos druomn n° 64"));
            repository.salvar(new Cliente("Maria","mariasinha@email.com","Rua Osca freire n° 978"));
            repository.salvar(new Cliente("Alex","alexxx@email.com","Rua Olegario maciel  n° 1245"));
            repository.salvar(new Cliente("Bob","bob@email.com","Rua sem saida n° 23"));
            List<Cliente> returnAll = repository.getAll();
            returnAll.forEach(System.out::println);
        };
    }





    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
