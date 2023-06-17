package drop.model.enums;

public enum Categoria {

    ELETRONICO("Eletronicos"),
    ESCOLARES("Escolares"),
    VESTUARIO("Vestuario");

    private String categoria;

    Categoria(String categoria) {
        this.categoria = categoria;
    }

    public String getCategoria() {
        return categoria;
    }
}
