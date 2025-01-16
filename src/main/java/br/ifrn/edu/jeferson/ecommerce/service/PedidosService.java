package br.ifrn.edu.jeferson.ecommerce.service;

import br.ifrn.edu.jeferson.ecommerce.domain.Cliente;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.*;
import br.ifrn.edu.jeferson.ecommerce.domain.enums.StatusPedido;
import br.ifrn.edu.jeferson.ecommerce.exception.BusinessException;
import br.ifrn.edu.jeferson.ecommerce.domain.Pedido;
import br.ifrn.edu.jeferson.ecommerce.domain.Produto;
import br.ifrn.edu.jeferson.ecommerce.domain.ItemPedido;
import br.ifrn.edu.jeferson.ecommerce.mapper.ItemPedidoMapper;
import br.ifrn.edu.jeferson.ecommerce.repository.ClienteRepository;
import br.ifrn.edu.jeferson.ecommerce.repository.ItemPedidoRepository;
import br.ifrn.edu.jeferson.ecommerce.repository.PedidoRepository;
import br.ifrn.edu.jeferson.ecommerce.mapper.PedidoMapper;
import br.ifrn.edu.jeferson.ecommerce.repository.ProdutoRepository;
import br.ifrn.edu.jeferson.ecommerce.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
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

    @Autowired
    private ClienteRepository clienteRepository;

    public PedidoResponseDTO salvar(PedidoRequestDTO pedidoRequestDTO) {
        Cliente cliente = clienteRepository.findById(pedidoRequestDTO.getClienteId()).orElseThrow(()-> new ResourceNotFoundException("Cliente não encontrado"));

        if(pedidoRequestDTO.getItens().size() <= 0 ) throw new BusinessException("Adicione pelo menos um item no seu pedido");

        Pedido pedido = pedidoMapper.toEntity(pedidoRequestDTO);
        pedido.setCliente(cliente);
        pedido.setStatusPedido(StatusPedido.AGUARDANDO);
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


    public Page<PedidoResponseDTO> listarPedidos(Pageable pageable) {
        Page<Pedido> pedidosPage = pedidoRepository.findAll(pageable);
        return pedidosPage.map(pedidoMapper::toDTO);
    }


    public PedidoResponseDTO buscarPedidoPorId(Long id) {
        Pedido pedido = pedidoRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("pedido não encontrado"));
        return pedidoMapper.toDTO(pedido);
    }


    public PedidoResponseDTO atualizarStatus(Long id, PedidoUpdateStatusDTO dto) {
        Pedido pedido = pedidoRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Pedido não encontrado"));
        pedido.setStatusPedido(dto.getStatusPedido());
        return pedidoMapper.toDTO(pedidoRepository.save(pedido));
    }

    public List<PedidoResponseDTO> listarPedidosPorCliente(Long clienteId) {
        List<Pedido> pedidos = pedidoRepository.findByClienteId(clienteId);

        if (pedidos.isEmpty())
            throw new ResourceNotFoundException("Nenhum pedido encontrado para o cliente ID: " + clienteId);


        return pedidos.stream()
                .map(pedidoMapper::toDTO)
                .toList();
    }

}
