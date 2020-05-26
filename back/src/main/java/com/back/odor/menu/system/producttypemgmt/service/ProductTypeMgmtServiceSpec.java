package com.back.odor.menu.system.producttypemgmt.service;

import com.back.odor.menu.system.producttypemgmt.vo.ProductTypeVO;

import java.util.List;

public interface ProductTypeMgmtServiceSpec {

    List<ProductTypeVO> getProductTypeListAll();
    List<ProductTypeVO> getProductTypeList(ProductTypeVO vo);

    Integer checkDuplication(ProductTypeVO vo);
    void deleteProductType(ProductTypeVO vo);
    void updateProductType(ProductTypeVO vo);
    void insertProductType(ProductTypeVO vo);

}
