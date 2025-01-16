package br.ifrn.edu.jeferson.ecommerce.mapper;

import br.ifrn.edu.jeferson.ecommerce.domain.ItemPedido;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ItemPedidoRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ItemPedidoResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ItemPedidoMapper {

    ItemPedidoResponseDTO toDTO(ItemPedido itemPedido);

    ItemPedido toEntity(ItemPedidoRequestDTO itemPedidoRequestDTO);
    void updateEntityFromDTO(ItemPedidoRequestDTO dto, @MappingTarget ItemPedido itemPedido);
}
