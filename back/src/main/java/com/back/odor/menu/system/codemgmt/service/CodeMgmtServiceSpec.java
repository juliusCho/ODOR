package com.back.odor.menu.system.codemgmt.service;

import com.back.odor.menu.system.codemgmt.vo.CodeGroupVO;
import com.back.odor.menu.system.codemgmt.vo.CodeVO;

import java.util.List;

public interface CodeMgmtServiceSpec {

    List<CodeVO> getCodeList();
    List<CodeGroupVO> getCodeGroupListAll();

    List<CodeGroupVO> getCodeGroupList(CodeGroupVO vo);
    Integer checkDuplication(String codeGroupId);
    void insertCodeGroup(CodeGroupVO vo);
    void updateCodeGroup(CodeGroupVO vo);
    void deleteCodeGroup(CodeGroupVO vo);

    List<CodeVO> getCodeListSystem(CodeVO vo);
    Integer checkDetailDuplication(CodeVO vo);
    void insertCode(CodeVO vo);
    void updateCode(CodeVO vo);
    void deleteCode(CodeVO vo);

    List<CodeVO> getForumCodeList();

}
