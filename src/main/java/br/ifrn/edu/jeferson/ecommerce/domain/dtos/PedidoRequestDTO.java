package br.ifrn.edu.jeferson.ecommerce.domain.dtos;
import br.ifrn.edu.jeferson.ecommerce.domain.enums.StatusPedido;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO para request do Pedido")
public class PedidoRequestDTO {

    @Schema(description = "Data e hora do pedido", example = "2025-01-15T14:30:00")
    private LocalDateTime dataPedido;

    @Schema(description = "Id do cliente associado", example = "1")
    private Long clienteId;

    @Schema(description = "Lista de itens do pedido")
    private List<ItemPedidoRequestDTO> itens;
}
