package test;

import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WeatherAPITest extends BaseAPITests {

   private final String appid = "0a1b11f110d4b6cd43181d23d724cb94";

    @Test
    public void testGetWeatherForSydney() {
        // Prepare location and appid
        String location = "Sydney";


        APIResponse response = weatherContext().get("/data/2.5/weather",
                RequestOptions.create().setQueryParam("q", location).setQueryParam("appid", appid));

        // Print the response body (for debugging purposes)
        System.out.println("RESPONSE " + response.text());

        // Assert response status
        Assert.assertTrue(response.ok(), "Response should be OK");
        Assert.assertEquals(response.status(), 200, "Status code should be 200");

        // Parse and assert the JSON body
        String body = response.text();
        Assert.assertTrue(body.contains(location), "Response body should contain the location");
    }

    @Test
    public void testGetWeatherForDublin() {
        // Prepare location and appid
        String location = "Dublin";


        APIResponse response = weatherContext().get("/data/2.5/weather",
                RequestOptions.create().setQueryParam("q", location).setQueryParam("appid", appid));

        // Print the response body (for debugging purposes)
        System.out.println("RESPONSE " + response.text());

        // Assert response status
        Assert.assertTrue(response.ok(), "Response should be OK");
        Assert.assertEquals(response.status(), 200, "Status code should be 200");

        // Parse and assert the JSON body
        String body = response.text();
        Assert.assertTrue(body.contains(location), "Response body should contain the location");
    }
}
