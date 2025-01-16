package br.ifrn.edu.jeferson.ecommerce.service;

import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ItemPedidoRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.enums.StatusPedido;
import br.ifrn.edu.jeferson.ecommerce.exception.BusinessException;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.PedidoRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.Pedido;
import br.ifrn.edu.jeferson.ecommerce.domain.Produto;
import br.ifrn.edu.jeferson.ecommerce.domain.ItemPedido;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.PedidoResponseDTO;
import br.ifrn.edu.jeferson.ecommerce.mapper.ItemPedidoMapper;
import br.ifrn.edu.jeferson.ecommerce.repository.ItemPedidoRepository;
import br.ifrn.edu.jeferson.ecommerce.repository.PedidoRepository;
import br.ifrn.edu.jeferson.ecommerce.mapper.PedidoMapper;
import br.ifrn.edu.jeferson.ecommerce.repository.ProdutoRepository;
import br.ifrn.edu.jeferson.ecommerce.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
@Service
public class PedidosService {
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private PedidoMapper pedidoMapper;

    @Autowired
    private ItemPedidoMapper itemPedidoMapper;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    public PedidoResponseDTO salvar(PedidoRequestDTO pedidoRequestDTO) {
        validarStatusPedido(pedidoRequestDTO.getStatusPedido());

        Pedido pedido = pedidoMapper.toEntity(pedidoRequestDTO);

        BigDecimal valorTotal = BigDecimal.ZERO;
        for (ItemPedidoRequestDTO newItem : pedidoRequestDTO.getItens()) {
            ItemPedido item = itemPedidoMapper.toEntity(newItem);
            Produto produto = produtoRepository.findById(newItem.getProdutoId())
                    .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado: ID " + item.getProduto().getId()));

            if (produto.getEstoque() < item.getQuantidade()) {
                throw new BusinessException("Estoque insuficiente para o produto: " + produto.getNome());
            }

            produto.setEstoque(produto.getEstoque() - item.getQuantidade());


            BigDecimal subtotal = produto.getPreco().multiply(BigDecimal.valueOf(item.getQuantidade()));
            valorTotal = valorTotal.add(subtotal);

            item.setValorUnitario(produto.getPreco());
            item.setPedido(pedido);
            item.setProduto(produto);
            itemPedidoRepository.save(item);

            pedido.getItens().add(item);
        }

        pedido.setValorTotal(valorTotal);

        return pedidoMapper.toDTO(pedidoRepository.save(pedido));
    }




    private void validarStatusPedido(StatusPedido status) {

        if (status == null) {
            throw new BusinessException("O status do pedido não pode ser nulo.");
        }

        if (status != StatusPedido.AGUARDANDO) {
            throw new BusinessException("Status do pedido inválido: " + status);
        }
    }


}
