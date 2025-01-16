package br.ifrn.edu.jeferson.ecommerce.mapper;


import br.ifrn.edu.jeferson.ecommerce.domain.Endereco;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.*;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {
    EnderecoResponseDTO toDTO(Endereco endereco);

    Endereco toEntity(EnderecoRequestDTO enderecoRequestDTO);
}
