package com.back.odor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LoggingRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.trace("완전 하찮은 서버 정보");
        log.debug("하찮은 서버 정보");
        log.info("볼만한 서버 정보");
        log.warn("주의사항");
        log.error("오류");
    }
}
