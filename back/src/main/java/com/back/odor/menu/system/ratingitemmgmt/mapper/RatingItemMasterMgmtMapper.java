package com.back.odor.menu.system.ratingitemmgmt.mapper;

import com.back.odor.menu.system.ratingitemmgmt.vo.RatingItemMasterVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RatingItemMasterMgmtMapper {

    List<RatingItemMasterVO> getRatingItemMasterList(@Param("vo") RatingItemMasterVO vo);

    Integer checkDuplication(@Param("vo") RatingItemMasterVO vo);
    void deleteRatingItemMaster(@Param("vo") RatingItemMasterVO vo);
    void updateRatingItemMaster(@Param("vo") RatingItemMasterVO vo);
    void insertRatingItemMaster(@Param("vo") RatingItemMasterVO vo);

}
