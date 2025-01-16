package br.ifrn.edu.jeferson.ecommerce.domain.dtos;
import br.ifrn.edu.jeferson.ecommerce.domain.enums.StatusPedido;
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
public class PedidoRequestDTO {

    @Schema(description = "ID do Cliente associado ao pedido", example = "1", required = true)
    private Long clienteId;



    @Schema(description = "Status do Pedido", example = "PENDENTE", required = true)
    private StatusPedido statusPedido;

    @Schema(description = "Valor total do pedido", example = "100.50", required = true)
    private BigDecimal valorTotal;
}
