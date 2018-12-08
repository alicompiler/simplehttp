package com.baghdadit.simplehttp;

import com.baghdadit.simplehttp.config.HttpActionType;
import com.baghdadit.simplehttp.config.RequestConfig;
import com.baghdadit.simplehttp.config.RequestConfigBuilder;
import com.baghdadit.simplehttp.config.RequestParameters;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class RequestConfigBuilderTest {

    private RequestConfigBuilder builder;

    @Before
    public void before() {
        this.builder = new RequestConfigBuilder();
    }

    @Test
    public void should_use_GET_as_default() {
        RequestConfig requestConfig = this.builder.build();
        assertEquals(HttpActionType.GET, requestConfig.getActionType());
    }

    @Test
    public void should_have_no_parameters_and_headers_as_default() {
        RequestConfig requestConfig = this.builder.build();
        assertEquals(0, requestConfig.getParameters().keys().size());
        assertEquals(0, requestConfig.getHeaders().keys().size());
    }


    @Test
    public void should_able_to_add_param_and_header() {
        builder.parameter("KEY", "value");
        builder.header("Content-Type", "application/json");
        RequestConfig config = builder.build();
        assertEquals("value", config.getParameters().get("KEY"));
        assertEquals("application/json", config.getHeaders().get("Content-Type"));
    }

    @Test
    public void should_able_to_attach_file() {
        builder.attach(new File("name"), "name", "media-type");
        RequestConfig config = builder.build();
        assertEquals("name", config.getAttachments().all().get(0).getName());
    }

    @Test
    public void should_return_simple_config_with_GET_request_and_specific_url() {
        RequestConfig requestConfig = RequestConfigBuilder.simpleGETConfig("http://google.com");
        assertEquals(HttpActionType.GET, requestConfig.getActionType());
        assertEquals("http://google.com", requestConfig.getUrl());
    }

    @Test
    public void should_return_simple_config_with_GET_request_and_specific_url_and_some_params() {
        RequestParameters parameters = new RequestParameters().add("KEY1", "value").add("KEY2", "value");
        RequestConfig requestConfig = RequestConfigBuilder.simpleGETConfig("http://google.com", parameters);
        assertEquals(HttpActionType.GET, requestConfig.getActionType());
        assertEquals("http://google.com", requestConfig.getUrl());
        assertEquals(2, requestConfig.getParameters().keys().size());
    }

    @Test
    public void should_return_simple_config_with_POST_request_and_specific_url() {
        RequestConfig requestConfig = RequestConfigBuilder.simplePOSTConfig("http://google.com");
        assertEquals(HttpActionType.POST, requestConfig.getActionType());
        assertEquals("http://google.com", requestConfig.getUrl());
    }

    @Test
    public void should_return_simple_config_with_POST_request_and_specific_url_and_some_params() {
        RequestParameters parameters = new RequestParameters().add("KEY1", "value").add("KEY2", "value");
        RequestConfig requestConfig = RequestConfigBuilder.simplePOSTConfig("http://google.com", parameters);
        assertEquals(HttpActionType.POST, requestConfig.getActionType());
        assertEquals("http://google.com", requestConfig.getUrl());
        assertEquals(2, requestConfig.getParameters().keys().size());
    }
}
