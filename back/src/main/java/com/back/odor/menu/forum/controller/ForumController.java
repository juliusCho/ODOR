package com.back.odor.menu.forum.controller;

import com.back.odor.menu.forum.service.ForumService;
import com.back.odor.menu.forum.vo.ArticleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("odor/articles")
public class ForumController {

    @Autowired
    private ForumService forumService;

    @PostMapping("api")
    public ResponseEntity<List<ArticleVO>> list(@RequestParam String forumType, @RequestBody ArticleVO vo) {
        switch (forumType) {
            case "review":
                return ResponseEntity.ok(forumService.reviewList(vo));
            case "photo":
                return null;
            default: // etc
                return null;
        }
    }

    @PostMapping("one/api")
    public ResponseEntity<ArticleVO> detail(@RequestParam String forumType, @RequestBody ArticleVO vo) {
        switch (forumType) {
            case "review":
                return ResponseEntity.ok(forumService.review(vo));
            case "photo":
                return null;
            default: // etc
                return null;
        }
    }

    @PutMapping("api")
    public ResponseEntity create(@RequestParam String forumType, @RequestBody ArticleVO vo) {
        switch (forumType) {
            case "review":
                forumService.createReview(vo);
                break;
            case "photo":
                break;
            default: // etc
                break;
        }
        return new ResponseEntity("success", HttpStatus.OK);
    }

    @PatchMapping("api")
    public ResponseEntity update(@RequestParam String forumType, @RequestBody ArticleVO vo) {
        switch (forumType) {
            case "review":
                forumService.updateReview(vo);
                break;
            case "photo":
                break;
            default: // etc
                break;
        }
        return new ResponseEntity("success", HttpStatus.OK);
    }

    @PatchMapping("delete/api")
    public ResponseEntity delete(@RequestParam String forumType, @RequestBody ArticleVO vo) {
        switch (forumType) {
            case "review":
                forumService.deleteReview(vo);
                break;
            case "photo":
                break;
            default: // etc
                break;
        }
        return new ResponseEntity("success", HttpStatus.OK);
    }

}
