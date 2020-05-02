package com.back.odor.menu.system.forummgmt.service;

import com.back.odor.common.etc.GlobalConst;
import com.back.odor.common.session.service.SessionService;
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
    public List<ForumVO> getForumList() {
        return forumMgmtMapper.getForumList();
    }

    @Override
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
                result.add(vo);
            }
        }
        return result;
    }
}