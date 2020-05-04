package com.back.odor.menu.system.brandmgmt.controller;

import com.back.odor.menu.system.brandmgmt.service.BrandMgmtService;
import com.back.odor.menu.system.brandmgmt.vo.BrandVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
