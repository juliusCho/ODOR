package com.back.odor.menu.system.countrymgmt.mapper;

import com.back.odor.menu.system.countrymgmt.vo.CountryVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CountryMgmtMapper {

    List<CountryVO> getCountryListAll(@Param("locale") String locale);

    List<CountryVO> getCountryList(@Param("vo") CountryVO vo);
    Integer checkDuplication(@Param("countryCode") String countryCode);
    void insertCountry(@Param("vo") CountryVO vo);
    void updateCountry(@Param("vo") CountryVO vo);
    void deleteCountry(@Param("vo") CountryVO vo);

}
