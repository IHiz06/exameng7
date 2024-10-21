package com.example.exameng7.repository;

import com.example.exameng7.entity.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonaRepository extends JpaRepository<PersonaEntity, Long> {
    //Busca todas las personas con un estado específico
    List<PersonaEntity> findByEstado(PersonaEntity.EstadoPersona estado);
}
