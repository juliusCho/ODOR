package com.back.odor.menu.system.codemgmt.service;

import com.back.odor.menu.system.codemgmt.vo.CodeGroupVO;
import com.back.odor.menu.system.codemgmt.vo.CodeVO;

import java.util.List;

public interface CodeMgmtServiceSpec {

    List<CodeVO> getCodeList();

    List<CodeGroupVO> getCodeGroupList(CodeGroupVO vo);

}
