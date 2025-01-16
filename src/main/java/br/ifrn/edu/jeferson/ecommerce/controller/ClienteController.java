package br.ifrn.edu.jeferson.ecommerce.controller;

import br.ifrn.edu.jeferson.ecommerce.domain.dtos.EnderecoRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.EnderecoResponseDTO;
import br.ifrn.edu.jeferson.ecommerce.service.EnderecoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ClienteRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ClienteResponseDTO;
import br.ifrn.edu.jeferson.ecommerce.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/clientes")
public class ClienteController {
    @Autowired
    private ClientService clienteService;
    @Autowired
    private EnderecoService enderecoService;

    @Operation(summary = "Listar clientes páginado")
    @GetMapping("/")
    public ResponseEntity<Page<ClienteResponseDTO>> listar(@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok().body(clienteService.listarClientes(pageable));
    }

    @Operation(summary = "Criar uma novo cliente")
    @PostMapping
    public ResponseEntity<ClienteResponseDTO> salvar(@RequestBody ClienteRequestDTO clienteDTO) {
        return ResponseEntity.ok(clienteService.salvar(clienteDTO));
    }

    @Operation(summary = "Pegar cliente pelo ID")
    @GetMapping("/{id}/")
    public ResponseEntity<ClienteResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.getById(id));
    }

    @Operation(summary = "Deletar um cliente")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        clienteService.deletar(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Atualizar cliente")
    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> atualizar(@PathVariable Long id, @RequestBody ClienteRequestDTO categoriaDto) {
        return ResponseEntity.ok(clienteService.atualizar(id, categoriaDto));
    }

    @Operation(summary = "Cadastrar endereço para cliente")
    @PostMapping("/{id}/enderecos")
    public ResponseEntity<EnderecoResponseDTO> cadastrarEndereco(@PathVariable Long id, @RequestBody EnderecoRequestDTO enderecoDto) {
        return ResponseEntity.ok(enderecoService.salvar(id, enderecoDto));
    }

    @Operation(summary = " Buscar endereço do cliente")
    @GetMapping("/{id}/enderecos")
    public ResponseEntity<EnderecoResponseDTO> listEnderecos(@PathVariable Long id) {
        return ResponseEntity.ok(enderecoService.getByClientId(id));
    }

    @Operation(summary = "Atualizar endereço do cliente")
    @PutMapping("/{id}/enderecos")
    public ResponseEntity<EnderecoResponseDTO> updateEndereco(@PathVariable Long id, @RequestBody EnderecoRequestDTO enderecoDto) {
        return ResponseEntity.ok(enderecoService.atualizar(id, enderecoDto));
    }

    @Operation(summary = "Remover endereço do cliente")
    @DeleteMapping("/{id}/enderecos")
    public ResponseEntity<Void> removerEndereco(@PathVariable Long id) {
        enderecoService.deletar(id);
        return ResponseEntity.ok().build();
    }

}

