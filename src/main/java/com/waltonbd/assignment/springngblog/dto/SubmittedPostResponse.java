package com.waltonbd.assignment.springngblog.dto;

import com.waltonbd.assignment.springngblog.model.Post;

import java.util.List;

public class SubmittedPostResponse extends BaseResponse {
    List<Post> submittedPostList;

    public SubmittedPostResponse(int status, String message, List<Post> submittedPostList) {
        super(status, message);
        this.submittedPostList = submittedPostList;
    }
}
