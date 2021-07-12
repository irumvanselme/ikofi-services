package com.anselme.ikofi.repositories;

import com.anselme.ikofi.models.Activity;
import com.anselme.ikofi.models.User;
import com.anselme.ikofi.models.enums.EUserActions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IActivityRepository extends JpaRepository<Activity, Long> {

    public List<Activity> findByActionAndUser(EUserActions action, User user);
}
