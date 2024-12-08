package com.app.agenda_reuniao;

import com.app.agenda_reuniao.models.EventoModel;
import com.app.agenda_reuniao.repository.EventoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class EventoControllerTesteDeIntegracao {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EventoRepository eventoRepository;

    @Test
    void getEventos_ShouldReturn200WithList() throws Exception {
        EventoModel evento1 = new EventoModel(null, "Evento 1", "Local 1", "2024-12-10", "09:00", "11:00");
        EventoModel evento2 = new EventoModel(null, "Evento 2", "Local 2", "2024-12-11", "10:00", "12:00");
        eventoRepository.saveAll(Arrays.asList(evento1, evento2));

        mockMvc.perform(get("/agendar"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[2].id").exists()) // Verificar se o ID existe
                .andExpect(jsonPath("$[2].nome").value("Evento 1"))
                .andExpect(jsonPath("$[2].local").value("Local 1"))
                .andExpect(jsonPath("$[2].data").value("2024-12-10"))
                .andExpect(jsonPath("$[2].horaInicial").value("09:00"))
                .andExpect(jsonPath("$[2].horaFinal").value("11:00"));
    }
}
