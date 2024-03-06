package com.xantrix.webapp.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.xantrix.webapp.entities.Articolo;

public interface ArticoliRepository extends PagingAndSortingRepository<Articolo, String> {

	@Query(value = "SELECT * FROM ARTICOLI WHERE DESCRIZIONE LIKE :desArt", nativeQuery = true)
	List<Articolo> selByDescrizioneLike(@Param("desArt") String descrizione);
	
	List<Articolo> findByDescrizioneLike(String descrizione, Pageable pageable);
	
	Articolo findByCodArt(String codArt);
	
	@Query(value = "SELECT a FROM Articolo a JOIN a.barcode b WHERE b.barcode IN (:ean)")
	Articolo selByEan(@Param("ean") String ean);
}
