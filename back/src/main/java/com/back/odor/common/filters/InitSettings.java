package com.back.odor.common.filters;

import com.back.odor.common.utils.LocaleUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@Slf4j
public class InitSettings {

    @EventListener
    public void onApplicationLoaded(ContextRefreshedEvent event) {
        log.debug(event.toString());
        LocaleUtil.setLocale(Locale.getDefault().getCountry());
    }

}
