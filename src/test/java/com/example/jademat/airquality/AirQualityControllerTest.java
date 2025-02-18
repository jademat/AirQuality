package com.example.jademat.airquality;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AirQualityControllerTest {

    // MVC 테스트를 위한 가짜객체 생성
    @Autowired private MockMvc mvc;

    // 올바른 Json 형태의 데이터인지 확인
    // ObjectMapper 객체 선언
    private final ObjectMapper mapper = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("testAirQualityController")
    public void testAirQualityController() throws Exception {
        // 테스트 실행
        MvcResult result = mockMvc.perform(get("/basic"))
                .andExpect(status().isOk())                                         // basic엔드포인트 접속 여부 확인
                .andExpect(view().name("airQuality"))               // 올바른 뷰가 리턴 되는지 여부 확인
                .andExpect(model().attributeExists("airQulityData"))        // 모델에 객체가 존재하는지 여부 확인
                .andReturn();

        // 모델에서 airQulityData 추출
        String json = result.getModelAndView().getModel().get("airQulityData").toString();
        System.out.println(json);

        // JSON 유효성 검사
        assertDoesNotThrow(( ) -> mapper.readTree(json));
    }
}
