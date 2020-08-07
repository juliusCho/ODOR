package com.back.odor.menu.system.producttypemgmt.controller;

import com.back.odor.menu.system.producttypemgmt.service.ProductTypeMgmtService;
import com.back.odor.menu.system.producttypemgmt.vo.ProductTypeVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("odor/producttypes")
@Slf4j
public class ProductTypeMgmtController {

    @Autowired
    private ProductTypeMgmtService productTypeMgmtService;


    @GetMapping("system")
    public ResponseEntity<List<ProductTypeVO>> getProductTypeListAll() {
        return ResponseEntity.ok(productTypeMgmtService.getProductTypeListAll());
    }

    @PostMapping("system")
    public ResponseEntity<List<ProductTypeVO>> getProductTypeList(@RequestBody ProductTypeVO vo) {
        return ResponseEntity.ok(productTypeMgmtService.getProductTypeList(vo));
    }

    @PostMapping("check/duplication/system")
    public ResponseEntity<Integer> checkDuplication(@RequestBody ProductTypeVO vo) {
        return ResponseEntity.ok(productTypeMgmtService.checkDuplication(vo));
    }

    @PatchMapping("delete/system")
    public ResponseEntity<Integer> deleteProductType(@RequestBody ProductTypeVO vo) {
        productTypeMgmtService.deleteProductType(vo);
        return new ResponseEntity("success", HttpStatus.OK);
    }

    @PatchMapping("system")
    public ResponseEntity<Integer> updateProductType(@RequestBody ProductTypeVO vo) {
        productTypeMgmtService.updateProductType(vo);
        return new ResponseEntity("success", HttpStatus.OK);
    }

    @PutMapping("system")
    public ResponseEntity<Integer> insertProductType(@RequestBody ProductTypeVO vo) {
        productTypeMgmtService.insertProductType(vo);
        return new ResponseEntity("success", HttpStatus.OK);
    }

}
