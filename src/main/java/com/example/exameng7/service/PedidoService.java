package com.example.exameng7.service;

import com.example.exameng7.entity.PedidoEntity;

import java.util.List;

public interface PedidoService {
    PedidoEntity guardarPedido(Long personaId, PedidoEntity pedido);
    List<PedidoEntity> buscarTodos();
    PedidoEntity obtenerPedidoPorId(Long id);
    PedidoEntity buscarPorParametro(Long id, String estado);
    PedidoEntity actualizarPedido(Long id, Long personaId, PedidoEntity pedido);
    void eliminarPedido(Long id);
}
