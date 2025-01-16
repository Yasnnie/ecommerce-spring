package br.ifrn.edu.jeferson.ecommerce.controller;


import br.ifrn.edu.jeferson.ecommerce.domain.dtos.PedidoRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.PedidoResponseDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.PedidoUpdateStatusDTO;
import br.ifrn.edu.jeferson.ecommerce.service.PedidosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/pedidos")
@Tag(name = "Pedidos", description = "API de gerenciamento de pedidos")
public class PedidosController {

    @Autowired
    private PedidosService pedidosService;


    @Operation(summary = "Criar novo pedido")
    @PostMapping("/")
    public ResponseEntity<PedidoResponseDTO> salvarPedido(@RequestBody PedidoRequestDTO pedidoRequestDTO) {
        return ResponseEntity.ok(pedidosService.salvar(pedidoRequestDTO));
    }

    @Operation(summary = "Listar pedidos")
    @GetMapping("/")
    public ResponseEntity<Page<PedidoResponseDTO>> listar(@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(pedidosService.listarPedidos(pageable));
    }


    @Operation(summary = "Buscar pedido por ID")
    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponseDTO> getById(@RequestParam Long id) {
        return ResponseEntity.ok(pedidosService.buscarPedidoPorId(id));
    }

    @Operation(summary = "Atualizar status do pedido")
    @PatchMapping("/{id}/status")
    public ResponseEntity<PedidoResponseDTO> updateStatus(@RequestBody PedidoUpdateStatusDTO dto , @PathVariable Long id) {
        return ResponseEntity.ok(pedidosService.atualizarStatus(id, dto));
    }

    @Operation(summary = "Listar pedidos por cliente")
    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<PedidoResponseDTO>> listarPedidosPorCliente(@PathVariable Long clienteId) {
        List<PedidoResponseDTO> pedidos = pedidosService.listarPedidosPorCliente(clienteId);
        return ResponseEntity.ok(pedidos);
    }
}
