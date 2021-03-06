package com.example.sandboxsubject.apidoc.register;

import com.example.sandboxsubject.common.util.JsonUtil;
import com.example.sandboxsubject.common.util.Utils;
import com.example.sandboxsubject.creator.domain.CreatorEntity;
import com.example.sandboxsubject.register.controller.req.registerRequest;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;


import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.example.sandboxsubject.register.controller.req.profitRequest;
import com.example.sandboxsubject.register.service.RegisterService;

import org.junit.Rule;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@AutoConfigureRestDocs
public class RegisterJunitTest {
    @Autowired
    private MockMvc mockMvc;

    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation();


    private RegisterService mockRegisterService =mock(RegisterService.class);



    @Transactional
    @Test
    public void apidoc_??????_???????????????_?????????_??????() throws Exception{

        profitRequest request = profitRequest.builder()
                    .channelId("300")
                    .profitCreateAt("2022-03-25")
                    .profitAmt(400000).build();

        given(mockRegisterService.insertProfitAmt(request)).willReturn(true);
        mockMvc.perform(post("/api/profit/register")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(JsonUtil.asJsonString(request)))
				.andExpect(status().isOk())
				.andDo(print())
				.andDo(document("apidoc_??????_???????????????_?????????_??????",
						requestFields(
								fieldWithPath("channelId").description("??????ID"),
								fieldWithPath("profitCreateAt").description("?????? ?????? ??????"),
								fieldWithPath("profitAmt").description("?????? ??????")
						),
						responseFields(
								fieldWithPath("data").description("?????? ????????? (EX : {})"),
								fieldWithPath("status").description("???????????? (EX : SUCCESS)"),
								fieldWithPath("msg").description("??????????????? (EX : ?????? ??????.)")
						)));

    }

    @Transactional
	@Test
	public void apidoc_???????????????_???????????????_??????_??????() throws Exception{

		JSONObject test = new JSONObject();
		test.put("creatorCd","101");
		test.put("creatorNm", "?????????");
		test.put("registDate", "2022-02-01");
		test.put("creatorRs", "1.0");
		JSONArray creators = new JSONArray();
		creators.put(test);


		JSONObject request = new JSONObject();
		request.put("chanelId","300");
		request.put("chanelName", "??????");
		request.put("createChannelAt", "2022-03-01");
		request.put("channelRs" , "0.7");
		request.put("sandboxRs", "0.3");
		request.put("creators", creators);


		List<CreatorEntity> rCreators = new ArrayList<>();
		CreatorEntity rCEntity = new CreatorEntity();
		rCEntity.setCreatorCd("101");
		rCEntity.setCreatorNm("?????????");
		Utils util  = new Utils();
		//rCEntity.setRegistDate(util.stringToTimesta??mp("2022-02-01"));
		Timestamp timestamp = Timestamp.valueOf("2022-02-01 09:00:00.0");
		rCEntity.setRegistDate(timestamp);
		BigDecimal res = new BigDecimal("1.0");
		rCEntity.setCreatorRs(res);
		rCreators.add(rCEntity);


    	registerRequest request1 = registerRequest.builder()
						.chanelId("300")
						.chanelName("??????")
						.createChannelAt("2022-03-01")
						.channelRs("0.7")
						.sandboxRs("0.3")
						.creators(rCreators)
						.build();



    	given(mockRegisterService.insertRegisterInfo(request1)).willReturn(true);
        mockMvc.perform(post("/api/contract/register")
				.contentType(MediaType.APPLICATION_JSON)
				//.content(JsonUtil.asJsonString(request1)))
				.content(request.toString()))
				.andDo(print())
				.andExpect(status().isOk())
				.andDo(document("apidoc_???????????????_???????????????_??????_??????",
						requestFields(
								fieldWithPath("chanelId").description("?????? ID"),
								fieldWithPath("chanelName").description("?????? ???"),
								fieldWithPath("createChannelAt").description("?????? ?????? ??????"),
								fieldWithPath("channelRs").description("?????? ??????"),
								fieldWithPath("sandboxRs").description("?????? ??????"),
								fieldWithPath("creators[].creatorCd").description("??????????????? ?????? ??????"),
								fieldWithPath("creators[].creatorRs").description("??????????????? ??????"),
								fieldWithPath("creators[].creatorNm").description("??????????????? ???"),
								fieldWithPath("creators[].registDate").description("?????? ?????? ??????")
						),
						responseFields(
								fieldWithPath("data").description("?????? ????????? (EX : {})"),
								fieldWithPath("status").description("???????????? (EX : SUCCESS)"),
								fieldWithPath("msg").description("??????????????? (EX : ?????? ??????.)")
						)));


	}



}
