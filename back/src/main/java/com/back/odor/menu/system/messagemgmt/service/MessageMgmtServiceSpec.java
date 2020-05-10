package com.back.odor.menu.system.messagemgmt.service;

import com.back.odor.menu.system.messagemgmt.vo.MessageVO;

import java.util.List;

public interface MessageMgmtServiceSpec {

    List<MessageVO> getMessageList();

    List<MessageVO> getMessageList(MessageVO vo);

    void insertMessage(List<MessageVO> list);

}
