package com.app.agenda_reuniao;

import com.app.agenda_reuniao.controller.EventoController;
import com.app.agenda_reuniao.models.EventoModel;
import com.app.agenda_reuniao.service.EventoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class EventoControllerUnitTest {

    private final EventoService eventoService = mock(EventoService.class);
    private final EventoController eventoController = new EventoController(eventoService);

    @Test
    void testGetEventos_ReturnsListOfEventos() {
        List<EventoModel> eventosMock = Arrays.asList(
                new EventoModel(1L, "Evento 1", "Local 1", "2024-12-06", "10:00", "12:00"),
                new EventoModel(2L, "Evento 2", "Local 2", "2024-12-07", "14:00", "16:00")
        );
        when(eventoService.findAll()).thenReturn(eventosMock);

        // Chamando o método do controlador
        ResponseEntity<List<EventoModel>> response = eventoController.getEventos();

        Assertions.assertEquals(200, response.getStatusCodeValue());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(2, response.getBody().size());
        Assertions.assertEquals("Evento 1", response.getBody().get(0).getNome());
        Assertions.assertEquals("Evento 2", response.getBody().get(1).getNome());
        verify(eventoService, times(1)).findAll();
    }
}
