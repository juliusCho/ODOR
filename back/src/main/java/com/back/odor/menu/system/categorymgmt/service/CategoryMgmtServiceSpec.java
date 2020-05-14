package com.back.odor.menu.system.categorymgmt.service;

import com.back.odor.menu.system.categorymgmt.vo.CategoryVO;

import java.util.List;

public interface CategoryMgmtServiceSpec {

    List<CategoryVO> getCategoryListAll();

    List<CategoryVO> getCategoryList(CategoryVO vo);
    Integer checkDuplication(String categoryId);
    void insertCategory(CategoryVO vo);
    void updateCategory(CategoryVO vo);
    void deleteCategory(CategoryVO vo);

}
