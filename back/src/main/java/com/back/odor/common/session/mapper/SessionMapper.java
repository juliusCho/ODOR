package com.back.odor.common.session.mapper;

import com.back.odor.menu.system.usermgmt.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SessionMapper {

    UserVO validateLogin(@Param("user") UserVO user);
}
