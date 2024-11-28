package com.app.agenda_reuniao;

import com.app.agenda_reuniao.models.EventoModel;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

@SpringBootTest
class AgendaReuniaoApplicationTests {

	private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	@Test
	void contextLoads() {
	}

	@Test
	public void testEventoModelConstructorAndSetters() {
		EventoModel evento = new EventoModel();

		evento.setId(1L);
		evento.setNome("Reunião de Planejamento");
		evento.setLocal("Sala 101");
		evento.setData("2024-12-01");
		evento.setHoraInicial("09:00");
		evento.setHoraFinal("11:00");

		assertNotNull(evento.getId());
		assertEquals(1L, evento.getId());
		assertEquals("Reunião de Planejamento", evento.getNome());
		assertEquals("Sala 101", evento.getLocal());
		assertEquals("2024-12-01", evento.getData());
		assertEquals("09:00", evento.getHoraInicial());
		assertEquals("11:00", evento.getHoraFinal());
	}

	@Test
	public void testValidEventoModel() {
		EventoModel evento = new EventoModel();
		evento.setNome("Reunião de Planejamento");
		evento.setLocal("Sala 101");
		evento.setData("2024-12-01");
		evento.setHoraInicial("09:00");
		evento.setHoraFinal("11:00");

		Set<ConstraintViolation<EventoModel>> violations = validator.validate(evento);
		assertTrue(violations.isEmpty());
	}

	@Test
	public void testInvalidEventoModelWithBlankNome() {
		EventoModel evento = new EventoModel();
		evento.setNome("");
		evento.setLocal("Sala 101");
		evento.setData("2024-12-01");
		evento.setHoraInicial("09:00");
		evento.setHoraFinal("11:00");

		Set<ConstraintViolation<EventoModel>> violations = validator.validate(evento);
		assertFalse(violations.isEmpty());
		assertEquals(1, violations.size());
	}

	@Test
	public void testInvalidEventoModelWithNullLocal() {
		EventoModel evento = new EventoModel();
		evento.setNome("Reunião de Planejamento");
		evento.setLocal(null);
		evento.setData("2024-12-01");
		evento.setHoraInicial("09:00");
		evento.setHoraFinal("11:00");

		Set<ConstraintViolation<EventoModel>> violations = validator.validate(evento);
		assertFalse(violations.isEmpty());
		assertEquals(1, violations.size());
	}

	@Test
	public void testEqualsAndHashCode() {
		EventoModel evento1 = new EventoModel();
		evento1.setId(1L);
		evento1.setNome("Reunião de Planejamento");
		evento1.setLocal("Sala 101");
		evento1.setData("2024-12-01");
		evento1.setHoraInicial("09:00");
		evento1.setHoraFinal("11:00");

		EventoModel evento2 = new EventoModel();
		evento2.setId(1L);
		evento2.setNome("Reunião de Planejamento");
		evento2.setLocal("Sala 101");
		evento2.setData("2024-12-01");
		evento2.setHoraInicial("09:00");
		evento2.setHoraFinal("11:00");

		assertTrue(evento1.equals(evento2));
		assertEquals(evento1.hashCode(), evento2.hashCode());
	}

	@Test
	public void testNotEquals() {
		EventoModel evento1 = new EventoModel();
		evento1.setId(1L);
		evento1.setNome("Reunião de Planejamento");

		EventoModel evento2 = new EventoModel();
		evento2.setId(2L);
		evento2.setNome("Reunião de Marketing");

		assertFalse(evento1.equals(evento2));
	}

	@Test
	public void testToString() {
		EventoModel evento = new EventoModel();
		evento.setId(1L);
		evento.setNome("Reunião de Planejamento");
		evento.setLocal("Sala 101");
		evento.setData("2024-12-01");
		evento.setHoraInicial("09:00");
		evento.setHoraFinal("11:00");

		String expected = "EventoModel [id=1, nome=Reunião de Planejamento, local=Sala 101, data=2024-12-01, horaInicial=09:00, horaFinal=11:00]";
		assertEquals(expected, evento.toString());
	}
}
