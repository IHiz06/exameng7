package com.example.exameng7.service.impl;

import com.example.exameng7.entity.PedidoEntity;
import com.example.exameng7.entity.PersonaEntity;
import com.example.exameng7.repository.PedidoRepository;
import com.example.exameng7.repository.PersonaRepository;
import com.example.exameng7.service.PedidoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PedidoServiceImpl implements PedidoService {
    //Repositorios
    private final PedidoRepository pedidoRepository;
    private final PersonaRepository personaRepository;
    //Constructor de los Repositorios
    public PedidoServiceImpl(PedidoRepository pedidoRepository, PersonaRepository personaRepository){
        this.pedidoRepository = pedidoRepository;
        this.personaRepository = personaRepository;
    }

    @Override //GUardar un pedido y asignar a una persona
    public PedidoEntity guardarPedido(Long personaId, PedidoEntity pedido) {
        PersonaEntity personaExistente = personaRepository.findById(personaId)
                .orElseThrow(() -> new NoSuchElementException("Persona no encontrada"));
        pedido.setPersona(personaExistente);
        return pedidoRepository.save(pedido);
    }

    @Override //Devuelve todos los pedidos
    public List<PedidoEntity> buscarTodos() {
        return pedidoRepository.findAll();
    }

    @Override //Busca un pedido por su id (solo para probar)
    public PedidoEntity obtenerPedidoPorId(Long id) {
        return pedidoRepository.findById(id).orElseThrow(()
                -> new NoSuchElementException("Pedido no ecnontrado"));
    }

    @Override //Buscar un pedido por id o estado, segun lo proporcionado
    public PedidoEntity buscarPorParametro(Long id, String estado) {
        if (id != null) {
            return pedidoRepository.findById(id).orElseThrow(()
                    -> new NoSuchElementException("Pedido no encontrado"));
        }

        //Si no se proporciona un id pero se proporciona un estado, buscar por estado
        if (estado != null) {
            return buscarPedidoPorEstado(estado);
        }

        // Si no se proporciona ni id ni estado, devolver null
        return null;
    }
    //Busca un pedido por su estado, este devuelve la primera coincidencia
    private PedidoEntity buscarPedidoPorEstado(String estado) {
        //Convertir el estado de String a Enum
        PedidoEntity.EstadoPedido estadoPedido = convertirEstado(estado);
        List<PedidoEntity> pedidos = pedidoRepository.findByEstado(estadoPedido);
        if (!pedidos.isEmpty()) {
            return pedidos.get(0); //Devolver el primer pedido encontrado
        }
        return null; //Si no hay pedidos con el estado
    }
    //Convierte el estado en string para ingresar al enum
    private PedidoEntity.EstadoPedido convertirEstado(String estado) {
        // Convertir el estado a mayúsculas para que coincida con los valores del Enum
        return PedidoEntity.EstadoPedido.valueOf(estado.toUpperCase());
    }

    @Override //Actualiza los datos de un pedido
    public PedidoEntity actualizarPedido(Long id, Long personaId, PedidoEntity pedido) {
        PedidoEntity pedidoExistente = obtenerPedidoPorId(id);
        PersonaEntity personaExistente = personaRepository.findById(personaId)
                .orElseThrow(() -> new NoSuchElementException("Persona no encontrada"));
        //Actualiza los campos del pedido
        pedidoExistente.setDescripcion(pedido.getDescripcion());
        pedidoExistente.setCantidad(pedido.getCantidad());
        pedidoExistente.setEstado(pedido.getEstado());
        pedidoExistente.setPersona(personaExistente);
        return pedidoRepository.save(pedidoExistente);
    }

    @Override //Elimina un pedido (cambia el estado a ELIMINADO)
    public void eliminarPedido(Long id) {
        PedidoEntity pedidoExistente = obtenerPedidoPorId(id);
        pedidoExistente.setEstado(PedidoEntity.EstadoPedido.ELIMINADO);
        pedidoRepository.save(pedidoExistente);
    }
}
