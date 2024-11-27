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

@RestController // Usando @RestController para uma API RESTful
@RequestMapping("/agendar") // Definindo um prefixo comum para todas as rotas
public class EventoController {

	@Autowired
	private EventoService eventoService;

	// Endpoint para listar todos os eventos
	@GetMapping
	public ResponseEntity<List<EventoModel>> getEventos() {
		List<EventoModel> eventos = eventoService.findAll();
		return new ResponseEntity<>(eventos, HttpStatus.OK); // Retorna os eventos em formato JSON com status 200 OK
	}

	// Endpoint para buscar detalhes de um evento pelo ID
	@GetMapping("/{id}")
	public ResponseEntity<EventoModel> getEventoDetails(@PathVariable("id") Long id) {
		EventoModel evento = eventoService.findById(id);
		if (evento != null) {
			return new ResponseEntity<>(evento, HttpStatus.OK); // Retorna o evento em formato JSON
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Retorna 404 caso o evento não seja encontrado
		}
	}

	// Endpoint para editar um evento (GET para carregar o evento para edição)
	@GetMapping("/edit/{id}")
	public ResponseEntity<EventoModel> editEvento(@PathVariable("id") Long id) {
		EventoModel evento = eventoService.findById(id);
		if (evento != null) {
			return new ResponseEntity<>(evento, HttpStatus.OK); // Retorna o evento em formato JSON
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Retorna 404 caso o evento não seja encontrado
		}
	}

	// Endpoint para atualizar um evento
	@PostMapping("/{id}")
	public ResponseEntity<String> update(@PathVariable("id") Long id, @Validated @RequestBody EventoModel evento, BindingResult result) {
		if (result.hasErrors()) {
			return new ResponseEntity<>("Verifique se os campos obrigatórios foram preenchidos!", HttpStatus.BAD_REQUEST); // Retorna erro 400 se houver problemas de validação
		}

		evento.setId(id); // Certifique-se de que o ID do evento está sendo atualizado corretamente
		eventoService.save(evento);
		return new ResponseEntity<>("Evento atualizado com sucesso!", HttpStatus.OK); // Retorna sucesso
	}

	// Endpoint para deletar um evento
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletarEvento(@PathVariable("id") Long id) {
		EventoModel evento = eventoService.findById(id);
		if (evento != null) {
			eventoService.deletarEvento(evento);
			return new ResponseEntity<>("Evento deletado com sucesso!", HttpStatus.OK); // Retorna sucesso
		} else {
			return new ResponseEntity<>("Evento não encontrado!", HttpStatus.NOT_FOUND); // Retorna erro 404 caso o evento não exista
		}
	}

	// Endpoint para criar um novo evento
	@PostMapping("/new")
	public ResponseEntity<String> save(@Validated @RequestBody EventoModel evento, BindingResult result) {
		if (result.hasErrors()) {
			return new ResponseEntity<>("Verifique se os campos obrigatórios foram preenchidos!", HttpStatus.BAD_REQUEST); // Retorna erro 400
		}

		eventoService.save(evento);
		return new ResponseEntity<>("Evento criado com sucesso!", HttpStatus.CREATED); // Retorna sucesso com código 201
	}
}
