package com.back.odor.common;

import com.back.odor.common.etc.FileService;
import com.back.odor.common.utils.LocaleUtil;
import com.back.odor.menu.system.codemgmt.service.CodeMgmtService;
import com.back.odor.menu.system.messagemgmt.service.MessageMgmtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("odor/common")
@Slf4j
public class CommonController {

    @Autowired
    private MessageMgmtService messageMgmtService;

    @Autowired
    private CodeMgmtService codeMgmtService;

    @Autowired
    private FileService fileService;




    @PostMapping("locale/rest")
    public ResponseEntity setSystemLocale(@RequestParam String locale) {
        LocaleUtil.setLocale(locale);
        return new ResponseEntity("success", HttpStatus.OK);
    }

    @GetMapping("messages/rest")
    public ResponseEntity getMessageList() {
        return ResponseEntity.ok(messageMgmtService.getMessageList());
    }

    @GetMapping("codes/rest")
    public ResponseEntity getCodeList() {
        return ResponseEntity.ok(codeMgmtService.getCodeList());
    }




    @PostMapping("file/api")
    public ResponseEntity<String[]> fileUpload(
            @RequestPart("file") MultipartFile[] multipartFile,
            @RequestPart("type") String type,
            @RequestPart("subPath") String subPath
    ) {
        return ResponseEntity.ok(fileService.fileUpload(multipartFile, type, subPath));
    }

    @GetMapping(value = "image/rest", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public @ResponseBody byte[] getImage(@RequestParam String path) throws IOException {
        return fileService.displayImage(path);
    }

}
