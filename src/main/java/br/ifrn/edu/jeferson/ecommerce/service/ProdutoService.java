package br.ifrn.edu.jeferson.ecommerce.service;

import br.ifrn.edu.jeferson.ecommerce.domain.Cliente;
import br.ifrn.edu.jeferson.ecommerce.domain.Produto;
import br.ifrn.edu.jeferson.ecommerce.domain.Categoria;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ProdutoResponseDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ProdutoResquestDTO;
import br.ifrn.edu.jeferson.ecommerce.exception.BusinessException;
import br.ifrn.edu.jeferson.ecommerce.exception.ResourceNotFoundException;
import br.ifrn.edu.jeferson.ecommerce.mapper.ProdutoMapper;
import br.ifrn.edu.jeferson.ecommerce.repository.CategoriaRepository;
import br.ifrn.edu.jeferson.ecommerce.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProdutoMapper produtoMapper;

    public ProdutoResponseDTO salvar(ProdutoResquestDTO produtoResquestDTO) {
        if (produtoResquestDTO.getPreco().compareTo(BigDecimal.ZERO) < 0) throw  new BusinessException("Preço não pode ser negativo");
        if (produtoResquestDTO.getEstoque() < 0) throw  new BusinessException("Estoque não pode ser negativo");

        Produto produto = produtoMapper.toEntity(produtoResquestDTO);
        return produtoMapper.toDto(produtoRepository.save(produto));
    }

    public ProdutoResponseDTO buscarPorId(Long id) {
        Produto produto = produtoRepository.findById(id).orElseThrow(()-> new BusinessException("Produto não encontrado"));
        return produtoMapper.toDto(produto);
    }

    public void deletar(Long id) {
        if (!produtoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Produto não existe");
        }
        produtoRepository.deleteById(id);
    }

    public ProdutoResponseDTO atualizar(Long id, ProdutoResquestDTO produtoResquestDTO) {
        Produto produto = produtoRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Produto não encontrado"));

        if (produtoResquestDTO.getEstoque() < 0 )
            throw  new BusinessException("Estoque não pode ser negativo");

        if (produtoResquestDTO.getPreco().compareTo(BigDecimal.ZERO) < 0) throw  new BusinessException("Preço não pode ser negativo");

        produtoMapper.updateEntityFromDTO(produtoResquestDTO,produto);
        return produtoMapper.toDto(produtoRepository.save(produto));

    }

    public void associarProdutoCategoria(Long idCategoria, Long idProduto) {
        Categoria categoria = categoriaRepository.findById(idCategoria)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada"));

        Produto produto = produtoRepository.findById(idProduto)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));

        if (!produto.getCategorias().contains(categoria)) {
            produto.getCategorias().add(categoria);
            produtoRepository.save(produto);
        }
    }

    public void removerProdutoDaCategoria(Long categoriaId, Long produtoId) {
        Categoria categoria = categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada"));

        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));

        if (!categoria.getProdutos().contains(produto)) {
            throw new BusinessException("O produto não está associado a esta categoria");
        }

        categoria.getProdutos().remove(produto);
        produto.getCategorias().remove(categoria);

        categoriaRepository.save(categoria);
        produtoRepository.save(produto);
    }

    public List<ProdutoResponseDTO> listarProdutosPorCategoria(Long categoriaId) {
        Categoria categoria = categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada"));

        return categoria.getProdutos().stream().map(produtoMapper::toDto).collect(Collectors.toList());
    }

    public ProdutoResponseDTO atualizarEstoque(Long id, Integer novoEstoque) {
        if (novoEstoque < 0) {
            throw new BusinessException("O estoque não pode ser negativo");
        }

        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));

        produto.setEstoque(novoEstoque);
        return produtoMapper.toDto(produtoRepository.save(produto));
    }


}
