package com.example.exameng7.service;

import com.example.exameng7.entity.PersonaEntity;

import java.util.List;

public interface PersonaService {
    PersonaEntity crearPersona(PersonaEntity persona);
    List<PersonaEntity> buscarTodasActivas();
    PersonaEntity buscarPorDocumento(Integer numDocuemento);
    PersonaEntity actualizarPersona(Long id, PersonaEntity persona);
    void eliminarPersona(Long id);
}
