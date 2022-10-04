package com.waltonbd.assignment.springngblog.dto;

public class UserRequest {
    public String adminUser;
    public String adminAuthToken;
    public UserInfo userInfo;

    public static class UserInfo {
        public String userName;
        public String password;
        public String email;
        public String status;
        public String roles;
    }
}
