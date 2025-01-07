package utils;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.Playwright;

import java.util.HashMap;
import java.util.Map;

public class RequestContext {

    private APIRequestContext request;

    protected APIRequestContext getWeatherAPIContext(Playwright playwright) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        return playwright.request().newContext(new APIRequest.NewContextOptions()
                .setBaseURL("http://api.openweathermap.org")
                .setExtraHTTPHeaders(headers)
        );
    }

    protected void disposeAPIRequestContext() {
        if (request != null) {
            request.dispose();
            request = null;
        }
    }

    protected void closePlaywright(Playwright playwright) {
        if (playwright != null) {
            playwright.close();
        }
    }
}
