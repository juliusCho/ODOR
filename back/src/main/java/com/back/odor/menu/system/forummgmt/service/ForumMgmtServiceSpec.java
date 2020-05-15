package com.back.odor.menu.system.forummgmt.service;

import com.back.odor.menu.system.forummgmt.vo.ForumVO;
import com.back.odor.menu.system.usermgmt.vo.UserVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ForumMgmtServiceSpec {

    List<ForumVO> getForumList();

    List<ForumVO> getSystemList(UserVO user, HttpServletRequest req);

    List<ForumVO> getForumListAll();
    List<ForumVO> getForumListSystem(ForumVO vo);

    Integer checkDuplication(ForumVO vo);
    void deleteForum(ForumVO vo);
    void updateForum(ForumVO vo);
    void insertForum(ForumVO vo);

}
