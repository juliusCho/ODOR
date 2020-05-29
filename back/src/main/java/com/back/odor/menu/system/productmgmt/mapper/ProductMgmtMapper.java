package com.back.odor.menu.system.productmgmt.mapper;

import com.back.odor.menu.system.productmgmt.vo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductMgmtMapper {

    List<ProductVO> getProductList(@Param("vo") ProductVO vo);
    List<ProductVO> getProductListAll();

    Integer checkDuplication(@Param("vo") ProductVO vo);
    void insertProduct(@Param("vo") ProductVO vo);
    void updateProduct(@Param("vo") ProductVO vo);

    void deleteAllBrandMapper(@Param("productKey") Long productKey);
    void deleteAllCreatorMapper(@Param("productKey") Long productKey);
    void deleteAllIngredientMapper(@Param("productKey") Long productKey);
    void deleteAllProductTypeMapper(@Param("productKey") Long productKey);
    void deleteProduct(@Param("productKey") Long productKey);

    List<ProductBrandMapperVO> getMappedBrandList(@Param("productKey") Long productKey);
    List<ProductCreatorMapperVO> getMappedCreatorList(@Param("productKey") Long productKey);
    List<ProductIngredientMapperVO> getMappedIngredientList(@Param("productKey") Long productKey);
    List<ProductProductTypeMapperVO> getMappedProductTypeList(@Param("productKey") Long productKey);

    void deleteBrandMapper(@Param("vo") ProductBrandMapperVO vo);
    void deleteCreatorMapper(@Param("vo") ProductCreatorMapperVO vo);
    void deleteIngredientMapper(@Param("vo") ProductIngredientMapperVO vo);
    void deleteProductTypeMapper(@Param("vo") ProductProductTypeMapperVO vo);

    void insertBrandMapping(@Param("vo") ProductBrandMapperVO vo);
    void insertCreatorMapping(@Param("vo") ProductCreatorMapperVO vo);
    void insertIngredientMapping(@Param("vo") ProductIngredientMapperVO vo);
    void insertProductTypeMapping(@Param("vo") ProductProductTypeMapperVO vo);

}
