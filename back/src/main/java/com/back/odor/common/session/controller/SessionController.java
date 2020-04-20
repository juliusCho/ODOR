package com.back.odor.common.session.controller;

import com.back.odor.common.session.service.SessionService;
import com.back.odor.menu.system.usermgmt.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.session.Session;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import java.util.Enumeration;

@SessionAttributes("user")
@RestController
@RequestMapping("rest")
@Slf4j
public class SessionController {

    @Autowired
    private SessionService sessionService;

    @PostMapping(value = "sessionCheck")
    public ResponseEntity<String> sessionCheck(@RequestBody String sessionToken) {
        log.debug("Session Token: " + sessionToken);
        return ResponseEntity.ok("XX");
    }

    @PostMapping(value = "validateLogin")
    public ResponseEntity<UserVO> validateLogin(@RequestBody UserVO user) {
        log.debug("#$Q#####RGRGFGESD");

        UserVO loginUser = sessionService.validateLogin(user);
        if (loginUser != null) {
            addToSessionModel(loginUser);
            log.debug(getSession());
        }
        return ResponseEntity.ok(loginUser);
    }

    private String getSession() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

        return attr.getRequest().getSession().getAttribute("user").toString();
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
