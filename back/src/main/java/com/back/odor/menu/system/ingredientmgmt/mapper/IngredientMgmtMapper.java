package com.back.odor.menu.system.ingredientmgmt.mapper;

import com.back.odor.menu.system.ingredientmgmt.vo.IngredientVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IngredientMgmtMapper {

    List<IngredientVO> getIngredientListAll(@Param("locale") String locale);
    List<IngredientVO> getIngredientList(@Param("vo") IngredientVO vo);

    Integer checkDuplication(@Param("vo") IngredientVO vo);
    void deleteIngredient(@Param("vo") IngredientVO vo);
    void updateIngredient(@Param("vo") IngredientVO vo);
    void insertIngredient(@Param("vo") IngredientVO vo);

}
