package com.back.odor.menu.system.creatormgmt.mapper;

import com.back.odor.menu.system.creatormgmt.vo.CreatorVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CreatorMgmtMapper {

    List<CreatorVO> getCreatorList(@Param("vo") CreatorVO vo);
    List<CreatorVO> getCreatorListAll();

    void insertCreator(@Param("vo") CreatorVO vo);
    void updateCreator(@Param("vo") CreatorVO vo);
    void deleteCreator(@Param("vo") CreatorVO vo);

}
