package com.xantrix.webapp;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.xantrix.webapp.dtos.ArticoloDto;
import com.xantrix.webapp.dtos.BarcodeDto;
import com.xantrix.webapp.entities.Articolo;
import com.xantrix.webapp.entities.Barcode;

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setSkipNullEnabled(true);
		mapper.addMappings(articoliMapping);
		mapper.addMappings(barcodeMapping);
		mapper.addConverter(stringTrim);
		return mapper;
	}
	
	Converter<String, String> stringTrim = context -> context.getSource() == null ? "" : context.getSource().trim();
	
	PropertyMap<Articolo, ArticoloDto> articoliMapping = new PropertyMap<Articolo, ArticoloDto>() {
		@Override
		protected void configure() {
			map().setDataCreazione(source.getDataCreaz());
			map(source.getBarcode()).setEan(null);
			map(source.getFamAssort()).setCategoria(null);
		}
	};
	
	PropertyMap<Barcode, BarcodeDto> barcodeMapping = new PropertyMap<Barcode, BarcodeDto>() {
		@Override
		protected void configure() {
			map().setTipo(source.getIdTipoArt());
		}
	};
	
	public <S, T> T mappa(S source, Class<T> target) {
		ModelMapper mapper = modelMapper();
		return mapper.map(source, target);
	}
	
	public <S extends List<?>, T> List<T> mappaLista(S source, Class<T> target) {
		ModelMapper mapper = modelMapper();
		return source.stream()
				.map(s -> mapper.map(s, target))
				.collect(Collectors.toList());
	}
	
	public <S extends Set<?>, T> Set<T> mappaSet(S source, Class<T> target) {
		ModelMapper mapper = modelMapper();
		return source.stream()
				.map(s -> mapper.map(s, target))
				.collect(Collectors.toSet());
	}
}
