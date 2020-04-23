package com.back.odor.menu.system.usermgmt.controller;

import com.back.odor.menu.system.usermgmt.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

// **
// * 스프링4부터 지원하는 어노테이션. @Controller와 동일하지만 리턴하는 값이 단순한 문자열이라면
// * 해당 문자열과 동일한 이름의 jsp/html 등을 호출하지 않고
// * 순수하게 해당 문자 값을 리턴 시키도록 해준다.
// *
// * 자바 객체를 front로 보낼때는 자동으로 JSON포멧으로 변환시켜준다
// **
@RestController
@RequestMapping("api")
@Slf4j
public class UserMgmtController {
}
