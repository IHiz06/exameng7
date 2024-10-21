package com.example.exameng7.controller;

import com.example.exameng7.entity.PersonaEntity;
import com.example.exameng7.service.PersonaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personas/v1")
public class PersonaController {

    private final PersonaService personaService;

    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    @PostMapping("/crear") //Crea una nueva persona y devuelve un estado HTTP 201
    public ResponseEntity<PersonaEntity> crearPersona(@RequestBody PersonaEntity persona) {
        return new ResponseEntity<>(personaService.crearPersona(persona), HttpStatus.CREATED);
    }

    @GetMapping("/buscarTodos") //Devuelve una lista de personas activas con estado HTTP 200
    public ResponseEntity<List<PersonaEntity>> buscarTodasActivas() {
        return new ResponseEntity<>(personaService.buscarTodasActivas(), HttpStatus.OK);
    }

    @GetMapping("/buscarPorDocumento/{numDocumento}") //Busca una persona por su número de documento y devuelve estado 200
    public ResponseEntity<PersonaEntity> buscarPorDocumento(@PathVariable Integer numDocumento) {
        return new ResponseEntity<>(personaService.buscarPorDocumento(numDocumento), HttpStatus.OK);
    }

    @PutMapping("/actualizar/{id}") //Actualiza los datos de una persona existente y devuelve estado 200
    public ResponseEntity<PersonaEntity> actualizarPersona(@PathVariable Long id, @RequestBody PersonaEntity persona) {
        return new ResponseEntity<>(personaService.actualizarPersona(id, persona), HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}") //Elimina (inactiva) una persona y devuelve estado 204
    public ResponseEntity<Void> eliminarPersona(@PathVariable Long id) {
        personaService.eliminarPersona(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
