package com.back.odor.menu.system.categorymgmt.controller;

import com.back.odor.menu.system.categorymgmt.service.CategoryMgmtService;
import com.back.odor.menu.system.categorymgmt.vo.CategoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("odor/category")
public class CategoryMgmtController {

    @Autowired
    private CategoryMgmtService categoryMgmtService;


    @GetMapping("getCategoryListAll/system")
    public ResponseEntity<List<CategoryVO>> getCountryListAll() {
        return ResponseEntity.ok(categoryMgmtService.getCategoryListAll());
    }

    @PostMapping("getCategoryList/system")
    public ResponseEntity<List<CategoryVO>> getCategoryList(@RequestBody CategoryVO vo) {
        return ResponseEntity.ok(categoryMgmtService.getCategoryList(vo));
    }

    @PostMapping("checkDuplication/system")
    public ResponseEntity<Integer> checkDuplication(@RequestParam String categoryId) {
        return ResponseEntity.ok(categoryMgmtService.checkDuplication(categoryId));
    }

    @PutMapping("insertCategory/system")
    public ResponseEntity insertCategory(@RequestBody CategoryVO vo) {
        categoryMgmtService.insertCategory(vo);
        return new ResponseEntity("success", HttpStatus.OK);
    }

    @PatchMapping("updateCategory/system")
    public ResponseEntity updateCategory(@RequestBody CategoryVO vo) {
        categoryMgmtService.updateCategory(vo);
        return new ResponseEntity("success", HttpStatus.OK);
    }

    @PatchMapping("deleteCategory/system")
    public ResponseEntity deleteCategory(@RequestBody CategoryVO vo) {
        categoryMgmtService.deleteCategory(vo);
        return new ResponseEntity("success", HttpStatus.OK);
    }

}
