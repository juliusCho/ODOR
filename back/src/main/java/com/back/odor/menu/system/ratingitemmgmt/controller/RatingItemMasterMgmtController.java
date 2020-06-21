package com.back.odor.menu.system.ratingitemmgmt.controller;

import com.back.odor.menu.system.ratingitemmgmt.service.RatingItemMasterMgmtService;
import com.back.odor.menu.system.ratingitemmgmt.vo.RatingItemMasterVO;
import com.back.odor.menu.system.usermgmt.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("odor/rating")
@Slf4j
public class RatingItemMasterMgmtController {

    @Autowired
    private RatingItemMasterMgmtService ratingItemMasterMgmtService;


    @PostMapping("getRatingItemMasterList/system")
    public ResponseEntity<List<RatingItemMasterVO>> getRatingItemMasterList(
            @RequestBody RatingItemMasterVO vo
    ) {
        return ResponseEntity.ok(ratingItemMasterMgmtService.getRatingItemMasterList(vo));
    }

    @PostMapping("checkDuplication/system")
    public ResponseEntity<Integer> checkDuplication(@RequestBody RatingItemMasterVO vo) {
        return ResponseEntity.ok(ratingItemMasterMgmtService.checkDuplication(vo));
    }

    @PatchMapping("deleteRatingItemMaster/system")
    public ResponseEntity<Integer> deleteRatingItemMaster(@RequestBody RatingItemMasterVO vo) {
        ratingItemMasterMgmtService.deleteRatingItemMaster(vo);
        return new ResponseEntity("success", HttpStatus.OK);
    }

    @PatchMapping("updateRatingItemMaster/system")
    public ResponseEntity<Integer> updateRatingItemMaster(@RequestBody RatingItemMasterVO vo) {
        ratingItemMasterMgmtService.updateRatingItemMaster(vo);
        return new ResponseEntity("success", HttpStatus.OK);
    }

    @PutMapping("insertRatingItemMaster/system")
    public ResponseEntity<Integer> insertRatingItemMaster(@RequestBody RatingItemMasterVO vo) {
        ratingItemMasterMgmtService.insertRatingItemMaster(vo);
        return new ResponseEntity("success", HttpStatus.OK);
    }

}
