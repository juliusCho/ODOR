package com.back.odor.menu.system.usermgmt.service;

import com.back.odor.menu.system.usermgmt.mapper.UserMgmtMapper;
import com.back.odor.menu.system.usermgmt.vo.BlockedUserVO;
import com.back.odor.menu.system.usermgmt.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserMgmtService implements UserMgmtServiceSpec {

    @Autowired
    private UserMgmtMapper userMgmtMapper;

    @Override
    public List<UserVO> getUserListAll() {
        return userMgmtMapper.getUserListAll();
    }

    @Override
    public List<UserVO> getUserList(UserVO vo) {
        return userMgmtMapper.getUserList(vo);
    }

    @Override
    public Integer checkDuplication(UserVO vo) {
        return userMgmtMapper.checkDuplication(vo);
    }

    @Override
    public Integer checkEmailDuplication(UserVO vo) {
        return userMgmtMapper.checkEmailDuplication(vo);
    }

    @Override
    public void insertUser(UserVO vo) {
        userMgmtMapper.insertUser(vo);
    }

    @Override
    public void updateUser(UserVO vo) {
        userMgmtMapper.updateUser(vo);
    }

    @Override
    public void deleteUser(UserVO vo) {
        userMgmtMapper.deleteUser(vo);
    }

    @Override
    public void unblockUser(String userId, String email) {
        BlockedUserVO vo = new BlockedUserVO();
        vo.setUserId(userId);
        vo.setEmail(email);
        userMgmtMapper.unblockUser(vo);
    }

    @Override
    public void blockUser(BlockedUserVO vo) {
        userMgmtMapper.blockUser(vo);
    }

}
