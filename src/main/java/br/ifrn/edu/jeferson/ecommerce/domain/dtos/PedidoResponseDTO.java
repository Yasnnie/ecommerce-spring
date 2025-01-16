package br.ifrn.edu.jeferson.ecommerce.domain.dtos;

import br.ifrn.edu.jeferson.ecommerce.domain.enums.StatusPedido;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO para request do Pedido")
public class PedidoResponseDTO {

    @Schema(description = "ID do Pedido", example = "1")
    private Long id;

    @Schema(description = "Data e hora do pedido", example = "2025-01-15T14:30:00")
    private LocalDateTime dataPedido;

    @Schema(description = "Valor total do pedido", example = "100.50")
    private BigDecimal valorTotal;

    @Schema(description = "Status do pedido", example = "PENDENTE")
    private StatusPedido statusPedido;

    @Schema(description = "Lista de itens do pedido")
    private List<ItemPedidoResponseDTO> itens;
}
