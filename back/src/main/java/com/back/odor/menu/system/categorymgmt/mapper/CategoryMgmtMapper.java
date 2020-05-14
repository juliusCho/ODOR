package com.back.odor.menu.system.categorymgmt.mapper;

import com.back.odor.menu.system.categorymgmt.vo.CategoryVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CategoryMgmtMapper {

    List<CategoryVO> getCategoryListAll(@Param("locale") String locale);

    List<CategoryVO> getCategoryList(@Param("vo") CategoryVO vo);
    Integer checkDuplication(@Param("categoryId") String categoryId);
    void insertCategory(@Param("vo") CategoryVO vo);
    void updateCategory(@Param("vo") CategoryVO vo);
    void deleteCategory(@Param("vo") CategoryVO vo);

}
