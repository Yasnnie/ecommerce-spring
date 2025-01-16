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
@Schema(description = "DTO para requisição de Cliente")
public class ClienteRequestDTO {

    @Schema(description = "Nome do cliente", example = "José")
    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @Schema(description = "Email do cliente", example = "email@exemplo.com")
    @NotBlank(message = "Email é obrigatório")
    private String email;

    @Schema(description = "CPF do cliente", example = "000.000.000-00")
    @NotBlank(message = "CPF é obrigatório")
    private String cpf;

    @Schema(description = "Telefone do cliente", example = "(84) 99940-9669")
    private String telefone;

}
