package com.back.odor.menu.system.forummgmt.controller;

import com.back.odor.menu.system.forummgmt.service.ForumMgmtService;
import com.back.odor.menu.system.forummgmt.vo.ForumVO;
import com.back.odor.menu.system.usermgmt.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("api/forum")
public class ForumMgmtController {

    @Autowired
    private ForumMgmtService forumMgmtService;


    @GetMapping("getForumList")
    public ResponseEntity<List<ForumVO>> getForumList() {
        return ResponseEntity.ok(forumMgmtService.getForumList());
    }

    @PostMapping("getSystemList")
    public ResponseEntity<List<ForumVO>> getSystemList(
            @RequestBody UserVO user,
            HttpServletRequest req
    ) {
        return ResponseEntity.ok(forumMgmtService.getSystemList(user, req));
    }

}
