package com.back.odor.menu.system.messagemgmt.controller;

import com.back.odor.menu.system.messagemgmt.service.MessageMgmtService;
import com.back.odor.menu.system.messagemgmt.vo.MessageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("odor/msg")
public class MessageMgmtController {

    @Autowired
    private MessageMgmtService messageMgmtService;

    @PostMapping("getMessageList/system")
    public ResponseEntity<List<MessageVO>> getMessageList(@RequestBody MessageVO vo) {
        return ResponseEntity.ok(messageMgmtService.getMessageList(vo));
    }

    @PutMapping("insertMessage/system")
    public ResponseEntity insertMessage(@RequestBody List<MessageVO> list) {
        messageMgmtService.insertMessage(list);
        return new ResponseEntity("success", HttpStatus.OK);
    }

    @PostMapping("getMessageListSystem/system")
    public ResponseEntity<List<MessageVO>> getMessageListSystem(@RequestBody MessageVO vo) {
        return ResponseEntity.ok(messageMgmtService.getMessageListSystem(vo));
    }

    @PostMapping("checkDuplication/system")
    public ResponseEntity<Integer> checkDuplication(@RequestBody MessageVO vo) {
        return ResponseEntity.ok(messageMgmtService.checkDuplication(vo));
    }

    @PatchMapping("updateMessage/system")
    public ResponseEntity updateMessage(@RequestBody List<MessageVO> list) {
        messageMgmtService.updateMessage(list);
        return new ResponseEntity("success", HttpStatus.OK);
    }

    @PatchMapping("deleteMessageGroup/system")
    public ResponseEntity deleteMessageGroup(@RequestBody MessageVO vo) {
        messageMgmtService.deleteMessageGroup(vo);
        return new ResponseEntity("success", HttpStatus.OK);
    }

}
