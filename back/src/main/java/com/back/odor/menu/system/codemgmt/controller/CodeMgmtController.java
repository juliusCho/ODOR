package com.back.odor.menu.system.codemgmt.controller;

import com.back.odor.menu.system.codemgmt.service.CodeMgmtService;
import com.back.odor.menu.system.codemgmt.vo.CodeGroupVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("odor/code")
public class CodeMgmtController {

    @Autowired
    private CodeMgmtService codeMgmtService;

    @PostMapping("getCodeGroupList/system")
    public ResponseEntity<List<CodeGroupVO>> getCodeGroupList(@RequestBody CodeGroupVO vo) {
        return ResponseEntity.ok(codeMgmtService.getCodeGroupList(vo));
    }

    @PostMapping("checkDuplication/system")
    public ResponseEntity<Integer> checkDuplication(@RequestParam String codeGroupId) {
        return ResponseEntity.ok(codeMgmtService.checkDuplication(codeGroupId));
    }

    @PutMapping("insertCodeGroup/system")
    public ResponseEntity insertCodeGroup(@RequestBody CodeGroupVO vo) {
        codeMgmtService.insertCodeGroup(vo);
        return new ResponseEntity("success", HttpStatus.OK);
    }

    @PatchMapping("updateCodeGroup/system")
    public ResponseEntity updateCodeGroup(@RequestBody CodeGroupVO vo) {
        codeMgmtService.updateCodeGroup(vo);
        return new ResponseEntity("success", HttpStatus.OK);
    }

    @PatchMapping("deleteCodeGroup/system")
    public ResponseEntity deleteCodeGroup(@RequestBody CodeGroupVO vo) {
        codeMgmtService.deleteCodeGroup(vo);
        return new ResponseEntity("success", HttpStatus.OK);
    }

}
