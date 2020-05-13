package com.back.odor.menu.system.messagemgmt.mapper;

import com.back.odor.menu.system.messagemgmt.vo.MessageVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MessageMgmtMapper {

    List<MessageVO> getMessageList(@Param("locale") String locale);

    List<MessageVO> getMessageListSearch(@Param("vo") MessageVO vo);

    void insertMessageGroup(@Param("vo") MessageVO vo);
    void insertMessage(@Param("vo") MessageVO vo);

    List<MessageVO> getMessageListSystem(@Param("vo") MessageVO vo);

    Integer checkDuplication(@Param("messageId") String messageId);
    void updateMessageGroup(@Param("vo") MessageVO vo);
    void updateMessage(@Param("vo") MessageVO vo);
    void deleteMessageGroup(@Param("vo") MessageVO vo);

}
