package com.anselme.ikofi.services;

import com.anselme.ikofi.models.Activity;
import com.anselme.ikofi.models.User;
import com.anselme.ikofi.models.enums.EUserActions;

import java.util.List;

public interface IActivityService {

    public List<Activity> findByActionAndUser(User user, EUserActions action);
}
