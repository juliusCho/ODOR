package com.back.odor.common;

import com.back.odor.common.utils.LocaleUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rest/common")
@Slf4j
public class CommonController {

    @PostMapping("setSystemLocale")
    public ResponseEntity setSystemLocale(@RequestBody String locale) {
        locale = locale.replaceAll("=", "");
        LocaleUtil.setLocale(locale);
        return new ResponseEntity("success", HttpStatus.OK);
    }

}
