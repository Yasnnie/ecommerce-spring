package br.ifrn.edu.jeferson.ecommerce.controller;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ClienteRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.ClienteResponseDTO;
import br.ifrn.edu.jeferson.ecommerce.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/clientes")
public class ClienteController {
    @Autowired
    private ClientService clienteService;

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


}

