package br.ifrn.edu.jeferson.ecommerce.domain.dtos;
import java.math.BigDecimal;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO para request do Pedido")

public class ItemPedidoRequestDTO {
    @Schema(description = "Quantidade do produto no pedido", example = "2")
    private Integer quantidade;

    @Schema(description = "ID do Produto associado", example = "10")
    private Long produtoId;

}
