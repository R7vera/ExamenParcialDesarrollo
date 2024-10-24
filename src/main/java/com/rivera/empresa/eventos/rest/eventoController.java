package com.rivera.empresa.eventos.rest;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rivera.empresa.eventos.entity.evento;
import com.rivera.empresa.eventos.service.eventoService;
import com.rivera.empresa.eventos.util.WrapperResponse;
import com.rivera.empresa.eventos.dto.eventoDto;
import com.rivera.empresa.eventos.converter.eventoConverter;


@RestController
@RequestMapping("/v1/evento")
public class eventoController {
	@Autowired
	private eventoService service;
	
	@Autowired
	private eventoConverter converter;
	
	@GetMapping
	public ResponseEntity<List<eventoDto>> findAll(
			@RequestParam(value = "offset", required = false, defaultValue = "0") int pageNumber,
			@RequestParam(value = "limit", required = false, defaultValue = "5") int pageSize
			){
		Pageable page = PageRequest.of(pageNumber, pageSize);				
		List<eventoDto> categorias = converter.fromEntity(service.findAll());
		
		return new WrapperResponse(true, "success", categorias).createResponse(HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<eventoDto> create (@RequestBody eventoDto categoria){
		evento categoriaEntity=converter.fromDTO(categoria);
		eventoDto registro = converter.fromEntity(service.save(categoriaEntity));
		return new WrapperResponse(true, "success", registro).createResponse(HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<eventoDto> update(@PathVariable("id") int id,@RequestBody eventoDto categoria){
		evento categoriaEntity=converter.fromDTO(categoria);
		eventoDto registro = converter.fromEntity(service.save(categoriaEntity));
		return new WrapperResponse(true, "success", registro).createResponse(HttpStatus.OK);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable("id") int id){
		service.delete(id);
		return new WrapperResponse(true, "success", null).createResponse(HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<eventoDto> findById(@PathVariable("id") int id){
		eventoDto registro=converter.fromEntity(service.findById(id));
		return new WrapperResponse(true, "success", registro).createResponse(HttpStatus.OK);
	}
}





