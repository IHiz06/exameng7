package com.example.exameng7.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "pedido")
@Getter
@Setter
public class PedidoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    private String descripcion;
    private Integer cantidad;

    @Enumerated(EnumType.STRING) //almacenamiento como string en la bd
    private EstadoPedido estado;

    @JsonBackReference
    @ManyToOne //Relacion uno a muchos
    @JoinColumn(name = "persona_id", referencedColumnName = "id", nullable = false)
    private PersonaEntity persona;


    public enum EstadoPedido { //Enumeracion que define los estados posibles del pedido
        PENDIENTE, PROCESO, CONFIRMADO, ELIMINADO
    }
}
