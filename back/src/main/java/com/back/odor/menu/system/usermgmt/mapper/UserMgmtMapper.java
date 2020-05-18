package com.back.odor.menu.system.usermgmt.mapper;

import com.back.odor.menu.system.usermgmt.vo.BlockedUserVO;
import com.back.odor.menu.system.usermgmt.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMgmtMapper {

    List<UserVO> getUserListAll();
    List<UserVO> getUserList(@Param("vo") UserVO vo);

    Integer checkDuplication(@Param("vo") UserVO vo);
    Integer checkEmailDuplication(@Param("vo") UserVO vo);
    void insertUser(@Param("vo") UserVO vo);
    void updateUser(@Param("vo") UserVO vo);
    void deleteUser(@Param("vo") UserVO vo);

    void unblockUser(@Param("vo") BlockedUserVO vo);
    void blockUser(@Param("vo") BlockedUserVO vo);

}
