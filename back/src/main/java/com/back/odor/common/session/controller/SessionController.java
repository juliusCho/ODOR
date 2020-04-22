package com.back.odor.common.session.controller;

import com.back.odor.common.session.service.SessionService;
import com.back.odor.menu.system.usermgmt.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("rest")
@Slf4j
public class SessionController {

    @Autowired
    private SessionService sessionService;

    @GetMapping(value = "logout")
    public ResponseEntity logout(HttpServletRequest req) {
        HttpSession session = req.getSession();
        if (session != null) {
            session.invalidate();
        }
        return new ResponseEntity("success", HttpStatus.OK);
    }

    @PostMapping(value = "sessionCheck")
    public ResponseEntity<Boolean> sessionCheck(
            @RequestBody String sessionToken,
            HttpServletRequest req
    ) {
        Object obj = getSession(req);
        if (obj == null) {
            return ResponseEntity.ok(false);
        }
        if (!(obj instanceof UserVO)) {
            return ResponseEntity.ok(false);
        }
        UserVO session = (UserVO) obj;
        if (!sessionToken.equals(session.getUserId())) {
            return ResponseEntity.ok(false);
        }
        return ResponseEntity.ok(true);
    }

    @PostMapping(value = "validateLogin")
    public ResponseEntity<Object> validateLogin(@RequestBody UserVO user, HttpServletRequest req) {
        Object loginUser = sessionService.validateLogin(user);

        if (loginUser != null && loginUser instanceof UserVO) {
            addToSessionModel((UserVO) loginUser);
            HttpSession session = req.getSession();
            session.setAttribute("user", loginUser);
        }
        return ResponseEntity.ok(loginUser);
    }

    private Object getSession(HttpServletRequest req) {
        HttpSession session = req.getSession();
        return session.getAttribute("user");
    }

    @ModelAttribute("user")
    private UserVO addToSessionModel(UserVO user) {
        return user;
    }

    @RequestMapping("authorization-code")
    @ResponseBody
    public String authorizationCodeTest(@RequestParam("code") String code) {
        String curl = String.format("curl " +
                "-F \"grant_type=authorization_code\" " +
                "-F \"code=%s\" " +
                "-F \"scope=read\" " +
                "-F \"client-id=first-client\" " +
                "-F \"client_secret=noonewilleverguess\" " +
                "-F \"redirect_uri=http://localhost:8888/rest/authorization-code\" " +
                "\"http://first-client:noonewilleverguess@localhost:8888/oauth/token\""
                , code);
        return curl;
    }
}
