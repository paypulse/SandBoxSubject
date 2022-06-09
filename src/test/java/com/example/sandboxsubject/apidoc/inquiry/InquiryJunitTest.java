package com.example.sandboxsubject.apidoc.inquiry;


import com.example.sandboxsubject.common.response.CommonRes;
import com.example.sandboxsubject.common.util.JsonUtil;
import com.example.sandboxsubject.inquiry.controller.req.CreatorInquiryReq;
import com.example.sandboxsubject.inquiry.controller.req.ProfiInquiryReq;
import com.example.sandboxsubject.inquiry.service.InquiryService;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@AutoConfigureRestDocs
public class InquiryJunitTest {

    @Autowired
    private MockMvc mockMvc;

    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation();

    private InquiryService mockInquiryService = mock(InquiryService.class);


    @Transactional
    @Test
    public void apidoc_특정_채널수익_계약정보_크리에이터정산() throws Exception{

        //request
        ProfiInquiryReq request = ProfiInquiryReq.builder()
                .channelId("100")
                .startDate("2021-01-01")
                .endDate("2022-02-01").build();

        //response Data
        CommonRes common = new CommonRes();
        given(mockInquiryService.incomeInquery(request)).willReturn(common);

       mockMvc.perform(post("/api/inquery/income/creator")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(JsonUtil.asJsonString(request)))
				.andExpect(status().isOk())
				.andDo(print())
				.andDo(document("apidoc_특정_채널수익_계약정보_크리에이터정산",
						requestFields(
								fieldWithPath("channelId").description("채널ID"),
								fieldWithPath("startDate").description("날짜 조건 (시작일)"),
								fieldWithPath("endDate").description("날짜 조건 (종료일)")
						),
						responseFields(
								fieldWithPath("data.oneChannelMonthlyAmount").description("특정 채널의 월별 수익"),
								fieldWithPath("data.oneChannelMonthlyRS").description("특정 채널의 월별 순수익"),
								fieldWithPath("data.creatorsMonthlyRS[].creatorNm").description("특정 채널에 포함된 크리에이터 명"),
								fieldWithPath("data.creatorsMonthlyRS[].createAmt").description("특정 채널의 월별 수익에 크리에이터당 순수익"),
								fieldWithPath("status").description("처리상태 (EX : SUCCESS)"),
								fieldWithPath("msg").description("응답메시지 (EX : 등록 성공.)")
						)));


    }

    @Transactional
    @Test
    public void apidoc_특정_크리에이터기준_정산조회() throws Exception{

        //request
        CreatorInquiryReq request = CreatorInquiryReq.builder()
				.creatorCd("101")
				.startDate("2022-01-01")
				.endDate("2022-04-01")
				.build();

        //response Data
        CommonRes common = new CommonRes();
        given(mockInquiryService.incomeCreator(request)).willReturn(common);

       mockMvc.perform(post("/api/inquery/income/realCreator")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(JsonUtil.asJsonString(request)))
				.andExpect(status().isOk())
				.andDo(print())
				.andDo(document("apidoc_특정_크리에이터기준_정산조회",
						requestFields(
								fieldWithPath("creatorCd").description("크리에이터 계약 번호"),
								fieldWithPath("startDate").description("날짜 조건 (시작일)"),
								fieldWithPath("endDate").description("날짜 조건 (종료일)")
						),
						responseFields(
								fieldWithPath("data.CreatorsRealProfit").description("특정 크리에이터 월별 수익"),
								fieldWithPath("status").description("처리상태 (EX : SUCCESS)"),
								fieldWithPath("msg").description("응답메시지 (EX : 등록 성공.)")
						)));


    }


    @Transactional
    @Test
    public void apidoc_월별_sandbox_총_순매출조회() throws Exception{

        //request
        String startDate = "2022-01-01";
        String endDate = "2022-03-01";

        //response Data
        CommonRes common = new CommonRes();
        given(mockInquiryService.incomeSandbox(startDate, endDate)).willReturn(common);

       mockMvc.perform(get("/api/inquery/income/sandbox?startDate=2022-01-01&endDate=2022-03-01"))
				.andExpect(status().isOk())
				.andDo(print())
				.andDo(document("apidoc_월별_sandbox_총_순매출조회",
						responseFields(
								fieldWithPath("data.sandboxRealAmount").description("샌드박스 순수익"),
								fieldWithPath("data.sandboxTotalAmount").description("샌드박스 총수익"),
								fieldWithPath("status").description("처리상태 (EX : SUCCESS)"),
								fieldWithPath("msg").description("응답메시지 (EX : 등록 성공.)")
						)));


    }




}
