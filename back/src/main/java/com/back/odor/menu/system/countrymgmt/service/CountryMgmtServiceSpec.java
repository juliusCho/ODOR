package com.back.odor.menu.system.countrymgmt.service;

import com.back.odor.menu.system.countrymgmt.vo.CountryVO;

import java.util.List;

public interface CountryMgmtServiceSpec {

    List<CountryVO> getCountryListAll();

    List<CountryVO> getCountryList(CountryVO vo);
    Integer checkDuplication(String countryCode);
    void insertCountry(CountryVO vo);
    void updateCountry(CountryVO vo);
    void deleteCountry(CountryVO vo);

}
