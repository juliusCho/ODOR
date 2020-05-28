package com.back.odor.menu.system.ingredientmgmt.service;

import com.back.odor.common.utils.LocaleUtil;
import com.back.odor.menu.system.ingredientmgmt.mapper.IngredientMgmtMapper;
import com.back.odor.menu.system.ingredientmgmt.vo.IngredientVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
public class IngredientMgmtService implements IngredientMgmtServiceSpec {

    @Autowired
    private IngredientMgmtMapper ingredientMgmtMapper;


    @Override
    public List<IngredientVO> getIngredientListAll() {
        return ingredientMgmtMapper.getIngredientListAll(LocaleUtil.getLocale());
    }

    @Override
    public List<IngredientVO> getIngredientList(IngredientVO vo) {
        return ingredientMgmtMapper.getIngredientList(vo);
    }

    @Override
    public Integer checkDuplication(IngredientVO vo) {
        return ingredientMgmtMapper.checkDuplication(vo);
    }

    @Override
    public void deleteIngredient(IngredientVO vo) {
        ingredientMgmtMapper.deleteIngredient(vo);
    }

    @Override
    public void updateIngredient(IngredientVO vo) {
        ingredientMgmtMapper.updateIngredient(vo);
    }

    @Override
    public void insertIngredient(IngredientVO vo) {
        ingredientMgmtMapper.insertIngredient(vo);
    }
}
