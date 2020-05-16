package com.back.odor.menu.system.membershipmgmt.service;

import com.back.odor.common.utils.LocaleUtil;
import com.back.odor.menu.system.membershipmgmt.mapper.MembershipMgmtMapper;
import com.back.odor.menu.system.membershipmgmt.vo.MembershipForumMapperVO;
import com.back.odor.menu.system.membershipmgmt.vo.MembershipVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
public class MembershipMgmtService implements MembershipMgmtServiceSpec {

    @Autowired
    private MembershipMgmtMapper membershipMgmtMapper;


    @Override
    public List<MembershipVO> getMembershipListAll() {
        return membershipMgmtMapper.getMembershipListAll(LocaleUtil.getLocale());
    }

    @Override
    public List<MembershipVO> getMembershipList(MembershipVO vo) {
        return membershipMgmtMapper.getMembershipList(vo);
    }

    @Override
    public Integer checkDuplication(MembershipVO vo) {
        return membershipMgmtMapper.checkDuplication(vo);
    }

    @Override
    public void deleteMembership(MembershipVO vo) {
        membershipMgmtMapper.deleteMembership(vo);
    }

    @Override
    public void updateMembership(MembershipVO vo) {
        membershipMgmtMapper.updateMembership(vo);
    }

    @Override
    public void insertMembership(MembershipVO vo) {
        membershipMgmtMapper.insertMembership(vo);
    }

    @Override
    public List<MembershipForumMapperVO> getMappedForumList(MembershipVO vo) {
        return membershipMgmtMapper.getMappedForumList(vo.getMembershipKey());
    }

    @Override
    public void deleteMapping(Long membershipKey, String categoryId) {
        MembershipForumMapperVO vo = new MembershipForumMapperVO();
        vo.setMembershipKey(membershipKey);
        vo.setCategoryId(categoryId);
        membershipMgmtMapper.deleteMapping(vo);
    }

    @Override
    @Transactional
    public void saveMapping(List<MembershipForumMapperVO> list) {
        membershipMgmtMapper.deleteMapping(list.get(0));

        for (MembershipForumMapperVO vo : list) {
            membershipMgmtMapper.insertMapping(vo);
        }
    }

}
