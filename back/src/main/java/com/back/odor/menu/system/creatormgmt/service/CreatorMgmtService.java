package com.back.odor.menu.system.creatormgmt.service;

import com.back.odor.menu.system.creatormgmt.mapper.CreatorMgmtMapper;
import com.back.odor.menu.system.creatormgmt.vo.CreatorVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreatorMgmtService implements CreatorMgmtServiceSpec {

    @Autowired
    private CreatorMgmtMapper creatorMgmtMapper;

    @Override
    public List<CreatorVO> getCreatorList(CreatorVO vo) {
        return creatorMgmtMapper.getCreatorList(vo);
    }

    @Override
    public List<CreatorVO> getCreatorListAll() {
        return creatorMgmtMapper.getCreatorListAll();
    }

    @Override
    public void insertCreator(CreatorVO vo) {
        creatorMgmtMapper.insertCreator(vo);
    }

    @Override
    public void updateCreator(CreatorVO vo) {
        creatorMgmtMapper.updateCreator(vo);
    }

    @Override
    public void deleteCreator(CreatorVO vo) {
        creatorMgmtMapper.deleteCreator(vo);
    }

}
