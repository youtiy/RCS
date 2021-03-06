package com.koumi.framework.product.rcs.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.databind.ObjectMapper;


public class JacksonUtils {

	private static ObjectMapper mapper;
	private static String defaultPattern = "yyyy-MM-dd";

	/**
	 * 获取ObjectMapper实例
	 * 
	 * @param createNew
	 *            方式：true，新实例；false,存在的mapper实例
	 * @return
	 */
	public static synchronized ObjectMapper getMapperInstance(boolean createNew) {
		if (createNew) {
			return new ObjectMapper();
		} else if (mapper == null) {
			mapper = new ObjectMapper();
		}
		return mapper;
	}

	/**
	 * 将java对象转换成json字符串
	 * 
	 * @param obj
	 *            准备转换的对象
	 * @return json字符串
	 * @throws Exception
	 */
	public static String toJson(Object obj) throws Exception {
		try {
			ObjectMapper objectMapper = getMapperInstance(false);
			String json = objectMapper.writeValueAsString(obj);
			return json;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	/**
	 * 将java对象转换成json字符串,处理日期类型
	 * @param obj
	 * @param dateFormte
	 * @return
	 * @throws Exception
	 */
	public static String toJson(Object obj, String dateFormte)
			throws Exception {
		try {
			ObjectMapper objectMapper = getMapperInstance(true);
			dateFormte = StringUtils.isEmpy(dateFormte) ? defaultPattern : dateFormte;
			DateFormat dateFormat = new SimpleDateFormat(dateFormte);
			objectMapper.setDateFormat(dateFormat );
			String json = objectMapper.writeValueAsString(obj);
			return json;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	/**
	 * 将java对象转换成json字符串
	 * 
	 * @param obj
	 *            准备转换的对象
	 * @param createNew
	 *            ObjectMapper实例方式:true，新实例;false,存在的mapper实例
	 * @return json字符串
	 * @throws Exception
	 */
	public static String toJson(Object obj, Boolean createNew)
			throws Exception {
		try {
			ObjectMapper objectMapper = getMapperInstance(createNew);
			String json = objectMapper.writeValueAsString(obj);
			return json;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	/**
	 * 将json字符串转换成java对象
	 * 
	 * @param json
	 *            准备转换的json字符串
	 * @param cls
	 *            准备转换的类
	 * @return
	 * @throws Exception
	 */
	public static <T> T jsonToBean(String json, Class<T> valueType) throws Exception {
		try {
			ObjectMapper objectMapper = getMapperInstance(false);
			return objectMapper.readValue(json, valueType);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	/**
	 * 将json字符串转换成java对象
	 * 
	 * @param json
	 *            准备转换的json字符串
	 * @param cls
	 *            准备转换的类
	 * @param createNew
	 *            ObjectMapper实例方式:true，新实例;false,存在的mapper实例
	 * @return
	 * @throws Exception
	 */
	public static <T> T jsonToBean(String json, Class<T> cls, Boolean createNew)
			throws Exception {
		try {
			ObjectMapper objectMapper = getMapperInstance(createNew);
			T vo = objectMapper.readValue(json, cls);
			return vo;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
}
