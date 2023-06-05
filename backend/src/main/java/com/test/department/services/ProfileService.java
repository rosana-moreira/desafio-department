package com.test.department.services;

import com.test.department.dto.ProfileDTO;

import com.test.department.entities.Profile;
import com.test.department.repositories.ProfileRepository;
import com.test.department.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepository repository;

    @Transactional(readOnly = true)
    public Page<ProfileDTO> findAllPaged(Pageable pageable) {
        Page<Profile> list = repository.findAll(pageable);
        return list.map(x -> new ProfileDTO(x));

    }

    @Transactional
    public ProfileDTO insert(ProfileDTO dto) {
        Profile entity = new Profile();
        copyDtoEntity(dto, entity);
        entity = repository.save(entity);
        return new ProfileDTO(entity);

    }

    @Transactional
    public ProfileDTO update(ProfileDTO dto, Long id) {
        try {
            Profile entity = repository.getOne(id);
            copyDtoEntity(dto, entity);
            entity = repository.save(entity);
            return new ProfileDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Alteração não permitida para o id : " + id);
        }
    }

    @Transactional
    public void deleteProfile(Long id) {
        int records = repository.deleteProfilesWithoutUsers(id);
        if (records == 0) {
            throw new ResourceNotFoundException("Não foi possível excluir o perfil com id : " + id);
        }
    }

    private void copyDtoEntity(ProfileDTO dto, Profile entity) {
        entity.setName(dto.getName());
    }


}
