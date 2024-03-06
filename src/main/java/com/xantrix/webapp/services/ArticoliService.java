package com.xantrix.webapp.services;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.xantrix.webapp.dtos.ArticoloDto;
import com.xantrix.webapp.entities.Articolo;

public interface ArticoliService {
	
	public Iterable<Articolo> selTutti();
	
	public List<ArticoloDto> selByDescrizione(String descrizione);
	
	public List<ArticoloDto> selByDescrizione(String descrizione, Pageable pageable);
	
	public ArticoloDto selByCodArt(String codArt);
	
	public ArticoloDto selByBarcode(String barcode);
	
	public void delArticolo(Articolo articolo);
	
	public void insArticolo(Articolo articolo);
}
