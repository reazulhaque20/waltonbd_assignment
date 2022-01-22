package com.waltonbd.assignment.springngblog.service;

import com.waltonbd.assignment.springngblog.dto.UserRequest;
import com.waltonbd.assignment.springngblog.model.User;
import com.waltonbd.assignment.springngblog.repository.UserRepository;
import com.waltonbd.assignment.springngblog.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    UserRepository userRepo;
    @Autowired
    AuthService authService;

    @Autowired
    JwtProvider jwtProvider;

    public boolean validateAdmin(String userName){
        return "admin".equalsIgnoreCase(userRepo.findByUserName(userName).get().getRoles());
    }

    public String getUserRoleService(String userName){
        User user = userRepo.findByUserName(userName).get();
        return user.getRoles();
    }

    public String updateUser(UserRequest request) {
        String response;
        if(jwtProvider.validateToken(request.adminAuthToken)){
            if(validateAdmin(request.adminUser)){
                try {
                    updateUserStatus(request.userInfo.userName, request.userInfo.status, request.userInfo.roles);
                    response = "Successfully update user status.";
                } catch (Throwable h) {
                    response = "Unable to update user status.";
                }
            } else {
                response = "Only admin can modify user.";
            }
        } else {
            response = "Invelid Auth Token";
        }
        return response;
    }

    private void updateUserStatus(String userName, String status, String roles) throws Exception{
        User user = userRepo.findByUserName(userName).get();
        if(!status.isEmpty())
            user.setStatus(status);
        if(!roles.isEmpty())
            user.setRoles(roles);
        userRepo.save(user);
    }

    public String createUser(UserRequest request){
        String response;
        if(jwtProvider.validateToken(request.adminAuthToken)){
            if(validateAdmin(request.adminUser)){
                try {
                    createUser(request.userInfo.userName, request.userInfo.email, request.userInfo.password);
                    response = "Successfully create user.";
                } catch (Throwable h) {
                    response = "Unable to create user.";
                }
            } else {
                response = "Only admin can create user.";
            }
        } else {
            response = "Invelid Auth Token";
        }
        return response;
    }

    private void createUser(String userName, String email, String password) throws Exception{
        User user = new User();
        user.setUserName(userName);
        user.setEmail(email);
        user.setPassword(authService.encodePassword(password));
        userRepo.save(user);
    }
}
