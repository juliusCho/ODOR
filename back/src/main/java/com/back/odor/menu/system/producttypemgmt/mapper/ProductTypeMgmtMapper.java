package com.back.odor.menu.system.producttypemgmt.mapper;

import com.back.odor.menu.system.producttypemgmt.vo.ProductTypeVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductTypeMgmtMapper {

    List<ProductTypeVO> getProductTypeListAll(@Param("locale") String locale);
    List<ProductTypeVO> getProductTypeList(@Param("vo") ProductTypeVO vo);

    Integer checkDuplication(@Param("vo") ProductTypeVO vo);
    void deleteProductType(@Param("vo") ProductTypeVO vo);
    void updateProductType(@Param("vo") ProductTypeVO vo);
    void insertProductType(@Param("vo") ProductTypeVO vo);

}
