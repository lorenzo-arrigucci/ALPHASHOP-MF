package com.xantrix.webapp.dtos;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import lombok.Data;

@Data
public class ArticoloDto {
	
	private String codArt;
	private String descrizione;
	private String um;
	private String codStat;
	private Integer pzCart;
	private double pesoNetto;
	private String idStatoArt;
	private Date dataCreazione;
	private double prezzo = 0;
	
	private Set<BarcodeDto> ean = new HashSet<>();
	private IngredienteDto ingrediente;
	private CategoriaDto categoria;
	private IvaDto iva;
}
