package com.back.odor.common.session.controller;

import com.back.odor.common.session.service.SessionService;
import com.back.odor.menu.system.usermgmt.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("rest")
@Slf4j
public class SessionController {

    @Autowired
    private SessionService sessionService;


    @PostMapping(value = "sessionCheck")
    public String sessionCheck(@RequestBody String sessionToken) {
        log.debug("Session Token: " + sessionToken);
        return "XX";
    }

    @PostMapping(value = "validateLogin")
    public ResponseEntity<String> validateLogin(@RequestBody UserVO user) {
        String result = sessionService.validateLogin(user);
        return ResponseEntity.ok(result);
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
