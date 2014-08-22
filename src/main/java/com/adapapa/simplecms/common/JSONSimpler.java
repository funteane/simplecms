package com.adapapa.simplecms.common;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.dreammore.framework.user.model.Enumable;
import com.dreammore.framework.user.model.NameValuePair;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class JSONSimpler {
	static GsonBuilder gbuilder = new GsonBuilder().disableHtmlEscaping();// 不转义html标记
	static Gson gson = gbuilder.setDateFormat("yyyy-MM-dd HH:mm:ss").create();// 格式时间
	static List<NameValuePair> pairs = new LinkedList<NameValuePair>();

	public static String toJson(Object beanClazz) {

		return gson.toJson(beanClazz);
	}

	public static String toJson(Object beanClazz, Type type) {
		return gson.toJson(beanClazz, type);
	}

	@SuppressWarnings("unchecked")
	public static Object fromJson(String jsonStr, Class beanClazz) {

		return gson.fromJson(jsonStr, beanClazz);
	}

	public static Object fromJson(String jsonStr, Type type) {

		return gson.fromJson(jsonStr, type);
	}
	
	public static  String toJsonFromEnum(Enumable ... enumables){
		pairs.clear();
		for(Enumable enumable : enumables){
			pairs.add(new NameValuePair(enumable.getValue(), enumable.getName()));
		}
		return gson.toJson(pairs);
		
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		List array = new ArrayList();
		HashMap<String, String> hm = new HashMap<String, String>();
		List array1 = new ArrayList();
		array1.add("2012-07-16");
		array1.add("2");
		array.add(array1);
		List array2 = new ArrayList();
		array2.add("2012-07-17");
		array2.add("3");
		array.add(array2);
		hm.put("keyword", array.toString());
		String json = JSONSimpler.toJson(hm);
		System.out.println(hm);
		System.out.println(json);
		Type type = new TypeToken<HashMap<String, String>>() {
		}.getType();
		HashMap<String, String> hm2 = (HashMap<String, String>) JSONSimpler.fromJson(json, type);

		for (Iterator iter = hm2.keySet().iterator(); iter.hasNext();) {
			String key = (String) iter.next();
			System.out.println("--" + key + "=" + hm2.get(key));

		}

	}
}
