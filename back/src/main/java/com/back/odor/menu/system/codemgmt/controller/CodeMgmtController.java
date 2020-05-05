package com.back.odor.menu.system.codemgmt.controller;

import com.back.odor.menu.system.codemgmt.service.CodeMgmtService;
import com.back.odor.menu.system.codemgmt.vo.CodeGroupVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
