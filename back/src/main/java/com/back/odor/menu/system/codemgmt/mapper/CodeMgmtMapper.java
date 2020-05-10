package com.back.odor.menu.system.codemgmt.mapper;

import com.back.odor.menu.system.codemgmt.vo.CodeGroupVO;
import com.back.odor.menu.system.codemgmt.vo.CodeVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CodeMgmtMapper {

    List<CodeVO> getCodeList(@Param("locale") String locale);

    List<CodeGroupVO> getCodeGroupList(@Param("vo") CodeGroupVO vo);

    Integer checkDuplication(@Param("codeGroupId") String codeGroupId);

    void insertCodeGroup(@Param("vo") CodeGroupVO vo);
    void updateCodeGroup(@Param("vo") CodeGroupVO vo);
    void deleteCodeGroup(@Param("vo") CodeGroupVO vo);

}
