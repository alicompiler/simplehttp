package com.baghdadit.simplehttp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import static org.junit.Assert.*;


public class SimpleHttpResponseTest {

    @Test
    public void should_return_string_response() {
        String testResponse = "{\"success\" : true}";
        SimpleHttpResponse response = new SimpleHttpResponse(testResponse);
        String responseAsString = response.asString();
        assertEquals(testResponse, responseAsString);
    }

    @Test
    public void should_return_response_as_json_object() throws JSONException {
        String testResponse = "{\"success\" : true}";
        SimpleHttpResponse response = new SimpleHttpResponse(testResponse);
        JSONObject responseAsJsonObject = response.asJsonObject();
        assertTrue(responseAsJsonObject.getBoolean("success"));
    }

    @Test
    public void should_return_response_as_json_array() throws JSONException {
        String testResponse = "[1,2,3]";
        SimpleHttpResponse response = new SimpleHttpResponse(testResponse);
        JSONArray responseAsJsonArray = response.asJsonArray();
        assertEquals(2, responseAsJsonArray.getInt(1));
    }

    @Test(expected = JSONException.class)
    public void should_throw_exception_when_cannot_make_json_object_as_response() throws JSONException {
        String testResponse = "";
        SimpleHttpResponse response = new SimpleHttpResponse(testResponse);
        response.asJsonObject();
    }

    @Test(expected = JSONException.class)
    public void should_throw_exception_when_cannot_make_json_array_as_response() throws JSONException {
        String testResponse = "";
        SimpleHttpResponse response = new SimpleHttpResponse(testResponse);
        response.asJsonArray();
    }

}
