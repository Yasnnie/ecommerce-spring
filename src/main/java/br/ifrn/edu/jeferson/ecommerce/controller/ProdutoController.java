package br.ifrn.edu.jeferson.ecommerce.controller;

import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ProdutoResponseDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ProdutoResquestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ProdutoEstoqueResquestDTO;
import br.ifrn.edu.jeferson.ecommerce.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/produtos")

@Tag(name = "Produtos", description = "API de gerenciamento de produtos")
public class ProdutoController {
    @Autowired
    private ProdutoService produtoService;


    @Operation(summary = "Cadastrar novo produto")
    @PostMapping("/")
    public ResponseEntity<ProdutoResponseDTO> cadastrarProduto(@RequestBody ProdutoResquestDTO produtoDTO) {
        return ResponseEntity.ok(produtoService.salvar(produtoDTO));
    }

    @Operation(summary = "Buscar produto por ID")
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(produtoService.buscarPorId(id));
    }


    @Operation(summary = " Remover produto")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        produtoService.deletar(id);
        return ResponseEntity.ok().build();
    }


    @Operation(summary = "Atualizar produto")
    @PutMapping("/{id}/")
    public ResponseEntity<ProdutoResponseDTO> update(@PathVariable Long id, @RequestBody ProdutoResquestDTO produtoDTO) {
        return ResponseEntity.ok(produtoService.atualizar(id, produtoDTO));
    }

    @Operation(summary = " Listar produtos por categoria")
    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<List<ProdutoResponseDTO>> produtoPorCategoria(@PathVariable Long categoriaId) {
        return ResponseEntity.ok(produtoService.listarProdutosPorCategoria(categoriaId));
    }

    @Operation(summary = "Atualizar estoque do produto")
    @PatchMapping("/{id}/estoque")
    public ResponseEntity<ProdutoResponseDTO> atualizarEstoque(@PathVariable Long id, @RequestBody ProdutoEstoqueResquestDTO estoqueDto) {

        return ResponseEntity.ok(produtoService.atualizarEstoque(id, estoqueDto.getEstoque()));
    }

}
