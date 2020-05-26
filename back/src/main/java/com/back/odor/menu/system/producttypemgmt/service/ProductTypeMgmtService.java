package com.back.odor.menu.system.producttypemgmt.service;

import com.back.odor.common.utils.LocaleUtil;
import com.back.odor.menu.system.producttypemgmt.mapper.ProductTypeMgmtMapper;
import com.back.odor.menu.system.producttypemgmt.vo.ProductTypeVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
public class ProductTypeMgmtService implements ProductTypeMgmtServiceSpec {

    @Autowired
    private ProductTypeMgmtMapper productTypeMgmtMapper;


    @Override
    public List<ProductTypeVO> getProductTypeListAll() {
        return productTypeMgmtMapper.getProductTypeListAll(LocaleUtil.getLocale());
    }

    @Override
    public List<ProductTypeVO> getProductTypeList(ProductTypeVO vo) {
        return productTypeMgmtMapper.getProductTypeList(vo);
    }

    @Override
    public Integer checkDuplication(ProductTypeVO vo) {
        return productTypeMgmtMapper.checkDuplication(vo);
    }

    @Override
    public void deleteProductType(ProductTypeVO vo) {
        productTypeMgmtMapper.deleteProductType(vo);
    }

    @Override
    public void updateProductType(ProductTypeVO vo) {
        productTypeMgmtMapper.updateProductType(vo);
    }

    @Override
    public void insertProductType(ProductTypeVO vo) {
        productTypeMgmtMapper.insertProductType(vo);
    }
}
