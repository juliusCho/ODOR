package com.back.odor.menu.system.brandmgmt.service;

import com.back.odor.menu.system.brandmgmt.vo.BrandVO;

import java.util.List;

public interface BrandMgmtServiceSpec {

    List<BrandVO> getBrandList(BrandVO vo);

}
