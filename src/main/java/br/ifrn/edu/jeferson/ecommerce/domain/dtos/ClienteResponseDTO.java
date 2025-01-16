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
@Schema(description = "DTO para resposta de Cliente")
public class ClienteResponseDTO {

    @Schema(description = "ID do Cliente", example = "1")
    private Long id;

    @Schema(description = "Nome do cliente", example = "José")
    private String nome;

    @Schema(description = "Email do cliente", example = "email@exemplo.com")
    private String email;

    @Schema(description = "CPF do cliente", example = "000.000.000-00")
    private String cpf;

    @Schema(description = "Telefone do cliente", example = "(84) 99940-9669")
    private String telefone;

}
