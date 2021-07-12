package com.anselme.ikofi.services.impl;

import com.anselme.ikofi.models.Activity;
import com.anselme.ikofi.models.User;
import com.anselme.ikofi.models.enums.EUserActions;
import com.anselme.ikofi.repositories.IAccountRepository;
import com.anselme.ikofi.repositories.IActivityRepository;
import com.anselme.ikofi.services.IActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityServiceImpl implements IActivityService {
    private final IActivityRepository activityRepository;

    @Autowired
    public ActivityServiceImpl(IActivityRepository iActivityRepository){
        this.activityRepository = iActivityRepository;
    }

    @Override
    public List<Activity> findByActionAndUser(User user, EUserActions action) {
        return activityRepository.findByActionAndUser(action, user);
    }

    public Activity record(Activity activity){
        return activityRepository.save(activity);
    }
}
