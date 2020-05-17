package com.back.odor.menu.system.forummgmt.service;

import com.back.odor.common.etc.GlobalConst;
import com.back.odor.common.session.service.SessionService;
import com.back.odor.common.utils.AuthUtil;
import com.back.odor.menu.system.forummgmt.mapper.ForumMgmtMapper;
import com.back.odor.menu.system.forummgmt.vo.ForumVO;
import com.back.odor.menu.system.usermgmt.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Slf4j
public class ForumMgmtService implements ForumMgmtServiceSpec {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private ForumMgmtMapper forumMgmtMapper;


    @Override
    public List<ForumVO> getForumList(UserVO user, HttpServletRequest req) {
        UserVO vo = AuthUtil.getCurrentUser();
        if (!sessionService.sessionCheck(user, req)) {
            vo = new UserVO("", "", "0");
        }
        return forumMgmtMapper.getForumList(vo.getMembershipKey());
    }

    @Override
    @Transactional
    public List<ForumVO> getSystemList(UserVO user, HttpServletRequest req) {
        List<ForumVO> result = new ArrayList<>();

        if (sessionService.sessionCheck(user, req)) {
            String[] systemMenu = GlobalConst.SYSTEM_MENU;
            int ii = systemMenu.length;
            for (int i = 0; i < ii; i++) {
                ForumVO vo = new ForumVO();
                vo.setForumKey((long) (10000 + i));
                vo.setCategoryId("SYSTEM");
                vo.setForumName(systemMenu[i]);
                vo.setForumTypeCode("ETC");
                vo.setReviewYn(false);
                vo.setUseYn(true);
                vo.setDisabled(false);
                result.add(vo);
            }
        }
        return result;
    }

    @Override
    public List<ForumVO> getForumListAll() {
        return forumMgmtMapper.getForumListAll();
    }

    @Override
    public List<ForumVO> getForumListSystem(ForumVO vo) {
        return forumMgmtMapper.getForumListSystem(vo);
    }

    @Override
    public Integer checkDuplication(ForumVO vo) {
        return forumMgmtMapper.checkDuplication(vo);
    }

    @Override
    public void deleteForum(ForumVO vo) {
        forumMgmtMapper.deleteForum(vo);
    }

    @Override
    public void updateForum(ForumVO vo) {
        forumMgmtMapper.updateForum(vo);
    }

    @Override
    public void insertForum(ForumVO vo) {
        forumMgmtMapper.insertForum(vo);
    }
}
