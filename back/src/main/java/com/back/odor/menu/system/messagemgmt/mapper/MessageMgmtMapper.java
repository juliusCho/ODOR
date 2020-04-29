package com.back.odor.menu.system.messagemgmt.mapper;

import com.back.odor.menu.system.messagemgmt.vo.MessageVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MessageMgmtMapper {

    List<MessageVO> getMessageList(@Param("locale") String locale);

}
