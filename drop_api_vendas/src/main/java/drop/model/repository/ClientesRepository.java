package drop.model.repository;




import drop.model.entities.Cliente;
import drop.model.enums.OperacaoSQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ClientesRepository {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Cliente salvar(Cliente cliente){
        jdbcTemplate.update(
                OperacaoSQL.INSERT.getSql(),
                new Object[]{
                        cliente.getNome(),
                        cliente.getEmail(),
                        cliente.getEndereco()});
        return cliente;
    }

    public List<Cliente> getAll(){
        return jdbcTemplate.query(OperacaoSQL.SELECT_ALL.getSql(), getRowMapper());
    }

    private RowMapper<Cliente> getRowMapper() {
        return new RowMapper<Cliente>() {
            @Override
            public Cliente mapRow(ResultSet resultSet, int i) throws SQLException {
                Integer id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                String email = resultSet.getString("email");
                String endereco = resultSet.getString("endereco");
                return new Cliente(id,nome,email,endereco);
            }
        };
    }

    public List<Cliente> getByName(String nome){
        return jdbcTemplate.query(
                OperacaoSQL.SELECT_ALL.getSql(),
                new Object[]{"%" + nome + "%"},
                getRowMapper()
        );
    }


}
