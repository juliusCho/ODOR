package com.back.odor.menu.system.membershipmgmt.service;

import com.back.odor.menu.system.membershipmgmt.vo.MembershipForumMapperVO;
import com.back.odor.menu.system.membershipmgmt.vo.MembershipVO;

import java.util.List;

public interface MembershipMgmtServiceSpec {

    List<MembershipVO> getMembershipListAll();

    List<MembershipVO> getMembershipList(MembershipVO vo);

    Integer checkDuplication(MembershipVO vo);
    void deleteMembership(MembershipVO vo);
    void updateMembership(MembershipVO vo);
    void insertMembership(MembershipVO vo);

    List<MembershipForumMapperVO> getMappedForumList(MembershipVO vo);

    void deleteMapping(Long membershipKey, String categoryId);
    void saveMapping(List<MembershipForumMapperVO> list);

}
