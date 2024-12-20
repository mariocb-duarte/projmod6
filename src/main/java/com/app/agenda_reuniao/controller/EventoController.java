package com.app.agenda_reuniao.controller;

import com.app.agenda_reuniao.models.EventoModel;
import com.app.agenda_reuniao.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agendar")
public class EventoController {

	private final EventoService eventoService;

	// Injeção via construtor
	@Autowired
	public EventoController(EventoService eventoService) {
		this.eventoService = eventoService;
	}

	// Endpoint para listar todos os eventos
	@GetMapping
	public ResponseEntity<List<EventoModel>> getEventos() {
		List<EventoModel> eventos = eventoService.findAll();
		return new ResponseEntity<>(eventos, HttpStatus.OK);
	}

	// Endpoint para buscar detalhes de um evento pelo ID
	@GetMapping("/{id}")
	public ResponseEntity<EventoModel> getEventoDetails(@PathVariable("id") Long id) {
		EventoModel evento = eventoService.findById(id);
		if (evento != null) {
			return new ResponseEntity<>(evento, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// Endpoint para editar um evento
	@GetMapping("/edit/{id}")
	public ResponseEntity<EventoModel> editEvento(@PathVariable("id") Long id) {
		EventoModel evento = eventoService.findById(id);
		if (evento != null) {
			return new ResponseEntity<>(evento, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// Endpoint para atualizar um evento
	@PostMapping("/{id}")
	public ResponseEntity<String> update(@PathVariable("id") Long id, @Validated @RequestBody EventoModel evento, BindingResult result) {
		if (result.hasErrors()) {
			return new ResponseEntity<>("Verifique se os campos obrigatórios foram preenchidos!", HttpStatus.BAD_REQUEST);
		}

		evento.setId(id);
		eventoService.save(evento);
		return new ResponseEntity<>("Evento atualizado com sucesso!", HttpStatus.OK);
	}

	// Endpoint para deletar um evento
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletarEvento(@PathVariable("id") Long id) {
		EventoModel evento = eventoService.findById(id);
		if (evento != null) {
			eventoService.deletarEvento(evento);
			return new ResponseEntity<>("Evento deletado com sucesso!", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Evento não encontrado!", HttpStatus.NOT_FOUND);
		}
	}

	// Endpoint para criar um novo evento
	@PostMapping("/new")
	public ResponseEntity<String> save(@Validated @RequestBody EventoModel evento, BindingResult result) {
		if (result.hasErrors()) {
			return new ResponseEntity<>("Verifique se os campos obrigatórios foram preenchidos!", HttpStatus.BAD_REQUEST);
		}

		eventoService.save(evento);
		return new ResponseEntity<>("Evento criado com sucesso!", HttpStatus.CREATED);
	}
}

