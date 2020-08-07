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
@RequestMapping("odor/memberships")
@Slf4j
public class MembershipMgmtController {

    @Autowired
    private MembershipMgmtService membershipMgmtService;


    @GetMapping("system")
    public ResponseEntity<List<MembershipVO>> getMembershipListAll() {
        return ResponseEntity.ok(membershipMgmtService.getMembershipListAll());
    }

    @PostMapping("system")
    public ResponseEntity<List<MembershipVO>> getMembershipList(@RequestBody MembershipVO vo) {
        return ResponseEntity.ok(membershipMgmtService.getMembershipList(vo));
    }

    @PostMapping("check/duplication/system")
    public ResponseEntity<Integer> checkDuplication(@RequestBody MembershipVO vo) {
        return ResponseEntity.ok(membershipMgmtService.checkDuplication(vo));
    }

    @PatchMapping("delete/system")
    public ResponseEntity<Integer> deleteMembership(@RequestBody MembershipVO vo) {
        membershipMgmtService.deleteMembership(vo);
        return new ResponseEntity("success", HttpStatus.OK);
    }

    @PatchMapping("system")
    public ResponseEntity<Integer> updateMembership(@RequestBody MembershipVO vo) {
        membershipMgmtService.updateMembership(vo);
        return new ResponseEntity("success", HttpStatus.OK);
    }

    @PutMapping("system")
    public ResponseEntity<Integer> insertMembership(@RequestBody MembershipVO vo) {
        membershipMgmtService.insertMembership(vo);
        return new ResponseEntity("success", HttpStatus.OK);
    }

    @PostMapping("maps/system")
    public ResponseEntity<List<MembershipForumMapperVO>> getMappedForumList(@RequestBody MembershipVO vo) {
        return ResponseEntity.ok(membershipMgmtService.getMappedForumList(vo));
    }

    @DeleteMapping("maps/system")
    public ResponseEntity deleteMapping(
            @RequestParam Long membershipKey, @RequestParam String categoryId) {
        membershipMgmtService.deleteMapping(membershipKey, categoryId);
        return new ResponseEntity("success", HttpStatus.OK);
    }

    @PatchMapping("maps/system")
    public ResponseEntity saveMapping(@RequestBody List<MembershipForumMapperVO> list) {
        membershipMgmtService.saveMapping(list);
        return new ResponseEntity("success", HttpStatus.OK);
    }

}
