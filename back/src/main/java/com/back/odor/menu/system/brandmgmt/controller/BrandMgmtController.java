package com.back.odor.menu.system.brandmgmt.controller;

import com.back.odor.menu.system.brandmgmt.service.BrandMgmtService;
import com.back.odor.menu.system.brandmgmt.vo.BrandVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("odor/brand")
public class BrandMgmtController {

    @Autowired
    private BrandMgmtService brandMgmtService;


    @PostMapping("getBrandList/system")
    public ResponseEntity<List<BrandVO>> getBrandList(@RequestBody BrandVO vo) {
        return ResponseEntity.ok(brandMgmtService.getBrandList(vo));
    }

    @GetMapping("getBrandListAll/system")
    public ResponseEntity<List<BrandVO>> getBrandListAll() {
        return ResponseEntity.ok(brandMgmtService.getBrandListAll());
    }

    @PostMapping("checkDuplication/system")
    public ResponseEntity<Integer> checkDuplication(@RequestBody BrandVO vo) {
        return ResponseEntity.ok(brandMgmtService.checkDuplication(vo));
    }

    @PutMapping("insertBrand/system")
    public ResponseEntity<Integer> insertBrand(@RequestBody BrandVO vo) {
        brandMgmtService.insertBrand(vo);
        return new ResponseEntity("success", HttpStatus.OK);
    }

    @PatchMapping("updateBrand/system")
    public ResponseEntity<Integer> updateBrand(@RequestBody BrandVO vo) {
        brandMgmtService.updateBrand(vo);
        return new ResponseEntity("success", HttpStatus.OK);
    }

    @PatchMapping("deleteBrand/system")
    public ResponseEntity<Integer> deleteBrand(@RequestBody BrandVO vo) {
        brandMgmtService.deleteBrand(vo);
        return new ResponseEntity("success", HttpStatus.OK);
    }

}
