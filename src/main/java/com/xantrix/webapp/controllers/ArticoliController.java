package com.xantrix.webapp.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xantrix.webapp.dtos.ArticoloDto;
import com.xantrix.webapp.exceptions.NotFoundException;
import com.xantrix.webapp.services.ArticoliService;

@RestController
@RequestMapping("/api/articoli")
@CrossOrigin("http://localhost:4200")
public class ArticoliController {
	private static final Logger logger = LoggerFactory.getLogger(ArticoliController.class);
	
	@Autowired
	private ArticoliService articoliService;
	
	@GetMapping(value = "/cerca/barcode/{ean}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArticoloDto> listArtByEan(@PathVariable("ean") String ean) throws NotFoundException {
		logger.info("[listArtByEan] Ricerca articolo con barcode {}", ean);
		ArticoloDto articolo = articoliService.selByBarcode(ean);
		if (articolo == null) {
			String errMsg = String.format("Il barcode %s non è stato trovato!", ean);
			logger.warn(errMsg);
			throw new NotFoundException(errMsg);
		}
		logger.info("[listArtByEan] Trovato articolo con barcode {}", ean);
		return new ResponseEntity<>(articolo, HttpStatus.OK);
	}
	
	@GetMapping(value = "/cerca/codice/{codArt}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ArticoloDto> listArtByCodArt(@PathVariable("codArt") String codArt) throws NotFoundException {
		logger.info("[listArtByEan] Ricerca articolo con codice {}", codArt);
		ArticoloDto articolo = articoliService.selByCodArt(codArt);
		if (articolo == null) {
			String errMsg = String.format("L'articolo con codice %s non è stato trovato!", codArt);
			logger.warn(errMsg);
			throw new NotFoundException(errMsg);
		}
		logger.info("[listArtByEan] Trovato articolo con codice {}", codArt);
		return new ResponseEntity<>(articolo, HttpStatus.OK);
	}
	
	@GetMapping(value = "/cerca/descrizione/{filter}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ArticoloDto>> listArtByDesc(@PathVariable("filter") String descrizione) throws NotFoundException {
		logger.info("[listArtByEan] Ricerca articoli con descrizione {}", descrizione);
		List<ArticoloDto> articoli = articoliService.selByDescrizione("%" + descrizione.toUpperCase() + "%");
		if (articoli == null || articoli.isEmpty()) {
			String errMsg = String.format("L'articolo con filtro %s non è stato trovato!", descrizione);
			logger.warn(errMsg);
			throw new NotFoundException(errMsg);
		}
		logger.info("[listArtByEan] Trovati articoli con descrizione {}", descrizione);
		return new ResponseEntity<>(articoli, HttpStatus.OK);
	}
}
