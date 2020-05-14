package com.back.odor.menu.system.categorymgmt.service;

import com.back.odor.common.utils.LocaleUtil;
import com.back.odor.menu.system.categorymgmt.mapper.CategoryMgmtMapper;
import com.back.odor.menu.system.categorymgmt.vo.CategoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryMgmtService implements CategoryMgmtServiceSpec {

    @Autowired
    private CategoryMgmtMapper categoryMgmtMapper;


    @Override
    public List<CategoryVO> getCategoryListAll() {
        return categoryMgmtMapper.getCategoryListAll(LocaleUtil.getLocale());
    }

    @Override
    public List<CategoryVO> getCategoryList(CategoryVO vo) {
        return categoryMgmtMapper.getCategoryList(vo);
    }

    @Override
    public Integer checkDuplication(String categoryId) {
        return categoryMgmtMapper.checkDuplication(categoryId);
    }

    @Override
    public void insertCategory(CategoryVO vo) {
        categoryMgmtMapper.insertCategory(vo);
    }

    @Override
    public void updateCategory(CategoryVO vo) {
        categoryMgmtMapper.updateCategory(vo);
    }

    @Override
    public void deleteCategory(CategoryVO vo) {
        categoryMgmtMapper.deleteCategory(vo);
    }

}
