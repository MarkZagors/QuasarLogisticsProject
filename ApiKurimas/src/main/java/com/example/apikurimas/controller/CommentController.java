package com.example.apikurimas.controller;

import com.example.apikurimas.model.Checkpoint;
import com.example.apikurimas.model.Comment;
import com.example.apikurimas.model.Forum;
import com.example.apikurimas.repository.CheckpointRepository;
import com.example.apikurimas.repository.CommentRepository;
import com.example.apikurimas.repository.ForumRepository;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ForumRepository forumRepository;

    @JsonIgnore
    @GetMapping("/comments")
    @CrossOrigin
    public List<Comment> getAll() {
        return commentRepository.findAll();
    }

    @PostMapping(path="/comment/add/{id}")
    @CrossOrigin
    public @ResponseBody String addCommentToForum (@PathVariable(value = "id") Integer forumID, @RequestParam String commentBody) {
        Comment comment = new Comment(commentBody);
        commentRepository.save(comment);

        Forum forum = forumRepository.findById(forumID).isPresent() ? forumRepository.findById(forumID).get() : null;
        forum.addComment(comment);
        forumRepository.save(forum);
        return "Saved";
    }

    @PostMapping(path="/comment/reply/{commentID}")
    @CrossOrigin
    public @ResponseBody String addReplyToForum (@PathVariable(value = "commentID") Integer commentID, @RequestParam String commentBody) {
        Comment reply = new Comment(commentBody);
        commentRepository.save(reply);

        Comment comment = commentRepository.findById(commentID).isPresent() ? commentRepository.findById(commentID).get() : null;
        comment.addReply(reply);
        commentRepository.save(comment);
        return "Saved";
    }
}
