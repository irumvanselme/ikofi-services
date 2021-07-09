package com.anselme.ikofi.repositories;

import com.anselme.ikofi.models.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface IProfileRepository extends JpaRepository<Profile, Long> {
}
