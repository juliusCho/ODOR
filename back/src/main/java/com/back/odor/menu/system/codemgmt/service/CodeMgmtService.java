package com.back.odor.menu.system.codemgmt.service;

import com.back.odor.common.utils.LocaleUtil;
import com.back.odor.menu.system.codemgmt.mapper.CodeMgmtMapper;
import com.back.odor.menu.system.codemgmt.vo.CodeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CodeMgmtService implements CodeMgmtServiceSpec {

    @Autowired
    private CodeMgmtMapper codeMgmtMapper;

    @Override
    public List<CodeVO> getCodeList() {
        return codeMgmtMapper.getCodeList(LocaleUtil.getLocale());
    }

}
