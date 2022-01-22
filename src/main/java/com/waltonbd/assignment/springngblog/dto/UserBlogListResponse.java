package com.waltonbd.assignment.springngblog.dto;

import java.util.List;

public class UserBlogListResponse extends BaseResponse{
//    public List<Post>

    public static class Blog {
        public int blogId;
        public String userName;
        public String title;
        public String content;
        public List<Comment> comments;
    }

    public static class Comment{
        public int commentId;
//        public
    }
}
