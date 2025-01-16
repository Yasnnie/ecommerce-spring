package br.ifrn.edu.jeferson.ecommerce.domain.dtos;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoEstoqueResquestDTO {
    @Schema(description = "Quantidade em estoque do produto", example = "5")
    private Integer estoque;
}
