package com.xantrix.webapp.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;


@Entity
@Table(name = "ARTICOLI")
@Data
public class Articolo implements Serializable {
	private static final long serialVersionUID = -3425081639572062017L;
	
	@Id
	@Column(name = "CODART")
	private String codArt;

	@Column(name = "DESCRIZIONE")
	private String descrizione;

	@Column(name = "UM")
	private String um;

	@Column(name = "CODSTAT")
	private String codStat;

	@Column(name = "PZCART")
	private Integer pzCart;

	@Column(name = "PESONETTO")
	private double pesoNetto;

	@Column(name = "IDSTATOART")
	private String idStatoArt;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATACREAZIONE")
	private Date dataCreaz;
	
	@OneToMany(mappedBy = "articolo", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@JsonManagedReference
	private Set<Barcode> barcode = new HashSet<>();
	
	@OneToOne(mappedBy = "articolo", cascade = CascadeType.ALL, orphanRemoval = true)
	private Ingrediente ingrediente;
	
	@ManyToOne
	@JoinColumn(name = "IDIVA", referencedColumnName = "idIva")
	private Iva iva;
	
	@ManyToOne
	@JoinColumn(name = "IDFAMASS", referencedColumnName = "id")
	private FamigliaAssortimento famAssort;
}
