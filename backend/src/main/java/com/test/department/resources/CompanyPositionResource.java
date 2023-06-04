package com.test.department.resources;

import com.test.department.dto.CompanyPositionDTO;

import com.test.department.services.CompanyPositionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/positions")
public class CompanyPositionResource {
    @Autowired
    private CompanyPositionService service;

    @GetMapping
    public ResponseEntity<Page<CompanyPositionDTO>> findAll(Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("name").ascending());
        Page<CompanyPositionDTO> list = service.findAllPaged(pageable);
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<CompanyPositionDTO> insert(@RequestBody CompanyPositionDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CompanyPositionDTO> update(@PathVariable Long id, @RequestBody CompanyPositionDTO dto) {
        dto = service.update(dto, id);
        return ResponseEntity.ok().body(dto);
    }
}
