package com.anselme.ikofi.controllers;

import com.anselme.ikofi.models.User;
import com.anselme.ikofi.models.enums.EUserActions;
import com.anselme.ikofi.services.IActivityService;
import com.anselme.ikofi.services.IUserService;
import com.anselme.ikofi.utils.dto.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/activities")
public class ActivityController {

    private final IUserService userService;
    private final IActivityService activityService;

    @Autowired
    public ActivityController(IUserService iUserService, IActivityService iActivityService){
        this.userService = iUserService;
        this.activityService = iActivityService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAll(){
        User logggedInUser = userService.getLoggedInUser();

        return ResponseEntity.ok(ApiResponse.success(logggedInUser.getActivities()));
    }

    @GetMapping("/{status}")
    public ResponseEntity<ApiResponse> getSent(@PathVariable EUserActions status){
        User logggedInUser = userService.getLoggedInUser();

        return ResponseEntity.ok(ApiResponse.success(activityService.findByActionAndUser(logggedInUser, status)));
    }
}
