package com.back.odor.menu.system.brandmgmt.mapper;

import com.back.odor.menu.system.brandmgmt.vo.BrandCategoryMapperVO;
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

    List<BrandCategoryMapperVO> getMappedCategoryList(@Param("brandKey") Long brandKey);
    void deleteMapping(@Param("brandKey") Long brandKey);
    void insertMapping(@Param("vo") BrandCategoryMapperVO vo);

}
