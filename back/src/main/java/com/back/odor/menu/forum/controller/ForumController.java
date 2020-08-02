package com.back.odor.menu.forum.controller;

import com.back.odor.menu.forum.service.ForumService;
import com.back.odor.menu.forum.vo.ArticleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("odor/article")
public class ForumController {

    @Autowired
    private ForumService forumService;

    @PostMapping("list/api")
    public ResponseEntity<List<ArticleVO>> list(@RequestBody ArticleVO vo) {
        return ResponseEntity.ok(forumService.articleList(vo));
    }

    @PostMapping("detail/api")
    public ResponseEntity<ArticleVO> detail(@RequestBody ArticleVO vo) {
        return ResponseEntity.ok(forumService.article(vo));
    }

    @PutMapping("create/api")
    public ResponseEntity create(@RequestBody ArticleVO vo) {
        forumService.createArticle(vo);
        return new ResponseEntity("success", HttpStatus.OK);
    }

    @PatchMapping("update/api")
    public ResponseEntity update(@RequestBody ArticleVO vo) {
        forumService.updateArticle(vo);
        return new ResponseEntity("success", HttpStatus.OK);
    }

    @PatchMapping("delete/api")
    public ResponseEntity delete(@RequestBody ArticleVO vo) {
        forumService.deleteArticle(vo);
        return new ResponseEntity("success", HttpStatus.OK);
    }

}
