package com.test.department.services;

import com.test.department.dto.ProfileDTO;
import com.test.department.dto.UserDTO;
import com.test.department.entities.CompanyPosition;
import com.test.department.entities.Profile;
import com.test.department.entities.User;
import com.test.department.repositories.CompanyPositionRepository;
import com.test.department.repositories.ProfileRepository;
import com.test.department.repositories.UserRepository;
import com.test.department.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;


@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private CompanyPositionRepository companyPositionRepository;


    @Transactional(readOnly = true)
    public Page<UserDTO> findAllPaged(Pageable pageable) {
        Page<User> list = repository.findAll(pageable);
        return list.map(x -> new UserDTO(x));

    }

    @Transactional
    public UserDTO insert(UserDTO dto) {
        User entity = new User();
        copyDtoEntity(dto, entity);
        entity = repository.save(entity);
        return new UserDTO(entity);
    }

    @Transactional
    public UserDTO update(UserDTO dto, Long id) {
        try {
            User entity = repository.getOne(id);
            copyDtoEntity(dto, entity);
            entity = repository.save(entity);
            return new UserDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Alteração não permitida para o id :" + id);
        }
    }

    private void copyDtoEntity(UserDTO dto, User entity) {
        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setGender(dto.getGender());
        entity.setDateRegister(Instant.now());
        entity.setBirthDate(dto.getBirthDate());

        CompanyPosition companyPosition = companyPositionRepository.findById(dto.getCompanyPositionId())
                .orElseThrow(() -> new ResourceNotFoundException("Cargo não encontrado!"));
        entity.setCompanyPosition(companyPosition);

        entity.getProfiles().clear();
        for (ProfileDTO ptDto : dto.getProfile()) {
            Profile p = profileRepository.findById(ptDto.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Perfil não encontrado com o ID: " + ptDto.getId()));
            entity.getProfiles().add(p);
        }

    }

}
