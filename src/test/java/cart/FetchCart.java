package cart;

import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import org.testng.Assert;
import io.restassured.path.json.JsonPath;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.Data;
import com.example.ErrorInfo;
import com.example.MerchantDetails;
import io.restassured.specification.RequestSpecification;

import org.junit.jupiter.api.Assertions;

public class FetchCart {
    private RequestSpecification request;
    private RequestSpecification request_without_cart_session_token;
    private RequestSpecification request_with_expired_cart_session_token;

    private String getCookieValue() {
        return "auth-cookie=" + "visit-id=vs_2QpJBI18C656hDdoXaTNXPl2j90";
    }

    private String getCartSessoinTokenValue() {
        // 07234ea87164b7ea34260b724673b8ce
        return "07234ea87164b7ea34260b724673b8ce";
    }

    @BeforeClass
    public void setup() {
        request = RestAssured.given();
        request.baseUri("https://checkout-api.stagingsimpl.com");
        request.basePath("/api/v1/cart");

        Header sessionTokenHeader = new Header("cart-session-token",
                getCartSessoinTokenValue());
        Header cookieHeader = new Header("Cookie", getCookieValue());
        request.header(sessionTokenHeader).header(cookieHeader);

        request_without_cart_session_token = RestAssured.given();
        request_without_cart_session_token.baseUri("https://checkout-api.stagingsimpl.com");
        request_without_cart_session_token.basePath("/api/v1/cart");
        request_without_cart_session_token.header(cookieHeader);

        request_with_expired_cart_session_token = RestAssured.given();
        request_with_expired_cart_session_token.baseUri("https://checkout-api.stagingsimpl.com");
        request_with_expired_cart_session_token.basePath("/api/v1/cart");
        Header expired_sessionTokenHeader = new Header("cart-session-token",
                "251dd245f0c7a78f01340001ea1556ed");
        request_with_expired_cart_session_token.header(cookieHeader).header(expired_sessionTokenHeader);

    }

    @Test
    public void CartFetch_VerifyStatusCode() {
        Response response = request.when().get();

        int statusCode = response.getStatusCode();
        int expectedStatusCode = 200;
        Assert.assertEquals(statusCode, expectedStatusCode, "Status code mismatch");
    }

    @Test
    public void CartFetch_ValidateSuccessField() {
        Response response = request.when().get();

        JsonPath responseBody = response.jsonPath();
        boolean successFieldValue = responseBody.getBoolean("success");
        Assert.assertTrue(successFieldValue, "'success' field is not true");
    }

    @Test
    public void CartFetch_ValidateDataField() {

        Response response = request.when().get();
        JsonPath responseBody = response.jsonPath();
        Object dataObject = responseBody.get("data");
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String dataJsonString = objectMapper.writeValueAsString(dataObject);

            // Convert the JSON string to a Data object
            Data data = objectMapper.readValue(dataJsonString, Data.class);
            // Assert the fields using getters from the Data object
            // Assertions.assertNotNull(data.getApplicable_payment_options());
            Assertions.assertNotNull(data.getCart_session_token());
            Assertions.assertNotNull(data.getCurrency());
            // Assertions.assertNull(data.getCustomAttributes());
            Assertions.assertTrue(data.getDiscount_details() instanceof List);
            Assertions.assertTrue(data.getDiscounts() instanceof List);

            // Assert experiments as a list
            Assertions.assertTrue(data.getExperiments() instanceof List);

            // Assert line items as a list
            Assertions.assertTrue(data.getLine_items() instanceof List);

            // Assert fees as a list
            Assertions.assertTrue(data.getFees() instanceof List);

            // Assert the fields inside experiments list
            Assertions.assertTrue(data.getExperiments() instanceof List);

            // Assert the fields inside line items list
            Assertions.assertTrue(data.getLine_items() instanceof List);

            // Assert merchant details
            MerchantDetails merchantDetails = data.getMerchant_details();
            Assertions.assertTrue(merchantDetails.getIcon() instanceof String);
            Assertions.assertTrue(merchantDetails.getId() instanceof String);
            Assertions.assertTrue(merchantDetails.getIntegration_type() instanceof String);
            Assertions.assertTrue(merchantDetails.getLogo() instanceof String);
            Assertions.assertTrue(merchantDetails.getMerchant_policies() instanceof List);
            Assertions.assertTrue(merchantDetails.getName() instanceof String);

            // Assert shipping lines resource
            Assertions.assertTrue(data.getShipping_lines_resource() instanceof Object);

            // Assert other fields
            Assertions.assertTrue(data.getShop_domain() instanceof String);
            Assertions.assertTrue(data.getTaxes() instanceof List);
            Assertions.assertTrue(data.isTaxes_included() == true || data.isTaxes_included() == false);
            Assertions.assertNotNull(data.getTotal_discount());
            Assertions.assertNotNull(data.getTotal_item_price());
            Assertions.assertNotNull(data.getTotal_price());

        } catch (Exception e) {
            // e.printStackTrace();
            Assertions.fail("Test failed due to an exception: " + e.getMessage());
        }

    }

    @Test
    public void CartFetch_VerifyStatusCode_cart_session_token_absent() {
        Response response = request_without_cart_session_token.when().get();

        int statusCode = response.getStatusCode();
        int expectedStatusCode = 401;
        Assert.assertEquals(statusCode, expectedStatusCode, "Status code mismatch");
    }

    @Test
    public void CartFetch_ValidateSuccessField_cart_session_token_absent() {
        Response response = request_without_cart_session_token.when().get();

        JsonPath responseBody = response.jsonPath();
        boolean successFieldValue = responseBody.getBoolean("success");
        Assert.assertFalse(successFieldValue, "'success' field is not true");
    }

    @Test
    public void CartFetch_ValidateErrorField_cart_session_token_absent() {

        Response response = request_without_cart_session_token.when().get();
        JsonPath responseBody = response.jsonPath();
        Object dataObject = responseBody.get("error");
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String errorJsonString = objectMapper.writeValueAsString(dataObject);
            ErrorInfo errorData = objectMapper.readValue(errorJsonString, ErrorInfo.class);
            // Assert the code and message values
            Assertions.assertEquals("cart_token_absent", errorData.getCode(), "Code mismatch");
            Assertions.assertEquals("cart token is absent", errorData.getMessage(), "Message mismatch");
        } catch (Exception e) {
            // e.printStackTrace();
            Assertions.fail("Test failed due to an exception: " + e.getMessage());
        }
    }

    @Test
    public void CartFetch_VerifyStatusCode_cart_session_token_expired() {
        Response response = request_with_expired_cart_session_token.when().get();

        int statusCode = response.getStatusCode();
        int expectedStatusCode = 401;
        Assert.assertEquals(statusCode, expectedStatusCode, "Status code mismatch");
    }

    @Test
    public void CartFetch_ValidateSuccessField_cart_session_token_expired() {
        Response response = request_with_expired_cart_session_token.when().get();

        JsonPath responseBody = response.jsonPath();
        boolean successFieldValue = responseBody.getBoolean("success");
        Assert.assertFalse(successFieldValue, "'success' field is not true");
    }

    @Test
    public void CartFetch_ValidateErrorField_cart_session_token_expired() {

        Response response = request_with_expired_cart_session_token.when().get();
        JsonPath responseBody = response.jsonPath();
        Object dataObject = responseBody.get("error");
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String errorJsonString = objectMapper.writeValueAsString(dataObject);
            ErrorInfo errorData = objectMapper.readValue(errorJsonString, ErrorInfo.class);
            // Assert the code and message values
            Assertions.assertEquals("cart_token_expired", errorData.getCode(), "Code mismatch");
            Assertions.assertEquals("cart token is expired", errorData.getMessage(), "Message mismatch");
        } catch (Exception e) {
            // e.printStackTrace();
            Assertions.fail("Test failed due to an exception: " + e.getMessage());
        }
    }
}
