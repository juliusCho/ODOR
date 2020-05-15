package com.back.odor.menu.system.codemgmt.service;

import com.back.odor.common.utils.LocaleUtil;
import com.back.odor.menu.system.codemgmt.mapper.CodeMgmtMapper;
import com.back.odor.menu.system.codemgmt.vo.CodeGroupVO;
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

    @Override
    public List<CodeGroupVO> getCodeGroupListAll() {
        return codeMgmtMapper.getCodeGroupListAll(LocaleUtil.getLocale());
    }

    @Override
    public List<CodeGroupVO> getCodeGroupList(CodeGroupVO vo) {
        return codeMgmtMapper.getCodeGroupList(vo);
    }

    @Override
    public Integer checkDuplication(String codeGroupId) {
        return codeMgmtMapper.checkDuplication(codeGroupId);
    }

    @Override
    public void insertCodeGroup(CodeGroupVO vo) {
        codeMgmtMapper.insertCodeGroup(vo);
    }

    @Override
    public void updateCodeGroup(CodeGroupVO vo) {
        codeMgmtMapper.updateCodeGroup(vo);
    }

    @Override
    public void deleteCodeGroup(CodeGroupVO vo) {
        codeMgmtMapper.deleteCodeGroup(vo);
    }

    @Override
    public List<CodeVO> getCodeListSystem(CodeVO vo) {
        return codeMgmtMapper.getCodeListSystem(vo);
    }

    @Override
    public Integer checkDetailDuplication(CodeVO vo) {
        return codeMgmtMapper.checkDetailDuplication(vo);
    }

    @Override
    public void insertCode(CodeVO vo) {
        codeMgmtMapper.insertCode(vo);
    }

    @Override
    public void updateCode(CodeVO vo) {
        codeMgmtMapper.updateCode(vo);
    }

    @Override
    public void deleteCode(CodeVO vo) {
        codeMgmtMapper.deleteCode(vo);
    }

    @Override
    public List<CodeVO> getForumCodeList() {
        return codeMgmtMapper.getForumCodeList(LocaleUtil.getLocale());
    }

}
