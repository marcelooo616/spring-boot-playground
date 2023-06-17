package drop;

import drop.model.entities.*;
import drop.model.enums.Categoria;
import drop.model.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


@SpringBootApplication
@RestController
public class Application {



    @Bean

    public CommandLineRunner init(
            @Autowired ClientesRepository clientesRepository,
            @Autowired ProdutoRepository produtoRepository,
            @Autowired PedidoRepository pedidoRepository,
            @Autowired ItemRepository itemRepository,
            @Autowired ComentarioRepository comentarioRepository

            ){
        return args -> {
            clientesRepository.save(new Cliente("Marcelo","marcelo@email.com","Jõao Tavares n° 810"));
            clientesRepository.save(new Cliente("Pedro","pedrão@email.com","Rua santos druomn n° 64"));
            clientesRepository.save(new Cliente("Maria","mariasinha@email.com","Rua Osca freire n° 978"));
            clientesRepository.save(new Cliente("Alex","alexxx@email.com","Rua Olegario maciel  n° 1245"));
            clientesRepository.save(new Cliente("Bob","bob@email.com","Rua Jõao Tavares n° 810"));


            //produtoRepository.save(new Produto("Caneta", new BigDecimal(1.99), "https://i.imgur.com/kEhVvAi.jpg",Categoria.ESCOLARES.getCategoria(),25));
           // produtoRepository.save(new Produto("Caderno", new BigDecimal(12.50), "https://i.imgur.com/kEhVvAi.jpg",Categoria.ESCOLARES.getCategoria(), 25));
            //produtoRepository.save(new Produto("Copo", new BigDecimal(7.45), "https://i.imgur.com/kEhVvAi.jpg",Categoria.VESTUARIO.getCategoria(), 25));
            //produtoRepository.save(new Produto("Placa de video", new BigDecimal(4000.99), "https://i.imgur.com/kEhVvAi.jpg",Categoria.ELETRONICO.getCategoria() ,25));
            //produtoRepository.save(new Produto("Camiseta", new BigDecimal(25.99), "https://i.imgur.com/kEhVvAi.jpg",Categoria.VESTUARIO.getCategoria() ,25));
            //produtoRepository.save(new Produto("Contrabaixo", new BigDecimal(1888.9), "https://i.imgur.com/kEhVvAi.jpg",Categoria.ELETRONICO.getCategoria(), 25));

            Cliente cliente = new Cliente("Marcelo","marcelo@email.com","Jõao Tavares n° 810");
            clientesRepository.save(cliente);

            Produto caneta = new Produto("Caneta", new BigDecimal(1.99), "https://i.imgur.com/kEhVvAi.jpg",Categoria.ESCOLARES.getCategoria(),25);
            Produto caderno = new Produto("Caderno", new BigDecimal(12.99), "https://i.imgur.com/kEhVvAi.jpg",Categoria.ESCOLARES.getCategoria(),25);
            Produto lapis = new Produto("Lapis", new BigDecimal(1.99), "https://i.imgur.com/kEhVvAi.jpg",Categoria.ESCOLARES.getCategoria(),25);

            List<Produto> produtos = new ArrayList<>();
            produtos.add(caneta);
            produtos.add(caderno);
            produtos.add(lapis);
            produtoRepository.saveAll(produtos);

            produtos.forEach(System.out::println);
            System.out.println(produtos.get(0));




            Pedido p = new Pedido();
            p.setCliente(cliente);
            p.setDataPedido(LocalDate.now());
            p.setTotal_pedido(BigDecimal.valueOf(100));
            pedidoRepository.save(p);

            System.out.println(p);
            List<Item> itens = new ArrayList<>();

            Item item_cart1 = new Item(produtos.get(0), p, 5);
            Item item_cart2 = new Item(produtos.get(1), p, 2);
            Item item_cart3 = new Item(produtos.get(2), p, 3);



            itens.add(item_cart1);
            itens.add(item_cart2);
            itens.add(item_cart3);

            BigDecimal somaTotal = BigDecimal.ZERO;

            for (Item item : itens) {
                BigDecimal valorUnitario = item.getProduto().getValor_unitario();
                Integer quantidade = item.getQuantidade();

                BigDecimal somaItem = valorUnitario.multiply(BigDecimal.valueOf(quantidade));  // Cálculo da soma para o item atual
                somaTotal = somaTotal.add(somaItem);  // Acumule a soma na variável de soma total
            }




            itemRepository.saveAll(itens);
            itens.forEach(System.out::println);

            p.setCliente(cliente);
            p.setDataPedido(LocalDate.now());
            p.setTotal_pedido(somaTotal);

            pedidoRepository.save(p);



            Comentario comentario = new Comentario();
            comentario.setCliente(cliente);
            comentario.setDescricao("muito bom o produto");
            comentario.setProduto(caderno);
            comentarioRepository.save(comentario);

            System.out.println("Pedido: " +  p);




          //  List<Cliente> returnAll = clientesRepository.findAll();
           // returnAll.forEach(System.out::println);

            /*System.out.println("Buscando pelo nome");
            List<Cliente> getByName = clientesRepository.findByName("Alex");
            getByName.forEach(System.out::println);
            System.out.println("Buscando pelo Id");
            Cliente getById = clientesRepository.findById(1).orElseThrow(() -> new NoSuchElementException("Cliente não encontrado"));
            System.out.println(getById);*/

        };
    }





    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
