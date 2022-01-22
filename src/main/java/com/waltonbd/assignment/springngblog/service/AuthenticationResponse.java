package com.waltonbd.assignment.springngblog.service;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationResponse {
    private int status;
    private String message;
    private String username;
    private String role;
    private Long User_Id;
    private String authenticationToken;
}
