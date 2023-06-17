package marcelooo616;



import marcelooo616.domain.entity.Cliente;
import marcelooo616.domain.entity.Pedido;
import marcelooo616.domain.repository.Clientes;
import marcelooo616.domain.repository.Pedidos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
@RestController
public class VendasApplication {

    @Bean
    public CommandLineRunner init(
            @Autowired Clientes clientes,
            @Autowired Pedidos pedidos
    ){

        return  args -> {
            System.out.println("Salvando clientes!");
            Cliente cliente = new Cliente("Marcelo");
            clientes.save(cliente);

            Pedido p = new Pedido();
            p.setCliente(cliente);
            p.setDataPedito(LocalDate.now());
            p.setTotal(BigDecimal.valueOf(100));
            pedidos.save(p);



            Cliente result = clientes.findClienteFetchPedidos(cliente.getId());
            System.out.println(result);
            System.out.println(result.getPedidos());














        };
    }




    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }


}
