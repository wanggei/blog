package com.example.blog.controller;

import com.example.blog.model.Comment;
import com.example.blog.service.BlogService;
import com.example.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class CommentController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private CommentService commentService;
    @Value("${comment.avator}")
    private String avator;

    @GetMapping(value = "/comments/{blogid}")
    public String comments(@PathVariable Integer blogid, Model model){
        List<Comment> allComment =commentService.getAllComment(blogid);
        System.out.println();
        model.addAttribute("conmmentsList",allComment);
        return "blog :: commentList";
    }
    @PostMapping("/comments")
   public String post(Comment comment){
        int blogid=comment.getBlog().getId();
        comment.setBlog( blogService.getOneById(blogid));
        comment.setAvator(avator);
        commentService.addComment(comment);
        return "redirect:/comments/" + blogid;
   }
}
