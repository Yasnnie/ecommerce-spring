package br.ifrn.edu.jeferson.ecommerce.mapper;

import br.ifrn.edu.jeferson.ecommerce.domain.Pedido;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.PedidoRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.PedidoResponseDTO;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface PedidoMapper {
    PedidoResponseDTO toDTO(Pedido pedido);

    @Mapping(target = "itens", ignore = true)
    Pedido toEntity(PedidoRequestDTO pedidoRequestDTO);
    void updateEntityFromDTO(PedidoRequestDTO dto, @MappingTarget Pedido pedido);
}
