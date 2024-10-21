package com.example.exameng7.repository;

import com.example.exameng7.entity.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoRepository extends JpaRepository<PedidoEntity, Long> {
    //Busca todos los pedidos con un estado específico
    List<PedidoEntity> findByEstado(PedidoEntity.EstadoPedido estado);
}
