package com.cmccarthyirl.api.utils;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.Playwright;

import java.util.HashMap;
import java.util.Map;

public class RequestContext {


    /**
     * This method initializes and returns an APIRequestContext for interacting with the OpenWeatherMap API.
     * It configures the base URL for the API and sets default headers for the requests.
     *
     * @param playwright The Playwright instance to create the request context.
     * @return The created APIRequestContext.
     */
    protected APIRequestContext getWeatherAPIContext(Playwright playwright) {
        // Set the headers for the API requests
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        // Create and return a new APIRequestContext with the specified base URL and headers
        return playwright.request().newContext(new APIRequest.NewContextOptions()
                .setBaseURL("http://api.openweathermap.org")  // Set the base URL for the weather API
                .setExtraHTTPHeaders(headers)  // Set the default headers for the requests
        );
    }
}
