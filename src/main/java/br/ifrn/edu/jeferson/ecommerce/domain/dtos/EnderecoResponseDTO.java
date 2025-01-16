package br.ifrn.edu.jeferson.ecommerce.domain.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO para resposta de endeço")
public class EnderecoResponseDTO {

    @Schema(description = "ID do endereço", example = "1")
    private Long id;
    private String rua;

    private String numero;

    private String cidade;

    private String estado;

    @Schema(example = "59900-000")
    private String cep;
    private String bairro;
}
