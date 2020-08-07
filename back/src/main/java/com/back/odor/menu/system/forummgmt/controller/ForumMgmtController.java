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
@RequestMapping("odor/forums")
@Slf4j
public class ForumMgmtController {

    @Autowired
    private ForumMgmtService forumMgmtService;


    @PostMapping("rest")
    public ResponseEntity<List<ForumVO>> getForumList(
            @RequestBody UserVO user,
            HttpServletRequest req
    ) {
        return ResponseEntity.ok(forumMgmtService.getForumList(user, req));
    }

    @PostMapping("systems/system")
    public ResponseEntity<List<ForumVO>> getSystemList(
            @RequestBody UserVO user,
            HttpServletRequest req
    ) {
        return ResponseEntity.ok(forumMgmtService.getSystemList(user, req));
    }

    @GetMapping("system")
    public ResponseEntity<List<ForumVO>> getForumListAll() {
        return ResponseEntity.ok(forumMgmtService.getForumListAll());
    }

    @PostMapping("system")
    public ResponseEntity<List<ForumVO>> getForumListSystem(@RequestBody ForumVO vo) {
        return ResponseEntity.ok(forumMgmtService.getForumListSystem(vo));
    }

    @PostMapping("check/duplication/system")
    public ResponseEntity<Integer> checkDuplication(@RequestBody ForumVO vo) {
        return ResponseEntity.ok(forumMgmtService.checkDuplication(vo));
    }

    @PatchMapping("delete/system")
    public ResponseEntity<Integer> deleteForum(@RequestBody ForumVO vo) {
        forumMgmtService.deleteForum(vo);
        return new ResponseEntity("success", HttpStatus.OK);
    }

    @PatchMapping("system")
    public ResponseEntity<Integer> updateForum(@RequestBody ForumVO vo) {
        forumMgmtService.updateForum(vo);
        return new ResponseEntity("success", HttpStatus.OK);
    }

    @PutMapping("system")
    public ResponseEntity<Integer> insertForum(@RequestBody ForumVO vo) {
        forumMgmtService.insertForum(vo);
        return new ResponseEntity("success", HttpStatus.OK);
    }

}
