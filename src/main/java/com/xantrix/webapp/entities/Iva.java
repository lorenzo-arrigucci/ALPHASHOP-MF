package com.xantrix.webapp.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Entity
@Table(name = "IVA")
@Data
public class Iva implements Serializable {
	private static final long serialVersionUID = 3908413911095474076L;

	@Id
	@Column(name = "IDIVA")
	private int idIva;

	@Column(name = "DESCRIZIONE")
	private String descrizione;

	@Column(name = "ALIQUOTA")
	private int aliquota;
	
	@OneToMany(mappedBy = "iva", fetch = FetchType.LAZY)
	@JsonBackReference
	private Set<Articolo> articoli = new HashSet<>();
}
