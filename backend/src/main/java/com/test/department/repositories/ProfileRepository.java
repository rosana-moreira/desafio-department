package com.test.department.repositories;

import com.test.department.entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    @Modifying
    @Query(value = "DELETE FROM tb_profile " +
            " WHERE NOT EXISTS (SELECT * FROM tb_user_profile " +
            " WHERE tb_user_profile.profile_id = tb_profile.id) " +
            " AND tb_profile.id = :id ", nativeQuery = true)
    int deleteProfilesWithoutUsers(@Param("id") Long id);
}
