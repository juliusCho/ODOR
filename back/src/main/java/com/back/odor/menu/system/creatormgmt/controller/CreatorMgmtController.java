package com.back.odor.menu.system.creatormgmt.controller;

import com.back.odor.menu.system.creatormgmt.service.CreatorMgmtService;
import com.back.odor.menu.system.creatormgmt.vo.CreatorVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("odor/creator")
public class CreatorMgmtController {

    @Autowired
    private CreatorMgmtService creatorMgmtService;


    @PostMapping("getCreatorList/system")
    public ResponseEntity<List<CreatorVO>> getCreatorList(@RequestBody CreatorVO vo) {
        return ResponseEntity.ok(creatorMgmtService.getCreatorList(vo));
    }

    @GetMapping("getCreatorListAll/system")
    public ResponseEntity<List<CreatorVO>> getCreatorListAll() {
        return ResponseEntity.ok(creatorMgmtService.getCreatorListAll());
    }

    @PutMapping("insertCreator/system")
    public ResponseEntity<Integer> insertCreator(@RequestBody CreatorVO vo) {
        creatorMgmtService.insertCreator(vo);
        return new ResponseEntity("success", HttpStatus.OK);
    }

    @PatchMapping("updateCreator/system")
    public ResponseEntity<Integer> updateCreator(@RequestBody CreatorVO vo) {
        creatorMgmtService.updateCreator(vo);
        return new ResponseEntity("success", HttpStatus.OK);
    }

    @PatchMapping("deleteCreator/system")
    public ResponseEntity<Integer> deleteCreator(@RequestBody CreatorVO vo) {
        creatorMgmtService.deleteCreator(vo);
        return new ResponseEntity("success", HttpStatus.OK);
    }

}
