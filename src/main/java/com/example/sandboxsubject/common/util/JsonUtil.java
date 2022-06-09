package com.example.sandboxsubject.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
    /**
	 * 객체를 Json String으로 변환해준다.
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static String asJsonString(final Object obj) throws Exception{
		return new ObjectMapper().writeValueAsString(obj);
	}
}
