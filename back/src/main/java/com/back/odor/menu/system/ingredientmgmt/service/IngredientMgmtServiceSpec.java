package com.back.odor.menu.system.ingredientmgmt.service;

import com.back.odor.menu.system.ingredientmgmt.vo.IngredientVO;

import java.util.List;

public interface IngredientMgmtServiceSpec {

    List<IngredientVO> getIngredientListAll();
    List<IngredientVO> getIngredientList(IngredientVO vo);

    Integer checkDuplication(IngredientVO vo);
    void deleteIngredient(IngredientVO vo);
    void updateIngredient(IngredientVO vo);
    void insertIngredient(IngredientVO vo);

}
