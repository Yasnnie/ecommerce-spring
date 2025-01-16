package br.ifrn.edu.jeferson.ecommerce.mapper;


import br.ifrn.edu.jeferson.ecommerce.domain.Cliente;
import br.ifrn.edu.jeferson.ecommerce.domain.Endereco;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.*;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {
    EnderecoResponseDTO toDTO(Endereco endereco);

    Endereco toEntity(EnderecoRequestDTO enderecoRequestDTO);
    void updateEntityFromDTO(EnderecoRequestDTO dto, @MappingTarget Endereco endereco);
}
