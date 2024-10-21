package com.example.exameng7.service.impl;

import com.example.exameng7.entity.PersonaEntity;
import com.example.exameng7.repository.PersonaRepository;
import com.example.exameng7.service.PersonaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PersonaServiceImpl implements PersonaService {
    // Inyección del repositorio de Persona
    private final PersonaRepository personaRepository;
    // Constructor del Repositorio Persona
    public PersonaServiceImpl(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    @Override //Crea y guarda
    public PersonaEntity crearPersona(PersonaEntity persona) {
        return personaRepository.save(persona);
    }

    @Override //Busca todas las personas en estado Activo
    public List<PersonaEntity> buscarTodasActivas() {
        return personaRepository.findByEstado(PersonaEntity.EstadoPersona.ACTIVO);
    }

    @Override //Busca una persona por su numero de documento
    public PersonaEntity buscarPorDocumento(Integer numDocuemento) {
        List<PersonaEntity> personas = personaRepository.findAll();
        for (PersonaEntity persona : personas) {
            //Si el documento coincide, retorna la persona
            if (persona.getNumDocumento().equals(numDocuemento)) {
                return persona;
            }
        }
        //Lanza Excepcion en caso de no encontrar
        throw new NoSuchElementException("Persona no encontrada");
    }

    @Override //Actualiza los datos de una persona
    public PersonaEntity actualizarPersona(Long id, PersonaEntity persona) {
        PersonaEntity personaExistente = personaRepository.findById(id)
                .orElseThrow(()->new NoSuchElementException("Persona no encontrada"));
        personaExistente.setNombres(persona.getNombres());
        personaExistente.setApellidos(persona.getApellidos());
        personaExistente.setDireccionEntity(persona.getDireccionEntity());
        personaExistente.setPedido(persona.getPedido());
        return personaRepository.save(personaExistente);
    }

    @Override //Elimina una persona, en realidad coloca su estado en INACTIVO
    public void eliminarPersona(Long id) {
        PersonaEntity personaExistente = personaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Persona no encontrada"));
        personaExistente.setEstado(PersonaEntity.EstadoPersona.INACTIVO);
        personaRepository.save(personaExistente);
    }
}
