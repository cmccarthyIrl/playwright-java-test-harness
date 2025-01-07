package utils;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.Playwright;

import java.util.HashMap;
import java.util.Map;

import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.APIRequest;
import java.util.HashMap;
import java.util.Map;

public class RequestContext {

    private APIRequestContext request;

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

    /**
     * This method disposes of the current APIRequestContext, if it exists.
     * It ensures that the resources associated with the context are released.
     */
    protected void disposeAPIRequestContext() {
        if (request != null) {
            // Dispose of the request context to free resources
            request.dispose();
            request = null;
        }
    }

    /**
     * This method closes the Playwright instance, if it exists.
     * It ensures that any resources associated with Playwright are properly cleaned up.
     *
     * @param playwright The Playwright instance to close.
     */
    protected void closePlaywright(Playwright playwright) {
        if (playwright != null) {
            // Close the Playwright instance to release resources
            playwright.close();
        }
    }
}
