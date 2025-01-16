package br.ifrn.edu.jeferson.ecommerce.controller;


import br.ifrn.edu.jeferson.ecommerce.domain.dtos.PedidoRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.domain.dtos.PedidoResponseDTO;
import br.ifrn.edu.jeferson.ecommerce.service.PedidosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
