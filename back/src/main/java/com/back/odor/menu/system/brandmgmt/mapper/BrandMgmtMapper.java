package com.back.odor.menu.system.brandmgmt.mapper;

import com.back.odor.menu.system.brandmgmt.vo.BrandVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BrandMgmtMapper {

    List<BrandVO> getBrandList(@Param("vo") BrandVO vo);
    List<BrandVO> getBrandListAll();

    Integer checkDuplication(@Param("vo") BrandVO vo);
    void insertBrand(@Param("vo") BrandVO vo);
    void updateBrand(@Param("vo") BrandVO vo);
    void deleteBrand(@Param("vo") BrandVO vo);

}
