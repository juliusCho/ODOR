package com.back.odor.common;

import com.back.odor.common.utils.LocaleUtil;
import com.back.odor.menu.system.codemgmt.service.CodeMgmtService;
import com.back.odor.menu.system.messagemgmt.service.MessageMgmtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("odor/common")
@Slf4j
public class CommonController {

    @Autowired
    private MessageMgmtService messageMgmtService;

    @Autowired
    private CodeMgmtService codeMgmtService;

    @PostMapping("setSystemLocale/rest")
    public ResponseEntity setSystemLocale(@RequestParam String locale) {
//        locale = locale.replaceAll("=", "");
        LocaleUtil.setLocale(locale);
        return new ResponseEntity("success", HttpStatus.OK);
    }

    @PostMapping("getMessageList/rest")
    public ResponseEntity getMessageList() {
        return ResponseEntity.ok(messageMgmtService.getMessageList());
    }

    @PostMapping("getCodeList/rest")
    public ResponseEntity getCodeList() {
        return ResponseEntity.ok(codeMgmtService.getCodeList());
    }

}
