package br.ifrn.edu.jeferson.ecommerce.service;

import br.ifrn.edu.jeferson.ecommerce.domain.Produto;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ProdutoResponseDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ProdutoResquestDTO;
import br.ifrn.edu.jeferson.ecommerce.exception.BusinessException;
import br.ifrn.edu.jeferson.ecommerce.exception.ResourceNotFoundException;
import br.ifrn.edu.jeferson.ecommerce.mapper.ProdutoMapper;
import br.ifrn.edu.jeferson.ecommerce.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

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


}
