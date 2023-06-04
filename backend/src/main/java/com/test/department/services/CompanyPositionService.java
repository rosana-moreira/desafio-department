package com.test.department.services;

import com.test.department.dto.CompanyPositionDTO;
import com.test.department.entities.CompanyPosition;
import com.test.department.repositories.CompanyPositionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CompanyPositionService {
    @Autowired
    private CompanyPositionRepository repository;

    @Transactional(readOnly = true)
    public Page<CompanyPositionDTO> findAllPaged(Pageable pageable) {
        Page<CompanyPosition> list = repository.findAll(pageable);
        return list.map(x -> new CompanyPositionDTO(x));
    }

    @Transactional
    public CompanyPositionDTO insert(CompanyPositionDTO dto) {
        CompanyPosition entity = new CompanyPosition();
        copyDtoEntity(dto, entity);
        entity = repository.save(entity);
        return new CompanyPositionDTO(entity);

    }

    @Transactional
    public CompanyPositionDTO update(CompanyPositionDTO dto, Long id) {
        try {
            CompanyPosition entity = repository.getOne(id);
            copyDtoEntity(dto, entity);
            entity = repository.save(entity);
            return new CompanyPositionDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("id não encontrado!");
        }
    }

    private void copyDtoEntity(CompanyPositionDTO dto, CompanyPosition entity) {
        entity.setName(dto.getName());
    }
}