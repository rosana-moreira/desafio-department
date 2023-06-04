package com.test.department.resources;

import com.test.department.dto.ProfileDTO;
import com.test.department.services.ProfileService;
import com.test.department.services.exception.DeletePerfilException;
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
@RequestMapping(value = "/profiles")
public class ProfileResource {
    @Autowired
    private ProfileService service;

    @GetMapping
    public ResponseEntity<Page<ProfileDTO>> findAll(Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("name").ascending());
        Page<ProfileDTO> list = service.findAllPaged(pageable);
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<ProfileDTO> insert(@RequestBody ProfileDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProfileDTO> update(@PathVariable Long id, @RequestBody ProfileDTO dto) {
        dto = service.update(dto, id);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws DeletePerfilException {
        service.deleteProfile(id);
        return ResponseEntity.noContent().build();
    }
}
