package com.back.odor.menu.system.membershipmgmt.mapper;

import com.back.odor.menu.system.membershipmgmt.vo.MembershipForumMapperVO;
import com.back.odor.menu.system.membershipmgmt.vo.MembershipVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MembershipMgmtMapper {

    List<MembershipVO> getMembershipListAll(@Param("locale") String locale);

    List<MembershipVO> getMembershipList(@Param("vo") MembershipVO vo);

    Integer checkDuplication(@Param("vo") MembershipVO vo);
    void deleteMembership(@Param("vo") MembershipVO vo);
    void updateMembership(@Param("vo") MembershipVO vo);
    void insertMembership(@Param("vo") MembershipVO vo);

    List<MembershipForumMapperVO> getMappedForumList(@Param("membershipKey") Long membershipKey);

    void deleteMapping(@Param("vo") MembershipForumMapperVO vo);
    void insertMapping(@Param("vo") MembershipForumMapperVO vo);

}
