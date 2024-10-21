package com.example.exameng7.controller;

import com.example.exameng7.entity.PedidoEntity;
import com.example.exameng7.service.PedidoService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos/v1")
public class PedidoController {
    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping("/crear/{personaId}") //Crea un nuevo pedido asociado a una persona y devuelve estado 201
    public ResponseEntity<PedidoEntity> crearPedido(@PathVariable Long personaId,
                                                    @RequestBody PedidoEntity pedido){
        PedidoEntity nuevoPedido = pedidoService.guardarPedido(personaId, pedido);
        return new ResponseEntity<>(nuevoPedido, HttpStatus.CREATED);
    }

    @GetMapping("/buscartodos") //Obtiene todos los pedidos
    public List<PedidoEntity> obtenerTodosLosPedidos() {
        return pedidoService.buscarTodos();
    }

    @GetMapping("/buscarporid/{id}") //Busca un pedido por su ID y devuelve estado 200
    public ResponseEntity<PedidoEntity> obtenerPedidoPorId(@PathVariable Long id) {
        PedidoEntity pedido = pedidoService.buscarPorParametro(id, null);
        return new ResponseEntity<>(pedido, HttpStatus.OK);
    }
    @GetMapping("/buscarporestado/{estado}") //Busca un pedido por su estado y devuelve estado 200
    public ResponseEntity<PedidoEntity> obtenerPedidoPorEstado(@PathVariable String estado) {
        PedidoEntity pedido = pedidoService.buscarPorParametro(null, estado);
        return new ResponseEntity<>(pedido, HttpStatus.OK);
    }
    @PutMapping("/actualizar/{id}/persona/{personaId}") //Actualiza un pedido y lo asocia a una persona, devuelve estado 200
    public ResponseEntity<PedidoEntity> actualizarPedido(@PathVariable Long id,
                                                         @PathVariable Long personaId,
                                                         @RequestBody PedidoEntity pedido){
        PedidoEntity pedidoActualizado = pedidoService.actualizarPedido(id, personaId, pedido);
        return new ResponseEntity<>(pedidoActualizado, HttpStatus.OK);
    }
    @DeleteMapping("/eliminar/{id}") //Marca un pedido como eliminado y devuelve estado 204
    public ResponseEntity<Void> eliminarPedido(@PathVariable Long id){
        pedidoService.eliminarPedido(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
