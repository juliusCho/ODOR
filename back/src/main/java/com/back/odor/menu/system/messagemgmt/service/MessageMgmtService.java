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

    @Override
    public List<MessageVO> getMessageList(MessageVO vo) {
        return messageMgmtMapper.getMessageListSearch(vo);
    }

    @Override
    @Transactional
    public void insertMessage(List<MessageVO> list) {
        messageMgmtMapper.insertMessageGroup(list.get(0));

        for (MessageVO vo : list) {
            messageMgmtMapper.insertMessage(vo);
        }
    }

    @Override
    public List<MessageVO> getMessageListSystem(MessageVO vo) {
        return messageMgmtMapper.getMessageListSystem(vo);
    }

    @Override
    public Integer checkDuplication(MessageVO vo) {
        return messageMgmtMapper.checkDuplication(vo.getMessageId());
    }

    @Override
    @Transactional
    public void updateMessage(List<MessageVO> list) {
        messageMgmtMapper.updateMessageGroup(list.get(0));
        messageMgmtMapper.updateMessage(list.get(0));
        messageMgmtMapper.updateMessage(list.get(1));
    }

    @Override
    public void deleteMessageGroup(MessageVO vo) {
        messageMgmtMapper.deleteMessageGroup(vo);
    }
}
