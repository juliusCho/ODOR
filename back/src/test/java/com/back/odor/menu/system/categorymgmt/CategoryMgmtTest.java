package com.back.odor.menu.system.categorymgmt;

import com.back.odor.menu.system.categorymgmt.service.CategoryMgmtService;
import com.back.odor.menu.system.categorymgmt.vo.CategoryVO;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class CategoryMgmtTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryMgmtService service;

    @Test
    @DisplayName("arareare : validateLogin()")
    public void checkFirstCategory() throws Exception {
        CategoryVO vo = new CategoryVO();
        vo.setCategoryName("Perfume1");
        List<CategoryVO> list = Arrays.asList(vo);
        when(service.getCategoryListAll()).thenReturn(list);

        mockMvc.perform(get("http://localhost:8888/odor/category/getCategoryListAll/system"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].categoryName", is(vo.getCategoryName())));
    }

}
