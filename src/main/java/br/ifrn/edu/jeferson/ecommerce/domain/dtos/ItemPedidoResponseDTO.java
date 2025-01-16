package br.ifrn.edu.jeferson.ecommerce.domain.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO para request do Pedido")
public class ItemPedidoResponseDTO {

    @Schema(description = "ID do Item do Pedido", example = "1")
    private Long id;

    @Schema(description = "Quantidade do produto no pedido", example = "2")
    private Integer quantidade;

    @Schema(description = "Valor unitário do produto", example = "50.00")
    private BigDecimal valorUnitario;

    @Schema(description = "Produto associado")
    private ProdutoResponseDTO produto;
}
