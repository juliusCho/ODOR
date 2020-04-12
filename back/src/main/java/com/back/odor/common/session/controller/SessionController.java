package com.back.odor.common.session.controller;

import com.back.odor.common.session.service.SessionService;
import com.back.odor.menu.system.usermgmt.vo.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("rest")
public class SessionController {

    Logger logger = LoggerFactory.getLogger(SessionController.class);

    @Autowired
    private SessionService sessionService;

    @PostMapping(value = "validateLogin")
    public ResponseEntity<String> validateLogin(@RequestBody UserVO user) {
        String result = sessionService.validateLogin(user);
        logger.debug(result);
        return ResponseEntity.ok(result);
    }
}
