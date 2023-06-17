package drop;

import drop.model.entities.Cliente;
import drop.model.entities.Produto;
import drop.model.enums.Categoria;
import drop.model.repository.ClientesRepository;
import drop.model.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;


@SpringBootApplication
@RestController
public class Application {



    @Bean

    public CommandLineRunner init(
            @Autowired ClientesRepository clientesRepository,
            @Autowired ProdutoRepository produtoRepository

    ){
        return args -> {
            clientesRepository.save(new Cliente("Marcelo","marcelo@email.com","Jõao Tavares n° 810"));
            clientesRepository.save(new Cliente("Pedro","pedrão@email.com","Rua santos druomn n° 64"));
            clientesRepository.save(new Cliente("Maria","mariasinha@email.com","Rua Osca freire n° 978"));
            clientesRepository.save(new Cliente("Alex","alexxx@email.com","Rua Olegario maciel  n° 1245"));
            clientesRepository.save(new Cliente("Bob","bob@email.com","Rua Jõao Tavares n° 810"));


            produtoRepository.save(new Produto("Caneta", new BigDecimal(1.99), "https://i.imgur.com/kEhVvAi.jpg",Categoria.ESCOLARES.getCategoria(),25));
            produtoRepository.save(new Produto("Caderno", new BigDecimal(12.50), "https://i.imgur.com/kEhVvAi.jpg",Categoria.ESCOLARES.getCategoria(), 25));
            produtoRepository.save(new Produto("Copo", new BigDecimal(7.45), "https://i.imgur.com/kEhVvAi.jpg",Categoria.VESTUARIO.getCategoria(), 25));
            produtoRepository.save(new Produto("Placa de video", new BigDecimal(4000.99), "https://i.imgur.com/kEhVvAi.jpg",Categoria.ELETRONICO.getCategoria() ,25));
            produtoRepository.save(new Produto("Camiseta", new BigDecimal(25.99), "https://i.imgur.com/kEhVvAi.jpg",Categoria.VESTUARIO.getCategoria() ,25));
            produtoRepository.save(new Produto("Contrabaixo", new BigDecimal(1888.9), "https://i.imgur.com/kEhVvAi.jpg",Categoria.ELETRONICO.getCategoria(), 25));


            List<Cliente> returnAll = clientesRepository.findAll();
           // returnAll.forEach(System.out::println);

            System.out.println("Buscando pelo nome");
            List<Cliente> getByName = clientesRepository.findByName("Alex");
            getByName.forEach(System.out::println);
            System.out.println("Buscando pelo Id");
            Cliente getById = clientesRepository.findById(1).orElseThrow(() -> new NoSuchElementException("Cliente não encontrado"));
            System.out.println(getById);

        };
    }





    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
