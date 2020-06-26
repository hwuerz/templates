package com.openfaas.function;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.kotlin.KotlinModule;
import com.openfaas.model.IHandler;
import com.openfaas.model.IRequest;
import com.openfaas.model.IResponse;
import com.openfaas.model.Response;
import model.processchain.Executable;

public class Handler implements IHandler {

    io.steep.faas.Handler steepHandler = new io.steep.faas.Handler();
    ObjectMapper objectMapper = new ObjectMapper();

    public Handler() {
        // Support for Kotlin data class.
        objectMapper.registerModule(new KotlinModule());
    }

    public IResponse Handle(IRequest req) {

        Response res = new Response();
        try {
            // Json to object mapping might throw an exception if the content could not be parsed.
            String body = req.getBody();
            Executable executable = objectMapper.readValue(body, Executable.class);

            try {
                // The Handle() method might fail.
                String steepResponse = steepHandler.Handle(executable);
                res.setStatusCode(200);
                res.setBody(steepResponse);

            } catch (Exception e) { // Function code failed.
                e.printStackTrace();
                res.setStatusCode(500);
                res.setBody(e.getMessage());
            }
        } catch (JsonProcessingException e) { // Input JSON mapping failed.
            e.printStackTrace();
            res.setStatusCode(400);
            res.setBody("Body does not contain an Executable object: " + e.getMessage() +
                    " You passed " + req.getBody());
        } catch (Exception e) { // Should never happen.
            e.printStackTrace();
            res.setStatusCode(500);
            res.setBody("Unknown Error: " + e.getMessage());
        }

	    return res;
    }
}
