package com.back.odor.menu.system.brandmgmt.controller;

import com.back.odor.menu.system.brandmgmt.service.BrandMgmtService;
import com.back.odor.menu.system.brandmgmt.vo.BrandCategoryMapperVO;
import com.back.odor.menu.system.brandmgmt.vo.BrandVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("odor/brands")
public class BrandMgmtController {

    @Autowired
    private BrandMgmtService brandMgmtService;


    @PostMapping("system")
    public ResponseEntity<List<BrandVO>> getBrandList(@RequestBody BrandVO vo) {
        return ResponseEntity.ok(brandMgmtService.getBrandList(vo));
    }

    @GetMapping("system")
    public ResponseEntity<List<BrandVO>> getBrandListAll() {
        return ResponseEntity.ok(brandMgmtService.getBrandListAll());
    }

    @PostMapping("check/duplication/system")
    public ResponseEntity<Integer> checkDuplication(@RequestBody BrandVO vo) {
        return ResponseEntity.ok(brandMgmtService.checkDuplication(vo));
    }

    @PutMapping("insert/system")
    public ResponseEntity<Integer> insertBrand(@RequestBody BrandVO vo) {
        brandMgmtService.insertBrand(vo);
        return new ResponseEntity("success", HttpStatus.OK);
    }

    @PatchMapping("update/system")
    public ResponseEntity<Integer> updateBrand(@RequestBody BrandVO vo) {
        brandMgmtService.updateBrand(vo);
        return new ResponseEntity("success", HttpStatus.OK);
    }

    @PatchMapping("delete/system")
    public ResponseEntity<Integer> deleteBrand(@RequestBody BrandVO vo) {
        brandMgmtService.deleteBrand(vo);
        return new ResponseEntity("success", HttpStatus.OK);
    }

    @PostMapping("maps/system")
    public ResponseEntity<List<BrandCategoryMapperVO>> getMappedCategoryList(@RequestParam Long brandKey) {
        return ResponseEntity.ok(brandMgmtService.getMappedCategoryList(brandKey));
    }

    @DeleteMapping("maps/delete/system")
    public ResponseEntity deleteMapping(@RequestParam Long brandKey) {
        brandMgmtService.deleteMapping(brandKey);
        return new ResponseEntity("success", HttpStatus.OK);
    }

    @PatchMapping("maps/update/system")
    public ResponseEntity<Integer> saveMapping(@RequestBody List<BrandCategoryMapperVO> list) {
        brandMgmtService.saveMapping(list);
        return new ResponseEntity("success", HttpStatus.OK);
    }

}
