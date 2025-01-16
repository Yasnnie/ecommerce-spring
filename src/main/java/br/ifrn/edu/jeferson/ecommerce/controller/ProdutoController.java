package br.ifrn.edu.jeferson.ecommerce.controller;


import br.ifrn.edu.jeferson.ecommerce.domain.Produto;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ProdutoResponseDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ProdutoResquestDTO;
import br.ifrn.edu.jeferson.ecommerce.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/produtos")
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

}
