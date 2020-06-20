package com.back.odor.menu.system.ratingitemmgmt.service;

import com.back.odor.common.etc.GlobalConst;
import com.back.odor.common.session.service.SessionService;
import com.back.odor.common.utils.AuthUtil;
import com.back.odor.menu.system.ratingitemmgmt.mapper.RatingItemMasterMgmtMapper;
import com.back.odor.menu.system.ratingitemmgmt.vo.RatingItemMasterVO;
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
public class RatingItemMasterMgmtService implements RatingItemMasterMgmtServiceSpec {

    @Autowired
    private RatingItemMasterMgmtMapper ratingItemMasterMgmtMapper;


    @Override
    public List<RatingItemMasterVO> getRatingItemMasterList(RatingItemMasterVO vo) {
        return ratingItemMasterMgmtMapper.getRatingItemMasterList(vo);
    }

    @Override
    public Integer checkDuplication(RatingItemMasterVO vo) {
        return ratingItemMasterMgmtMapper.checkDuplication(vo);
    }

    @Override
    public void deleteRatingItemMaster(RatingItemMasterVO vo) {
        ratingItemMasterMgmtMapper.deleteRatingItemMaster(vo);
    }

    @Override
    public void updateRatingItemMaster(RatingItemMasterVO vo) {
        ratingItemMasterMgmtMapper.updateRatingItemMaster(vo);
    }

    @Override
    public void insertRatingItemMaster(RatingItemMasterVO vo) {
        ratingItemMasterMgmtMapper.insertRatingItemMaster(vo);
    }
}
