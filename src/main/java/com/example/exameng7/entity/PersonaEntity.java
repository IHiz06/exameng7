package com.example.exameng7.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "persona")
@Getter
@Setter
public class PersonaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombres;
    private String apellidos;
    private Integer numDocumento;

    @Enumerated(EnumType.STRING) //Guarda como string en la bd - esto proveniente de los estados asignados enum
    private EstadoPersona estado;

    //@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "direccion_id", referencedColumnName = "id")
    private DireccionEntity direccionEntity;

    @JsonManagedReference //Arma la conexion como JSON y evita la recursión
    @OneToMany(mappedBy = "persona", cascade = CascadeType.ALL) //Conexion de uno a muchos
    private List<PedidoEntity> pedido;

    //Enumeración que define los estados posibles de la persona
    public enum EstadoPersona{
        ACTIVO, INACTIVO
    }
}
