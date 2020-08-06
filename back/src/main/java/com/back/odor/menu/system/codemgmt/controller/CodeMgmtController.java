package com.back.odor.menu.system.codemgmt.controller;

import com.back.odor.menu.system.codemgmt.service.CodeMgmtService;
import com.back.odor.menu.system.codemgmt.vo.CodeGroupVO;
import com.back.odor.menu.system.codemgmt.vo.CodeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("odor/codegroups")
public class CodeMgmtController {

    @Autowired
    private CodeMgmtService codeMgmtService;

    @PostMapping("system")
    public ResponseEntity<List<CodeGroupVO>> getCodeGroupList(@RequestBody CodeGroupVO vo) {
        return ResponseEntity.ok(codeMgmtService.getCodeGroupList(vo));
    }

    @GetMapping("system")
    public ResponseEntity<List<CodeGroupVO>> getCodeGroupListAll() {
        return ResponseEntity.ok(codeMgmtService.getCodeGroupListAll());
    }

    @PostMapping("check/duplication/system")
    public ResponseEntity<Integer> checkDuplication(@RequestParam String codeGroupId) {
        return ResponseEntity.ok(codeMgmtService.checkDuplication(codeGroupId));
    }

    @PutMapping("insert/system")
    public ResponseEntity insertCodeGroup(@RequestBody CodeGroupVO vo) {
        codeMgmtService.insertCodeGroup(vo);
        return new ResponseEntity("success", HttpStatus.OK);
    }

    @PatchMapping("update/system")
    public ResponseEntity updateCodeGroup(@RequestBody CodeGroupVO vo) {
        codeMgmtService.updateCodeGroup(vo);
        return new ResponseEntity("success", HttpStatus.OK);
    }

    @PatchMapping("delete/system")
    public ResponseEntity deleteCodeGroup(@RequestBody CodeGroupVO vo) {
        codeMgmtService.deleteCodeGroup(vo);
        return new ResponseEntity("success", HttpStatus.OK);
    }

    @PostMapping("codes/system")
    public ResponseEntity<List<CodeVO>> getCodeListSystem(@RequestBody CodeVO vo) {
        return ResponseEntity.ok(codeMgmtService.getCodeListSystem(vo));
    }

    @PostMapping("codes/check/duplication/system")
    public ResponseEntity<Integer> checkDetailDuplication(@RequestBody CodeVO vo) {
        return ResponseEntity.ok(codeMgmtService.checkDetailDuplication(vo));
    }

    @PutMapping("codes/insert/system")
    public ResponseEntity insertCode(@RequestBody CodeVO vo) {
        codeMgmtService.insertCode(vo);
        return new ResponseEntity("success", HttpStatus.OK);
    }

    @PatchMapping("codes/update/system")
    public ResponseEntity updateCode(@RequestBody CodeVO vo) {
        codeMgmtService.updateCode(vo);
        return new ResponseEntity("success", HttpStatus.OK);
    }

    @PatchMapping("codes/delete/system")
    public ResponseEntity deleteCode(@RequestBody CodeVO vo) {
        codeMgmtService.deleteCode(vo);
        return new ResponseEntity("success", HttpStatus.OK);
    }

    @GetMapping("codes/forum/system")
    public ResponseEntity<List<CodeVO>> getForumCodeList() {
        return ResponseEntity.ok(codeMgmtService.getForumCodeList());
    }

}
