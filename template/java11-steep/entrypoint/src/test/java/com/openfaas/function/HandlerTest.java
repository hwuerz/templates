package com.openfaas.function;

import com.openfaas.model.IResponse;
import com.openfaas.model.Request;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

 public class HandlerTest {
     @Test public void testAppHasAGreeting() {
         Handler handler = new Handler();
         String body = "{\"id\":\"faas echo\",\"path\":\"example-function\",\"arguments\":[{\"id\":\"input\",\"variable\":{\"id\":\"sleep_seconds\",\"value\":\"10\"},\"type\":\"argument\",\"dataType\":\"integer\"},{\"id\":\"output\",\"variable\":{\"id\":\"output_string\",\"value\":\"/tmp/alismubfqkjhbpwevuja/alismujfqkjhbpwevunq\"},\"type\":\"output\",\"dataType\":\"integer\"}],\"runtime\":\"faas\",\"runtimeArgs\":[]}";
         Request request = new Request(body, new HashMap<>());
         IResponse response = handler.Handle(request);
         assertEquals(200, response.getStatusCode());
         // You can use this test as long as you develop the template.
         // But it MUST NOT be included in the final version.
         // The custom function will return anything and this test leads to a failure during deployment.
//         assertEquals("Hello, world!", response.getBody());
     }
 }
