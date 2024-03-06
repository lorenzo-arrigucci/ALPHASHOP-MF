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
@Table(name = "FAMASSORT")
@Data
public class FamigliaAssortimento implements Serializable {
	private static final long serialVersionUID = -904099946993447263L;

	@Id
	@Column(name = "ID")
	private int id;

	@Column(name = "DESCRIZIONE")
	private String descrizione;
	
	@OneToMany(mappedBy = "famAssort", fetch = FetchType.LAZY)
	@JsonBackReference
	private Set<Articolo> articoli = new HashSet<>();
}
