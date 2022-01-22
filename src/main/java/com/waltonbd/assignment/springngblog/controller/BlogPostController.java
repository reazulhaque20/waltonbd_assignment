package com.waltonbd.assignment.springngblog.controller;

import com.waltonbd.assignment.springngblog.dto.*;
import com.waltonbd.assignment.springngblog.model.Post;
import com.waltonbd.assignment.springngblog.security.PostService;
import com.waltonbd.assignment.springngblog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/blog")
public class BlogPostController {

    @Autowired
    BlogService blogService;

    @Autowired
    PostService postService;

    @PostMapping("/createblog")
    public ResponseEntity<BaseResponse> createPost(@RequestBody PostDto request) {
        String responseMessage = null;
        if(request.getUsername() == null || request.getUsername().equals("")){
            responseMessage = "User name can't be empty or null.";
        }
        if(request.getAuthToken() == null || request.getAuthToken().equals("")){
            responseMessage = "Auth token can't be empty or null.";
        }
        if(request.getTitle() == null || request.getTitle().equals("")){
            responseMessage = "Blog title can't be empty or null.";
        }
        if(request.getContent() == null || request.getContent().equals("")){
            responseMessage  = "Blog content can't be empty or null.";
        }
        return ResponseEntity.ok(responseMessage == null ?
                new BaseResponse(200, blogService.createNewPost(request)) :
                new BaseResponse(300, responseMessage));
    }

    @PostMapping("/getUserPost")
    public List<Post> getAllPost(@RequestBody PostCollectionRequest request){
        //Own list both approved and submitted
        //Other approved post
        if(request.userName == null || request.userName.equals("")){
            // TODO, what will be the return;
        }
        if(request.authToken == null || request.authToken.equals("")){
            // TODO, what will be the return;
        }
        return blogService.getAllPost(request);
    }

    @PostMapping("/getSubmitted")
    public ResponseEntity<SubmittedPostResponse> getAllSubmittedPost(@RequestBody UserRequest request){
        String responseMessage = null;
        if(request.adminUser == null || request.adminUser.equals("")){
            responseMessage = "Admin user field can't be empty or null.";
        }
        if(request.adminAuthToken == null || request.adminAuthToken.equals("")){
            responseMessage = "Auth token can't be empty or null.";
        }
        return ResponseEntity.ok(responseMessage == null ?
                new SubmittedPostResponse(200, "Success", blogService.getAllSubmittedPost("submit")) :
                new SubmittedPostResponse(300, responseMessage, null));
    }

    @PostMapping("/updatePostStatus")
    public String updatePostStatus(@RequestBody PostUpdateRequest request){
        if(request.adminUser == null || request.adminUser.equals("")){
            return "Admin user field can't be empty or null.";
        }
        if(request.adminAuthToken == null || request.adminAuthToken.equals("")){
            return "Auth token can't be empty or null.";
        }
        blogService.updatePostStatus(request);
        return null;
    }

    @PostMapping("/updatePostContent")
    public String updatePostContent(@RequestBody PostUpdateRequest request){
        //Admin or creator
        return null;
    }

    @PostMapping("/addComment")
    public String addComments(@RequestBody BlogCommentRequest request){
        try {
            blogService.saveComment(request);
            return "Successfully save comment.";
        } catch (Throwable h) {
            return "Unable to save comment.";
        }
    }

 /*   @GetMapping("/all")
    public ResponseEntity<List<PostDto>> showAllActivePosts() {
        return new ResponseEntity<>(postService.showAllActivePostService(), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<PostDto> getSinglePost(@PathVariable @RequestBody Long id) {
        return new ResponseEntity<>(postService.readSinglePost(id), HttpStatus.OK);
    }
    //TODO API for user submit
    //

    @PostMapping("/approved")
    public String approvedPostAdmin(){

    }

    @PostMapping("/published")
    public*/
}
