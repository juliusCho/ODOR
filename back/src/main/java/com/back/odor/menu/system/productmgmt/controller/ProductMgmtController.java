package com.back.odor.menu.system.productmgmt.controller;

import com.back.odor.menu.system.productmgmt.service.ProductMgmtService;
import com.back.odor.menu.system.productmgmt.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("odor/products")
public class ProductMgmtController {

    @Autowired
    private ProductMgmtService productMgmtService;


    @PostMapping("system")
    public ResponseEntity<List<ProductVO>> getProductList(@RequestBody ProductVO vo) {
        return ResponseEntity.ok(productMgmtService.getProductList(vo));
    }

    @GetMapping("system")
    public ResponseEntity<List<ProductVO>> getProductListAll() {
        return ResponseEntity.ok(productMgmtService.getProductListAll());
    }

    @PostMapping("check/duplication/system")
    public ResponseEntity<Integer> checkDuplication(@RequestBody ProductVO vo) {
        return ResponseEntity.ok(productMgmtService.checkDuplication(vo));
    }

    @PutMapping("insert/system")
    public ResponseEntity<Integer> insertProduct(@RequestBody ProductVO vo) {
        productMgmtService.insertProduct(vo);
        return new ResponseEntity("success", HttpStatus.OK);
    }

    @PatchMapping("update/system")
    public ResponseEntity<Integer> updateProduct(@RequestBody ProductVO vo) {
        productMgmtService.updateProduct(vo);
        return new ResponseEntity("success", HttpStatus.OK);
    }

    @DeleteMapping("delete/system")
    public ResponseEntity<Integer> deleteProduct(@RequestParam Long productKey) {
        productMgmtService.deleteProduct(productKey);
        return new ResponseEntity("success", HttpStatus.OK);
    }

    @GetMapping("maps/brands/system")
    public ResponseEntity<List<ProductBrandMapperVO>> getMappedBrandList(@RequestParam Long productKey) {
        return ResponseEntity.ok(productMgmtService.getMappedBrandList(productKey));
    }

    @GetMapping("maps/creators/system")
    public ResponseEntity<List<ProductCreatorMapperVO>> getMappedCreatorList(@RequestParam Long productKey) {
        return ResponseEntity.ok(productMgmtService.getMappedCreatorList(productKey));
    }

    @GetMapping("maps/ingredients/system")
    public ResponseEntity<List<ProductIngredientMapperVO>> getMappedIngredientList(@RequestParam Long productKey) {
        return ResponseEntity.ok(productMgmtService.getMappedIngredientList(productKey));
    }

    @GetMapping("maps/producttypes/system")
    public ResponseEntity<List<ProductProductTypeMapperVO>> getMappedProductTypeList(@RequestParam Long productKey) {
        return ResponseEntity.ok(productMgmtService.getMappedProductTypeList(productKey));
    }

    @PatchMapping("maps/brands/update/system")
    public ResponseEntity saveBrandMapping(@RequestBody List<ProductBrandMapperVO> list) {
        productMgmtService.saveBrandMapping(list);
        return new ResponseEntity("success", HttpStatus.OK);
    }

    @PatchMapping("maps/creators/update/system")
    public ResponseEntity saveCreatorMapping(@RequestBody List<ProductCreatorMapperVO> list) {
        productMgmtService.saveCreatorMapping(list);
        return new ResponseEntity("success", HttpStatus.OK);
    }

    @PatchMapping("maps/ingredients/update/system")
    public ResponseEntity saveIngredientMapping(@RequestBody List<ProductIngredientMapperVO> list) {
        productMgmtService.saveIngredientMapping(list);
        return new ResponseEntity("success", HttpStatus.OK);
    }

    @PatchMapping("maps/producttypes/update/system")
    public ResponseEntity saveProductTypeMapping(@RequestBody List<ProductProductTypeMapperVO> list) {
        productMgmtService.saveProductTypeMapping(list);
        return new ResponseEntity("success", HttpStatus.OK);
    }

    @DeleteMapping("maps/brands/delete/system")
    public ResponseEntity deleteBrandMapping(
            @RequestParam Long productKey, @RequestParam String categoryId) {
        productMgmtService.deleteBrandMapping(productKey, categoryId);
        return new ResponseEntity("success", HttpStatus.OK);
    }

    @DeleteMapping("maps/creators/delete/system")
    public ResponseEntity deleteCreatorMapping(
            @RequestParam Long productKey, @RequestParam String categoryId) {
        productMgmtService.deleteCreatorMapping(productKey, categoryId);
        return new ResponseEntity("success", HttpStatus.OK);
    }

    @DeleteMapping("maps/ingredients/delete/system")
    public ResponseEntity deleteIngredientMapping(
            @RequestParam Long productKey, @RequestParam String categoryId) {
        productMgmtService.deleteIngredientMapping(productKey, categoryId);
        return new ResponseEntity("success", HttpStatus.OK);
    }

    @DeleteMapping("maps/producttypes/delete/system")
    public ResponseEntity deleteProductTypeMapping(
            @RequestParam Long productKey, @RequestParam String categoryId) {
        productMgmtService.deleteProductTypeMapping(productKey, categoryId);
        return new ResponseEntity("success", HttpStatus.OK);
    }

}
