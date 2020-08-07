package com.back.odor.menu.system.ingredientmgmt.controller;

import com.back.odor.menu.system.ingredientmgmt.service.IngredientMgmtService;
import com.back.odor.menu.system.ingredientmgmt.vo.IngredientVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("odor/ingredients")
@Slf4j
public class IngredientMgmtController {

    @Autowired
    private IngredientMgmtService ingredientMgmtService;


    @GetMapping("system")
    public ResponseEntity<List<IngredientVO>> getIngredientListAll() {
        return ResponseEntity.ok(ingredientMgmtService.getIngredientListAll());
    }

    @PostMapping("system")
    public ResponseEntity<List<IngredientVO>> getIngredientList(@RequestBody IngredientVO vo) {
        return ResponseEntity.ok(ingredientMgmtService.getIngredientList(vo));
    }

    @PostMapping("check/duplication/system")
    public ResponseEntity<Integer> checkDuplication(@RequestBody IngredientVO vo) {
        return ResponseEntity.ok(ingredientMgmtService.checkDuplication(vo));
    }

    @PatchMapping("delete/system")
    public ResponseEntity<Integer> deleteIngredient(@RequestBody IngredientVO vo) {
        ingredientMgmtService.deleteIngredient(vo);
        return new ResponseEntity("success", HttpStatus.OK);
    }

    @PatchMapping("system")
    public ResponseEntity<Integer> updateIngredient(@RequestBody IngredientVO vo) {
        ingredientMgmtService.updateIngredient(vo);
        return new ResponseEntity("success", HttpStatus.OK);
    }

    @PutMapping("system")
    public ResponseEntity<Integer> insertIngredient(@RequestBody IngredientVO vo) {
        ingredientMgmtService.insertIngredient(vo);
        return new ResponseEntity("success", HttpStatus.OK);
    }

}
