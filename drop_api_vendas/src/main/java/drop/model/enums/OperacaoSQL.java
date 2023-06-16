package drop.model.enums;

public enum OperacaoSQL {

    INSERT("insert into cliente (nome, email, endereco) values (?,?,?) "),
    SELECT_ALL("SELECT * FROM CLIENTE"),
    UPDATE("update cliente set nome = ? where id = ?"),
    DELETE("delete from cliente where id = ?");

    private final String sql;

    OperacaoSQL(String sql) {
        this.sql = sql;
    }

    public String getSql() {
        return sql;
    }
}
