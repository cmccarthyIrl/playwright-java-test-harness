package com.cmccarthyirl.api.test;

import com.cmccarthyirl.common.LogManager;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WeatherAPITest extends BaseAPITests {

    // API key for accessing the weather service
    private final String appId = "0a1b11f110d4b6cd43181d23d724cb94";
    private static final LogManager log = new LogManager(WeatherAPITest.class);

    /**
     * Test to get the weather information for Sydney.
     * This test makes a GET request to the weather API and checks the response.
     */
    @Test
    public void testGetWeatherForSydney() {
        // Prepare location and appid for the request
        String location = "Sydney";

        // Make the GET request to the weather API with the location and appid as query parameters
        APIResponse response = weatherContext().get("/data/2.5/weather",
                RequestOptions.create()
                        .setQueryParam("q", location)  // Query parameter for location
                        .setQueryParam("appid", appId));  // Query parameter for the API key (appid)

        // Print the response body to the console (useful for debugging)
        log.info("The weather for Sydney is: " + response.text());

        // Assert that the response status is OK (200-299 range)
        Assert.assertTrue(response.ok(), "Response should be OK");

        // Assert that the status code is 200, meaning the request was successful
        Assert.assertEquals(response.status(), 200, "Status code should be 200");

        // Parse the response body and assert that it contains the location (Sydney)
        String body = response.text();
        Assert.assertTrue(body.contains(location), "Response body should contain the location");
    }

    /**
     * Test to get the weather information for Dublin.
     * Similar to the Sydney test, but for a different location (Dublin).
     */
    @Test
    public void testGetWeatherForDublin() {
        // Prepare location and appid for the request
        String location = "Dublin";

        // Make the GET request to the weather API with the location and appid as query parameters
        APIResponse response = weatherContext().get("/data/2.5/weather",
                RequestOptions.create()
                        .setQueryParam("q", location)  // Query parameter for location
                        .setQueryParam("appid", appId));  // Query parameter for the API key (appid)

        // Print the response body to the console (useful for debugging)
        log.info("The weather for Dublin is: " + response.text());

        // Assert that the response status is OK (200-299 range)
        Assert.assertTrue(response.ok(), "Response should be OK");

        // Assert that the status code is 200, meaning the request was successful
        Assert.assertEquals(response.status(), 200, "Status code should be 200");

        // Parse the response body and assert that it contains the location (Dublin)
        String body = response.text();
        Assert.assertTrue(body.contains(location), "Response body should contain the location");
    }
}
