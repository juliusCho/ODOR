package com.back.odor.menu.system.forummgmt.controller;

import com.back.odor.menu.system.forummgmt.service.ForumMgmtService;
import com.back.odor.menu.system.forummgmt.vo.ForumVO;
import com.back.odor.menu.system.usermgmt.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("odor/forum")
@Slf4j
public class ForumMgmtController {

    @Autowired
    private ForumMgmtService forumMgmtService;


    @GetMapping("getForumList/rest")
    public ResponseEntity<List<ForumVO>> getForumList() {
        return ResponseEntity.ok(forumMgmtService.getForumList());
    }

    @PostMapping("getSystemList/system")
    public ResponseEntity<List<ForumVO>> getSystemList(
            @RequestBody UserVO user,
            HttpServletRequest req
    ) {
        return ResponseEntity.ok(forumMgmtService.getSystemList(user, req));
    }

    @GetMapping("getForumListAll/system")
    public ResponseEntity<List<ForumVO>> getForumListAll() {
        return ResponseEntity.ok(forumMgmtService.getForumListAll());
    }

    @PostMapping("getForumListSystem/system")
    public ResponseEntity<List<ForumVO>> getForumListSystem(@RequestBody ForumVO vo) {
        return ResponseEntity.ok(forumMgmtService.getForumListSystem(vo));
    }

    @PostMapping("checkDuplication/system")
    public ResponseEntity<Integer> checkDuplication(@RequestBody ForumVO vo) {
        return ResponseEntity.ok(forumMgmtService.checkDuplication(vo));
    }

    @PatchMapping("deleteForum/system")
    public ResponseEntity<Integer> deleteForum(@RequestBody ForumVO vo) {
        forumMgmtService.deleteForum(vo);
        return new ResponseEntity("success", HttpStatus.OK);
    }

    @PatchMapping("updateForum/system")
    public ResponseEntity<Integer> updateForum(@RequestBody ForumVO vo) {
        forumMgmtService.updateForum(vo);
        return new ResponseEntity("success", HttpStatus.OK);
    }

    @PutMapping("insertForum/system")
    public ResponseEntity<Integer> insertForum(@RequestBody ForumVO vo) {
        forumMgmtService.insertForum(vo);
        return new ResponseEntity("success", HttpStatus.OK);
    }

}
