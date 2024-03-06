package com.xantrix.webapp.services.impl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xantrix.webapp.dtos.ArticoloDto;
import com.xantrix.webapp.entities.Articolo;
import com.xantrix.webapp.repository.ArticoliRepository;
import com.xantrix.webapp.services.ArticoliService;

@Service
@Transactional(readOnly = true)
public class ArticoliServiceImpl implements ArticoliService {
	
	@Autowired
	private ArticoliRepository articoliRepo;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public Iterable<Articolo> selTutti() {
		return articoliRepo.findAll();
	}

	@Override
	public List<ArticoloDto> selByDescrizione(String descrizione) {
		return convertToDtoList(articoliRepo.selByDescrizioneLike(descrizione));
	}

	@Override
	public List<ArticoloDto> selByDescrizione(String descrizione, Pageable pageable) {
		return convertToDtoList(articoliRepo.findByDescrizioneLike(descrizione, pageable));
	}

	@Override
	public ArticoloDto selByCodArt(String codArt) {		
		return convertToDto(articoliRepo.findByCodArt(codArt));
	}

	@Override
	public ArticoloDto selByBarcode(String barcode) {
		return convertToDto(articoliRepo.selByEan(barcode));
	}

	@Override
	@Transactional
	public void delArticolo(Articolo articolo) {
		articoliRepo.delete(articolo);
	}

	@Override
	@Transactional
	public void insArticolo(Articolo articolo) {
		articoliRepo.save(articolo);
	}
	
	private ArticoloDto convertToDto(Articolo articolo) {
		if (articolo == null) 
			return null;
		return mapper.map(articolo, ArticoloDto.class);
	}
	
	private List<ArticoloDto> convertToDtoList(List<Articolo> articoli) {
		if (articoli == null || articoli.isEmpty()) 
			return Collections.emptyList();
		return articoli.stream()
				.map(source -> mapper.map(source, ArticoloDto.class))
				.collect(Collectors.toList());
	}

}
