package com.back.odor.menu.system.brandmgmt.service;

import com.back.odor.menu.system.brandmgmt.mapper.BrandMgmtMapper;
import com.back.odor.menu.system.brandmgmt.vo.BrandVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandMgmtService implements BrandMgmtServiceSpec {

    @Autowired
    private BrandMgmtMapper brandMgmtMapper;

    @Override
    public List<BrandVO> getBrandList(BrandVO vo) {
        return brandMgmtMapper.getBrandList(vo);
    }

}
