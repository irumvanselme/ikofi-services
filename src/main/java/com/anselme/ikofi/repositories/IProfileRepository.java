package com.anselme.ikofi.repositories;

import com.anselme.ikofi.models.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProfileRepository extends JpaRepository<Profile, Long> {
}
