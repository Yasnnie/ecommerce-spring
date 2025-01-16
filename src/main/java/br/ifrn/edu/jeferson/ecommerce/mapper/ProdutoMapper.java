package br.ifrn.edu.jeferson.ecommerce.mapper;


import br.ifrn.edu.jeferson.ecommerce.domain.Produto;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ProdutoResponseDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ProdutoResquestDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {
    Produto toEntity(ProdutoResquestDTO dto);
    ProdutoResponseDTO toDto(Produto entity);
}
