package helper;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import io.restassured.authentication.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import org.springframework.stereotype.Component;
import java.io.File;
import java.util.Map;

/***
 * This class contains method required to create the Request using Rest Assured
 */
@Component
public class RestAssuredHelper {

    private final RequestSpecBuilder requestBuilder = new RequestSpecBuilder();
    private final BasicAuthScheme basicAuth = new BasicAuthScheme();
    private final PreemptiveBasicAuthScheme premptAuth = new PreemptiveBasicAuthScheme();
    private final FormAuthScheme formAuth = new FormAuthScheme();
    private final OAuth2Scheme oAuth2 = new OAuth2Scheme();
    private final OAuthScheme oAuth = new OAuthScheme();



    /***
     * This method is used to add Basic Authentication to Request builder.
     * @param userName - Username for Authentication
     * @param password - Password for Authentication
     */
    public void setBasicAuth(String userName, String password) {
        basicAuth.setUserName(userName);
        basicAuth.setPassword(password);
        requestBuilder.setAuth(basicAuth);
    }

    /***
     * This method is used to add Preempt Authentication to the Request Builder.
     * @param userName - Username for Authentication
     * @param password - Password for Authentication
     */
    public void setPremptAuth(String userName, String password) {
        premptAuth.setUserName(userName);
        premptAuth.setPassword(password);
        requestBuilder.setAuth(premptAuth);
    }

    /***
     * This method is used to add Form Authentication to the Request Builder.
     * @param userName - Username for Authentication
     * @param password - Password for Authentication
     */
    public void setFormAuth(String userName, String password) {
        formAuth.setUserName(userName);
        formAuth.setPassword(password);
        requestBuilder.setAuth(formAuth);
    }

    /***
     * This method is used to Set oAUth2 authorization to the Request Builder.
     * @param accesstoken - Access Token for Authorization
     */
    public void setoAuth2(String accesstoken) {
        oAuth2.setAccessToken(accesstoken);
        requestBuilder.setAuth(oAuth2);
    }

    /***
     * This method is used to set oAuth Authorization to the Request Builder.
     * @param cosumerKey - Consumer Key
     * @param consumerSecret - Consumer Secret
     * @param accessToken - Access Token
     * @param tokenSecret - Secret Token
     */
    public void setoAuth(String cosumerKey, String consumerSecret, String accessToken, String tokenSecret) {
        oAuth.setConsumerKey(cosumerKey);
        oAuth.setConsumerSecret(consumerSecret);
        oAuth.setAccessToken(accessToken);
        oAuth.setSecretToken(tokenSecret);
        requestBuilder.setAuth(oAuth);
    }

    /***
     * This method is use to add cookies to the Request Builder.
     * @param cookies - Map of Cookies
     */
    public void addCookies(Map<String, ?> cookies) {
        requestBuilder.addCookies(cookies);
    }

    /***
     * This method is used to add Headers to the Request Builder.
     * @param headers - Map of Headers
     */
    public void addHeaders(Map<String, String> headers) {
        requestBuilder.addHeaders(headers);
    }

    public void addHeader(String headerKey, String headerValue) {

        requestBuilder.addHeader(headerKey, headerValue);
    }

    /***
     * This method is used to add Query parameters to the Request Builder.
     * @param parameters - Map of Query Parameters
     */
    public void addQueryParameters(Map<String, String> parameters) {
        requestBuilder.addQueryParams(parameters);
    }

    public void addQueryParameter(String parameterKey, String parameterName) {
        requestBuilder.addQueryParam(parameterKey, parameterName);
    }

    /**
     * This method is used to add Form parameters to the Request Builder.
     *
     * @param parameters - Map of form Parameters
     */
    public void addFormParameters(Map<String, String> parameters) {
        requestBuilder.addFormParams(parameters);
    }

    public void addFormParams(String Key, String value) {
        requestBuilder.addFormParam(Key, value);
    }


    /***
     * This method is used to add Path parameters to the Request Builder.
     * @param parameters - Map of Path Parameters
     */
    public void addPathParameters(Map<String, String> parameters) {
        requestBuilder.addPathParams(parameters);
    }

    public void addPathParameter(String parameterKey, String parameterName) {
        requestBuilder.addPathParam(parameterKey, parameterName);
    }

    /***
     * This method is used to set string body to the Request Builder.
     * @param body - Body
     */
    public void addBody(String body) {
        requestBuilder.setBody(body);
    }

    public void addBody(Object payload) {
        requestBuilder.setBody(payload);
    }

    public void addBody(File body) {
        requestBuilder.setBody(body);
    }

    /***
     * This method is used to add multipart parameter to the Request Builder.
     * @param filePath - Path of the file to be passed as parameter
     */
    public void addMultiLine(String filePath) {
        requestBuilder.addMultiPart(new File(filePath));
    }


    /**
     * This method is used to fetch the response using Get Request
     *
     * @param requestSpecification - Request specification
     * @return - Returns response
     */

    public Response getRequest(FilterableRequestSpecification requestSpecification) {
        return requestSpecification.when()
                .log().all()
                .get()
                .prettyPeek();
    }

    /**
     * This method is used to fetch the response using Post Request
     *
     * @param requestSpecification - Request specification
     * @return - Returns response
     */
    public Response postRequest(FilterableRequestSpecification requestSpecification) {
        return requestSpecification
                .log().all().when()
                .post()
                .prettyPeek();
    }

    /**
     * This method is used to fetch the response using Put Request
     *
     * @param requestSpecification - Request Specification
     * @return - Returns response
     */
    public Response putRequest(FilterableRequestSpecification requestSpecification) {
        return requestSpecification.when()
                .log().all()
                .put()
                 .prettyPeek();
    }

    /**
     * This method is used to fetch the response using Patch Request
     *
     * @param requestSpecification - Request Specification
     * @return - Returns response
     */
    public Response patchRequest(FilterableRequestSpecification requestSpecification) {
        return requestSpecification
                .when()
                .log().all()
                .patch()
                .prettyPeek();
    }

    /**
     * This method is used to fetch the response using Delete Request
     *
     * @param requestSpecification - Request Specification
     * @return - Returns response
     */
    public Response deleteRequest(FilterableRequestSpecification requestSpecification) {
        return requestSpecification
                .when()
                .log().all()
                .delete()
                .prettyPeek();
    }

    public <T> T mapToPojo(Response response, Class T) throws Exception {
        try {
            ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return (T) objectMapper.readValue(response.getBody().asString(), T);
        }catch(JsonProcessingException je){
            throw new Exception(je.getMessage());
        }
    }

    public String mapToJson( Object obj) throws JsonProcessingException {
        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        return objectWriter.writeValueAsString(obj);
    }
}