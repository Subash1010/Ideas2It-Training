package com.I2I.aws.Util;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.S3Event;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class ConversionUtils {

	public static int INDEX_FACTOR = 4;

	public static String handleRequest(S3Event event, Context context) {
		System.out.println("event received");
		return "";
	}

	public static String main(String[] args) {
		try {
			JSONObject xmlJsonObj = XML.toJSONObject("");
			String convertedJson = xmlJsonObj.toString(INDEX_FACTOR);
			return convertedJson;

		} catch (JSONException exception) {
			log.error("JSON Parsing Error" + exception);
		}
		return "";
	}
}
