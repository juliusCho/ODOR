package com.back.odor.menu.system.brandmgmt.service;

import com.back.odor.menu.system.brandmgmt.vo.BrandVO;

import java.util.List;

public interface BrandMgmtServiceSpec {

    List<BrandVO> getBrandList(BrandVO vo);
    List<BrandVO> getBrandListAll();

    Integer checkDuplication(BrandVO vo);
    void insertBrand(BrandVO vo);
    void updateBrand(BrandVO vo);
    void deleteBrand(BrandVO vo);

}
