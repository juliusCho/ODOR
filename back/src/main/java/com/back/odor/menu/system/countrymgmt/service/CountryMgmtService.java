package com.back.odor.menu.system.countrymgmt.service;

import com.back.odor.common.utils.LocaleUtil;
import com.back.odor.menu.system.countrymgmt.mapper.CountryMgmtMapper;
import com.back.odor.menu.system.countrymgmt.vo.CountryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CountryMgmtService implements CountryMgmtServiceSpec {

    @Autowired
    private CountryMgmtMapper countryMgmtMapper;


    @Override
    public List<CountryVO> getCountryListAll() {
        return countryMgmtMapper.getCountryListAll(LocaleUtil.getLocale());
    }

    @Override
    public List<CountryVO> getCountryList(CountryVO vo) {
        return countryMgmtMapper.getCountryList(vo);
    }

    @Override
    public Integer checkDuplication(String countryCode) {
        return countryMgmtMapper.checkDuplication(countryCode);
    }

    @Override
    public void insertCountry(CountryVO vo) {
        countryMgmtMapper.insertCountry(vo);
    }

    @Override
    public void updateCountry(CountryVO vo) {
        countryMgmtMapper.updateCountry(vo);
    }

    @Override
    public void deleteCountry(CountryVO vo) {
        countryMgmtMapper.deleteCountry(vo);
    }

}
