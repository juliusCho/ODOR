package com.back.odor.menu.system.usermgmt.controller;

import com.back.odor.common.utils.AuthUtil;
import com.back.odor.menu.system.usermgmt.service.UserMgmtService;
import com.back.odor.menu.system.usermgmt.vo.BlockedUserVO;
import com.back.odor.menu.system.usermgmt.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// **
// * 스프링4부터 지원하는 어노테이션. @Controller와 동일하지만 리턴하는 값이 단순한 문자열이라면
// * 해당 문자열과 동일한 이름의 jsp/html 등을 호출하지 않고
// * 순수하게 해당 문자 값을 리턴 시키도록 해준다.
// *
// * 자바 객체를 front로 보낼때는 자동으로 JSON포멧으로 변환시켜준다
// **
@RestController
@RequestMapping("odor/users")
@Slf4j
public class UserMgmtController {

    @Autowired
    private UserMgmtService userMgmtService;

    @GetMapping("system")
    public ResponseEntity<List<UserVO>> getUserListAll() {
        return ResponseEntity.ok(userMgmtService.getUserListAll());
    }

    @PostMapping("system")
    public ResponseEntity<List<UserVO>> getUserList(@RequestBody UserVO vo) {
        return ResponseEntity.ok(userMgmtService.getUserList(vo));
    }

    @PostMapping("check/duplication/system")
    public ResponseEntity<Integer> checkDuplication(@RequestBody UserVO vo) {
        return ResponseEntity.ok(userMgmtService.checkDuplication(vo));
    }

    @PostMapping("check/email/duplication/system")
    public ResponseEntity<Integer> checkEmailDuplication(@RequestBody UserVO vo) {
        return ResponseEntity.ok(userMgmtService.checkEmailDuplication(vo));
    }

    @PutMapping("insert/system")
    public ResponseEntity insertUser(@RequestBody UserVO vo) {
        userMgmtService.insertUser(vo);
        return new ResponseEntity("success", HttpStatus.OK);
    }

    @PatchMapping("update/system")
    public ResponseEntity updateUser(@RequestBody UserVO vo) {
        userMgmtService.updateUser(vo);
        return new ResponseEntity("success", HttpStatus.OK);
    }

    @PatchMapping("delete/system")
    public ResponseEntity deleteUser(@RequestBody UserVO vo) {
        userMgmtService.deleteUser(vo);
        return new ResponseEntity("success", HttpStatus.OK);
    }

    @DeleteMapping("block/cancel/system")
    public ResponseEntity unblockUser(
            @RequestParam String userId,
            @RequestParam String email
    ) {
        userMgmtService.unblockUser(userId, email);
        return new ResponseEntity("success", HttpStatus.OK);
    }

    @PutMapping("block/execute/system")
    public ResponseEntity blockUser(@RequestBody BlockedUserVO vo) {
        userMgmtService.blockUser(vo);
        return new ResponseEntity("success", HttpStatus.OK);
    }

    @PostMapping("block/system")
    public ResponseEntity<BlockedUserVO> getBlockInfo(@RequestBody BlockedUserVO vo) {
        return ResponseEntity.ok(userMgmtService.getBlockInfo(vo));
    }

    @PatchMapping("photo/update/api")
    public ResponseEntity uploadPhoto(@RequestBody UserVO vo) {
        userMgmtService.uploadPhoto(vo);
        return new ResponseEntity("success", HttpStatus.OK);
    }

}
