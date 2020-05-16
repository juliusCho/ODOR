package com.back.odor.menu.system.membershipmgmt.controller;

import com.back.odor.menu.system.membershipmgmt.service.MembershipMgmtService;
import com.back.odor.menu.system.membershipmgmt.vo.MembershipForumMapperVO;
import com.back.odor.menu.system.membershipmgmt.vo.MembershipVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("odor/membership")
@Slf4j
public class MembershipMgmtController {

    @Autowired
    private MembershipMgmtService membershipMgmtService;


    @GetMapping("getMembershipListAll/system")
    public ResponseEntity<List<MembershipVO>> getMembershipListAll() {
        return ResponseEntity.ok(membershipMgmtService.getMembershipListAll());
    }

    @PostMapping("getMembershipList/system")
    public ResponseEntity<List<MembershipVO>> getMembershipList(@RequestBody MembershipVO vo) {
        return ResponseEntity.ok(membershipMgmtService.getMembershipList(vo));
    }

    @PostMapping("checkDuplication/system")
    public ResponseEntity<Integer> checkDuplication(@RequestBody MembershipVO vo) {
        return ResponseEntity.ok(membershipMgmtService.checkDuplication(vo));
    }

    @PatchMapping("deleteMembership/system")
    public ResponseEntity<Integer> deleteMembership(@RequestBody MembershipVO vo) {
        membershipMgmtService.deleteMembership(vo);
        return new ResponseEntity("success", HttpStatus.OK);
    }

    @PatchMapping("updateMembership/system")
    public ResponseEntity<Integer> updateMembership(@RequestBody MembershipVO vo) {
        membershipMgmtService.updateMembership(vo);
        return new ResponseEntity("success", HttpStatus.OK);
    }

    @PutMapping("insertMembership/system")
    public ResponseEntity<Integer> insertMembership(@RequestBody MembershipVO vo) {
        membershipMgmtService.insertMembership(vo);
        return new ResponseEntity("success", HttpStatus.OK);
    }

    @PostMapping("getMappedForumList/system")
    public ResponseEntity<List<MembershipForumMapperVO>> getMappedForumList(@RequestBody MembershipVO vo) {
        return ResponseEntity.ok(membershipMgmtService.getMappedForumList(vo));
    }

    @DeleteMapping("deleteMapping/system")
    public ResponseEntity deleteMapping(
            @RequestParam Long membershipKey, @RequestParam String categoryId) {
        membershipMgmtService.deleteMapping(membershipKey, categoryId);
        return new ResponseEntity("success", HttpStatus.OK);
    }

    @PatchMapping("saveMapping/system")
    public ResponseEntity saveMapping(@RequestBody List<MembershipForumMapperVO> list) {
        membershipMgmtService.saveMapping(list);
        return new ResponseEntity("success", HttpStatus.OK);
    }

}
