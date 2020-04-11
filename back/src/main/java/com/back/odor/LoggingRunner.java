package com.back.odor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

@Service
public class LoggingRunner implements ApplicationRunner {

    private Logger logger = LoggerFactory.getLogger(LoggingRunner.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.trace("완전 하찮은 서버 정보");
        logger.debug("하찮은 서버 정보");
        logger.info("볼만한 서버 정보");
        logger.warn("주의사항");
        logger.error("오류");
    }
}
