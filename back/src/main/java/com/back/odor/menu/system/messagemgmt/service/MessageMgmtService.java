package com.back.odor.menu.system.messagemgmt.service;

import com.back.odor.common.utils.LocaleUtil;
import com.back.odor.menu.system.messagemgmt.mapper.MessageMgmtMapper;
import com.back.odor.menu.system.messagemgmt.vo.MessageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MessageMgmtService implements MessageMgmtServiceSpec {

    @Autowired
    private MessageMgmtMapper messageMgmtMapper;

    @Override
    public List<MessageVO> getMessageList() {
        return messageMgmtMapper.getMessageList(LocaleUtil.getLocale());
    }
}
