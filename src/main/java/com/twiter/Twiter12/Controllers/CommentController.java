package com.twiter.Twiter12.Controllers;

import com.twiter.Twiter12.Models.Comment;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class CommentController {

    @PostMapping("/comment/add")
    public void addComment(@RequestBody Comment comment) {

    }
}
