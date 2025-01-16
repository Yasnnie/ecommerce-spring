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
@Schema(description = "DTO para request do Produto")
public class ProdutoResquestDTO {

    @Schema(description = "Nome do produto", example = "Televisão", required = true)
    private String nome;
    @Schema(description = "Descrição do produto", example = "50' polegadas 4k", required = true)
    private String descricao;
    @Schema(description = "Preço do produto", example = "2000.00", required = true)
    private BigDecimal preco;
    @Schema(description = "Quantidade em estoque do produto", example = "5", required = true)
    private Integer estoque;
}
