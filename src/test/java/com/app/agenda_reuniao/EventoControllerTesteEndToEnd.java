package com.app.agenda_reuniao;

import com.app.agenda_reuniao.models.EventoModel;
import com.app.agenda_reuniao.repository.EventoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EventoControllerTesteEndToEnd {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private EventoRepository eventoRepository;

    @Test
    void getEventos_ShouldReturn200WithList() {
        EventoModel evento1 = new EventoModel(null, "Evento 1", "Local 1", "2024-12-10", "09:00", "11:00");
        EventoModel evento2 = new EventoModel(null, "Evento 2", "Local 2", "2024-12-11", "10:00", "12:00");
        eventoRepository.saveAll(Arrays.asList(evento1, evento2));

        ResponseEntity<EventoModel[]> response = restTemplate.getForEntity("/agendar", EventoModel[].class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertTrue(response.getBody().length > 0);

        EventoModel[] eventos = response.getBody();
        Assertions.assertEquals("Evento 1", eventos[2].getNome());
        Assertions.assertEquals("Local 1", eventos[2].getLocal());
        Assertions.assertEquals("2024-12-10", eventos[2].getData());
        Assertions.assertEquals("09:00", eventos[2].getHoraInicial());
        Assertions.assertEquals("11:00", eventos[2].getHoraFinal());

        Assertions.assertEquals("Evento 2", eventos[3].getNome());
        Assertions.assertEquals("Local 2", eventos[3].getLocal());
        Assertions.assertEquals("2024-12-11", eventos[3].getData());
        Assertions.assertEquals("10:00", eventos[3].getHoraInicial());
        Assertions.assertEquals("12:00", eventos[3].getHoraFinal());
    }
}
