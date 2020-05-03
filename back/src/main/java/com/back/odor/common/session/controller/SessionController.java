package com.back.odor.common.session.controller;

import com.back.odor.common.session.service.SessionService;
import com.back.odor.menu.system.usermgmt.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("odor")
@Slf4j
public class SessionController {

    @Autowired
    private SessionService sessionService;


    @GetMapping("logout/rest")
    public ResponseEntity logout(HttpServletRequest req) {
        HttpSession session = req.getSession();
        if (session != null) {
            session.invalidate();
        }
        return new ResponseEntity("success", HttpStatus.OK);
    }

    @PostMapping("sessionCheck/rest")
    public ResponseEntity<Boolean> sessionCheck(
            @RequestBody UserVO user,
            HttpServletRequest req
    ) {
        return ResponseEntity.ok(sessionService.sessionCheck(user, req));
    }

    @PostMapping("validateLogin/rest")
    public ResponseEntity<Object> validateLogin(
            @RequestBody UserVO user,
            HttpServletRequest req
    ) {
        Object loginUser = sessionService.validateLogin(user);

        if (loginUser != null && loginUser instanceof UserVO) {
            addToSessionModel((UserVO) loginUser);
            HttpSession session = req.getSession();
            session.setAttribute("user", loginUser);
        }
        return ResponseEntity.ok(loginUser);
    }

    @ModelAttribute("user")
    private UserVO addToSessionModel(UserVO user) {
        return user;
    }

    @PostMapping("invalid/rest")
    public void invalid() throws AuthenticationException {
        throw new AuthenticationException();
    }

}
