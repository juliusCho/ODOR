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

}
