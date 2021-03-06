package com.back.odor.menu.system.countrymgmt.controller;

import com.back.odor.menu.system.countrymgmt.service.CountryMgmtService;
import com.back.odor.menu.system.countrymgmt.vo.CountryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("odor/countries")
public class CountryMgmtController {

    @Autowired
    private CountryMgmtService countryMgmtService;


    @GetMapping("system")
    public ResponseEntity<List<CountryVO>> getCountryListAll() {
        return ResponseEntity.ok(countryMgmtService.getCountryListAll());
    }

    @PostMapping("system")
    public ResponseEntity<List<CountryVO>> getCountryList(@RequestBody CountryVO vo) {
        return ResponseEntity.ok(countryMgmtService.getCountryList(vo));
    }

    @PostMapping("check/duplication/system")
    public ResponseEntity<Integer> checkDuplication(@RequestParam String countryCode) {
        return ResponseEntity.ok(countryMgmtService.checkDuplication(countryCode));
    }

    @PutMapping("system")
    public ResponseEntity insertCountry(@RequestBody CountryVO vo) {
        countryMgmtService.insertCountry(vo);
        return new ResponseEntity("success", HttpStatus.OK);
    }

    @PatchMapping("system")
    public ResponseEntity updateCountry(@RequestBody CountryVO vo) {
        countryMgmtService.updateCountry(vo);
        return new ResponseEntity("success", HttpStatus.OK);
    }

    @PatchMapping("delete/system")
    public ResponseEntity deleteCountry(@RequestBody CountryVO vo) {
        countryMgmtService.deleteCountry(vo);
        return new ResponseEntity("success", HttpStatus.OK);
    }

}
