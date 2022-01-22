package com.waltonbd.assignment.springngblog.controller;

import com.waltonbd.assignment.springngblog.dto.BaseResponse;
import com.waltonbd.assignment.springngblog.dto.UserRequest;
import com.waltonbd.assignment.springngblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserAdminController {

    @Autowired
    UserService userService;

    @PostMapping("/updateUser")
    public ResponseEntity<BaseResponse> updateUser(@RequestBody UserRequest request){
        String responseMessage = null;
        if(request.adminUser == null || request.adminUser.equals("")){
            responseMessage = "Admin user field can't be empty or null.";
        }
        if(request.adminAuthToken == null || request.adminAuthToken.equals("")){
            responseMessage = "Auth token can't be empty or null.";
        }
        if(request.userInfo.userName == null || request.userInfo.userName.equals("")){
            responseMessage = "User name can't be empty or null.";
        }
        return ResponseEntity.ok(responseMessage == null ?
                new BaseResponse(200, userService.updateUser(request)) :
                new BaseResponse(300, responseMessage));
    }

    @PostMapping("/createUser")
    public ResponseEntity<BaseResponse> createUser(@RequestBody UserRequest request){
        String responseMessage = null;
        if(request.adminUser == null || request.adminUser.equals("")){
            responseMessage = "Admin user field can't be empty or null.";
        }
        if(request.adminAuthToken == null || request.adminAuthToken.equals("")){
            responseMessage = "Auth token can't be empty or null.";
        }
        if(request.userInfo.userName == null || request.userInfo.userName.equals("")){
            responseMessage = "User name can't be empty or null.";
        }
        if(request.userInfo.status == null || request.userInfo.status.equals("")){
            responseMessage = "User status can't be empty or null.";
        }
        if(request.userInfo.email == null || request.userInfo.email.equals("")){
            responseMessage = "User email address can't be empty or null.";
        }
        if(request.userInfo.password == null || request.userInfo.password.equals("")){
            responseMessage = "User password can't be empty or null.";
        }
        return ResponseEntity.ok(responseMessage == null ?
                new BaseResponse(200, userService.createUser(request)) :
                new BaseResponse(300, responseMessage));
    }

    @PostMapping("/getUserRole")
    public String getUserRole(@RequestBody String userName){
        return userService.getUserRoleService(userName);
    }
}
