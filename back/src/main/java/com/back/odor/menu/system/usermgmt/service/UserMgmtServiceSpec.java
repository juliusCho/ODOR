package com.back.odor.menu.system.usermgmt.service;

import com.back.odor.menu.system.usermgmt.vo.BlockedUserVO;
import com.back.odor.menu.system.usermgmt.vo.UserVO;

import java.util.List;

public interface UserMgmtServiceSpec {

    List<UserVO> getUserListAll();
    List<UserVO> getUserList(UserVO vo);

    Integer checkDuplication(UserVO vo);
    Integer checkEmailDuplication(UserVO vo);
    void insertUser(UserVO vo);
    void updateUser(UserVO vo);
    void deleteUser(UserVO vo);

    void unblockUser(String userId, String email);
    void blockUser(BlockedUserVO vo);

    BlockedUserVO getBlockInfo(BlockedUserVO vo);

}
