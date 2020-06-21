package com.back.odor.menu.system.ratingitemmgmt.service;

import com.back.odor.menu.system.ratingitemmgmt.vo.RatingItemMasterVO;
import com.back.odor.menu.system.usermgmt.vo.UserVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface RatingItemMasterMgmtServiceSpec {

    List<RatingItemMasterVO> getRatingItemMasterList(RatingItemMasterVO vo);

    Integer checkDuplication(RatingItemMasterVO vo);
    void deleteRatingItemMaster(RatingItemMasterVO vo);
    void updateRatingItemMaster(RatingItemMasterVO vo);
    void insertRatingItemMaster(RatingItemMasterVO vo);

}
