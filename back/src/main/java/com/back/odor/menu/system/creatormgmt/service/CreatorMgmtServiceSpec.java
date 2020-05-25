package com.back.odor.menu.system.creatormgmt.service;

import com.back.odor.menu.system.creatormgmt.vo.CreatorVO;

import java.util.List;

public interface CreatorMgmtServiceSpec {

    List<CreatorVO> getCreatorList(CreatorVO vo);
    List<CreatorVO> getCreatorListAll();

    void insertCreator(CreatorVO vo);
    void updateCreator(CreatorVO vo);
    void deleteCreator(CreatorVO vo);

}
