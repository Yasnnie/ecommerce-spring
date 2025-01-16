package br.ifrn.edu.jeferson.ecommerce.controller;

import br.ifrn.edu.jeferson.ecommerce.domain.dtos.CategoriaRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.CategoriaResponseDTO;
import br.ifrn.edu.jeferson.ecommerce.service.CategoriaService;
import br.ifrn.edu.jeferson.ecommerce.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/categorias")
@Tag(name = "Categorias", description = "API de gerenciamento de categorias dos Produtos")
public class CategoriaController {
    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private ProdutoService produtoService;

    @Operation(summary = "Criar uma nova categoria")
    @PostMapping
    public ResponseEntity<CategoriaResponseDTO> salvar(@RequestBody CategoriaRequestDTO categoriaDto) {
        return ResponseEntity.ok(categoriaService.salvar(categoriaDto));
    }

    @Operation(summary = "Listar uma nova categoria")
    @GetMapping
    public ResponseEntity<List<CategoriaResponseDTO>> listar() {
        return ResponseEntity.ok(categoriaService.lista());
    }

    @Operation(summary = "Deletar uma nova categoria")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        categoriaService.deletar(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Atualizar uma nova categoria")
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> atualizar(@PathVariable Long id, @RequestBody CategoriaRequestDTO categoriaDto) {
        return ResponseEntity.ok(categoriaService.atualizar(id, categoriaDto));
    }

    @Operation(summary = "Buscar categoria por ID")
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> categoriaById(@RequestParam Long id) {
        return ResponseEntity.ok(categoriaService.buscarPorId(id));
    }

    @Operation(summary = "Associar produto à categoria")
    @PostMapping("/{categoriaId}/produtos/{produtoId}")
    public ResponseEntity<Void> associarProduto(@PathVariable Long categoriaId, @PathVariable Long produtoId) {
            produtoService.associarProdutoCategoria(categoriaId, produtoId);
            return ResponseEntity.ok().build();
    }

    @Operation(summary = "Remover produto da categoria")
    @DeleteMapping("/{categoriaId}/produtos/{produtoId}")
    public ResponseEntity<Void> removerProdutoDaCategoria(
            @PathVariable Long categoriaId,
            @PathVariable Long produtoId) {
        produtoService.removerProdutoDaCategoria(categoriaId, produtoId);
        return ResponseEntity.noContent().build();
    }

}
