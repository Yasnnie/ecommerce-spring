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
@Schema(description = "DTO para requisição de endeço")
public class EnderecoRequestDTO {

    @NotBlank(message = "Rua é obrigatório")
    private String rua;

    @NotBlank(message = "Número é obrigatório")
    private String numero;

    @NotBlank(message = "Cidade é obrigatória")
    private String cidade;

    @NotBlank(message = "Estado é obrigatório")
    private String estado;

    @Schema(example = "59900-000")
    @NotBlank(message = "CEP é obrigatório")
    private String cep;

    @NotBlank(message = "Bairro é obrigatório")
    private String bairro;
}
