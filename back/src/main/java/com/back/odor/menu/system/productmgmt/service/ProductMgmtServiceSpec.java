package com.back.odor.menu.system.productmgmt.service;

import com.back.odor.menu.system.productmgmt.vo.*;

import java.util.List;

public interface ProductMgmtServiceSpec {

    List<ProductVO> getProductList(ProductVO vo);
    List<ProductVO> getProductListAll();

    Integer checkDuplication(ProductVO vo);
    void insertProduct(ProductVO vo);
    void updateProduct(ProductVO vo);
    void deleteProduct(Long productKey);

    List<ProductBrandMapperVO> getMappedBrandList(Long productKey);
    List<ProductCreatorMapperVO> getMappedCreatorList(Long productKey);
    List<ProductIngredientMapperVO> getMappedIngredientList(Long productKey);
    List<ProductProductTypeMapperVO> getMappedProductTypeList(Long productKey);

    void saveBrandMapping(List<ProductBrandMapperVO> list);
    void saveCreatorMapping(List<ProductCreatorMapperVO> list);
    void saveIngredientMapping(List<ProductIngredientMapperVO> list);
    void saveProductTypeMapping(List<ProductProductTypeMapperVO> list);

}
