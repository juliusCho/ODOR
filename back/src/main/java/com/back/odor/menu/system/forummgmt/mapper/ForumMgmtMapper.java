package com.back.odor.menu.system.forummgmt.mapper;

import com.back.odor.menu.system.forummgmt.vo.ForumVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ForumMgmtMapper {

    List<ForumVO> getForumList();

    List<ForumVO> getForumListAll();
    List<ForumVO> getForumListSystem(@Param("vo") ForumVO vo);

    Integer checkDuplication(@Param("vo") ForumVO vo);
    void deleteForum(@Param("vo") ForumVO vo);
    void updateForum(@Param("vo") ForumVO vo);
    void insertForum(@Param("vo") ForumVO vo);

}
