package com.back.odor.menu.system.forummgmt.mapper;

import com.back.odor.menu.system.forummgmt.vo.ForumVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ForumMgmtMapper {

    List<ForumVO> getForumList();

}
