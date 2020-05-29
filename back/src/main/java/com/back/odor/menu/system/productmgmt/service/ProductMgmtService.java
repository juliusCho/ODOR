package com.back.odor.menu.system.productmgmt.service;

import com.back.odor.menu.system.productmgmt.mapper.ProductMgmtMapper;
import com.back.odor.menu.system.productmgmt.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductMgmtService implements ProductMgmtServiceSpec {

    @Autowired
    private ProductMgmtMapper productMgmtMapper;


    @Override
    public List<ProductVO> getProductList(ProductVO vo) {
        return productMgmtMapper.getProductList(vo);
    }

    @Override
    public List<ProductVO> getProductListAll() {
        return productMgmtMapper.getProductListAll();
    }

    @Override
    public Integer checkDuplication(ProductVO vo) {
        return productMgmtMapper.checkDuplication(vo);
    }

    @Override
    public void insertProduct(ProductVO vo) {
        productMgmtMapper.insertProduct(vo);
    }

    @Override
    public void updateProduct(ProductVO vo) {
        productMgmtMapper.updateProduct(vo);
    }

    @Override
    @Transactional
    public void deleteProduct(Long productKey) {

        productMgmtMapper.deleteAllBrandMapper(productKey);
        productMgmtMapper.deleteAllCreatorMapper(productKey);
        productMgmtMapper.deleteAllIngredientMapper(productKey);
        productMgmtMapper.deleteAllProductTypeMapper(productKey);

        productMgmtMapper.deleteProduct(productKey);
    }

    @Override
    public List<ProductBrandMapperVO> getMappedBrandList(Long productKey) {
        return productMgmtMapper.getMappedBrandList(productKey);
    }

    @Override
    public List<ProductCreatorMapperVO> getMappedCreatorList(Long productKey) {
        return productMgmtMapper.getMappedCreatorList(productKey);
    }

    @Override
    public List<ProductIngredientMapperVO> getMappedIngredientList(Long productKey) {
        return productMgmtMapper.getMappedIngredientList(productKey);
    }

    @Override
    public List<ProductProductTypeMapperVO> getMappedProductTypeList(Long productKey) {
        return productMgmtMapper.getMappedProductTypeList(productKey);
    }

    @Override
    @Transactional
    public void saveBrandMapping(List<ProductBrandMapperVO> list) {
        productMgmtMapper.deleteBrandMapper(list.get(0));

        for (ProductBrandMapperVO vo : list) {
            productMgmtMapper.insertBrandMapping(vo);
        }
    }

    @Override
    @Transactional
    public void saveCreatorMapping(List<ProductCreatorMapperVO> list) {
        productMgmtMapper.deleteCreatorMapper(list.get(0));

        for (ProductCreatorMapperVO vo : list) {
            productMgmtMapper.insertCreatorMapping(vo);
        }
    }

    @Override
    @Transactional
    public void saveIngredientMapping(List<ProductIngredientMapperVO> list) {
        productMgmtMapper.deleteIngredientMapper(list.get(0));

        for (ProductIngredientMapperVO vo : list) {
            productMgmtMapper.insertIngredientMapping(vo);
        }
    }

    @Override
    @Transactional
    public void saveProductTypeMapping(List<ProductProductTypeMapperVO> list) {
        productMgmtMapper.deleteProductTypeMapper(list.get(0));

        for (ProductProductTypeMapperVO vo : list) {
            productMgmtMapper.insertProductTypeMapping(vo);
        }
    }

}
