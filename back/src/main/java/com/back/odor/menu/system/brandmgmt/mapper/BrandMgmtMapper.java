package com.back.odor.menu.system.brandmgmt.mapper;

import com.back.odor.menu.system.brandmgmt.vo.BrandVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BrandMgmtMapper {

    List<BrandVO> getBrandList(@Param("vo") BrandVO vo);

}
