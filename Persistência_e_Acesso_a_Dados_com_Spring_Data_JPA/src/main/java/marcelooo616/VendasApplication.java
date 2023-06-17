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
            System.out.println("Salvando clientes!");
            clientes.save(new Cliente("Marcelo"));
            clientes.save(new Cliente("Pedro"));

            List<Cliente> getAll = clientes.findAll();
            getAll.forEach(System.out::println);

            System.out.println("Atualizando clientes!");
            getAll.forEach(c ->{
                c.setNome(c.getNome() + "atui");
                clientes.save(c);
            });

            System.out.println("buscando  clientes!");
            clientes.findByNomeLike("Mar").forEach(System.out::println);

            System.out.println("deletando  clientes!");
            clientes.findAll().forEach(c -> {
                clientes.delete(c);
            });

            getAll = clientes.findAll();
            if (getAll.isEmpty()){
                System.out.println("Nenhum ckliente encontrado");
            }else{
                getAll.forEach(System.out::println);
            }










        };
    }




    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }


}
