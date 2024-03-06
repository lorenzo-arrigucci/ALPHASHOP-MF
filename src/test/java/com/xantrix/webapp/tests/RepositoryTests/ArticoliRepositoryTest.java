package com.xantrix.webapp.tests.RepositoryTests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;

import com.xantrix.webapp.Application;
import com.xantrix.webapp.entities.Articolo;
import com.xantrix.webapp.repository.ArticoliRepository;


@SpringBootTest
@ContextConfiguration(classes = Application.class)
class ArticoliRepositoryTest
{
	
	@Autowired
	private ArticoliRepository articoliRepository;

	@Test
	void testfindByDescrizioneLike() {
		List<Articolo> items = articoliRepository.selByDescrizioneLike("ACQUA ULIVETO%");
		assertEquals(2, items.size());
	}
	
	@Test
	void testfindByDescrizioneLikePage() {
		List<Articolo> items = articoliRepository.findByDescrizioneLike("ACQUA%",PageRequest.of(0, 10));
		assertEquals(10, items.size());
	}
	
	@Test
	void testfindByCodArt() throws Exception {
		assertThat(articoliRepository.findByCodArt("002000301"))
				.extracting(Articolo::getDescrizione)
				.isEqualTo("ACQUA ULIVETO 15 LT");
	}
	
	@Test
	void testfindByEan() throws Exception {
		assertThat(articoliRepository.selByEan("8008490000021"))
		.extracting(Articolo::getDescrizione)
		.isEqualTo("ACQUA ULIVETO 15 LT");
	}
	

}
