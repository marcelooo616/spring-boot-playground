package com.github.marcelooo616.rest.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {

    @NotNull(message = "Informe o codigo do cliente")
    private Integer cliente;

    @NotNull(message = "Campo total do pedido e obrigatorio.")
    private BigDecimal total;
    private List<ItemPedidoDTO> items;




}
