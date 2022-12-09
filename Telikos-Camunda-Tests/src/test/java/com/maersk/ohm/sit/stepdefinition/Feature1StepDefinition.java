package com.maersk.ohm.sit.stepdefinition;

import com.jayway.jsonpath.JsonPath;
import com.maersk.ohm.sit.config.ConfigReader;
import com.maersk.ohm.sit.springapplication.CucumberSpringApplication;
import helper.CustomSoftAssertions;
import helper.RestAssuredHelper;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import models.response.*;
import net.serenitybdd.rest.SerenityRest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootTest(classes = CucumberSpringApplication.class)
public class Feature1StepDefinition {
    Scenario scenario;
    String processInstanceId;
    FilterableRequestSpecification requestSpecification = (FilterableRequestSpecification) SerenityRest.given();
    Response response;
    Response responseRunningStatus;
    Response responseCompletedTasks;
    String requestPayload = null;

    @Autowired
    ConfigReader configReader;

    @Autowired
    RestAssuredHelper restAssuredHelper;

    @Autowired
    CustomSoftAssertions customSoftAssertions;
    BusinessRuleResponse[] businessRuleResponse;
    DomainTaskRuleResponse[] domainTaskRuleResponses;
    BookingVariableResponse bookingVariableResponse;
    JSONParser parser = new JSONParser();
    DomainTaskRulesVariableResponse domainTaskRulesVariableResponse;
    CaptureVariableResponse captureVariableResponse;
    PreCaptureVariableResponse preCaptureVariableResponse;
    String variableId;
    ActivityInstancesResponse activityInstancesResponse;
    private static final String TEST_DATAFILE_PATH = "src/test/resources/requestpayload/";

    @BeforeStep
    public void beforeStep(Scenario scenario) {
        this.scenario = scenario;
        requestSpecification.baseUri(configReader.getBaseURI()).relaxedHTTPSValidation();
    }


    @Then("the status code should be {string}")
    public void the_status_code_should_be(String string) {
        Assert.assertEquals(200, response.getStatusCode());
    }

    @When("user hit the VerifyCurrentRunningStatus api to fetch the process instance id with {string}")
    public void user_hit_the_verify_current_running_status_api_to_fetch_the_process_instance_id_with(String businessKey) throws JSONException, InterruptedException {
        responseRunningStatus= commonGetRequest(configReader.getBaseURIStart(),  "/anchor-flow/process-instance");
        String jsonArrayResponse = JsonPath.read(responseRunningStatus.asString(), "$[?(@.businessKey=='" + businessKey + "')]").toString();
        JSONArray jsonArray = new JSONArray(jsonArrayResponse);
        JSONObject jsonObject1 = jsonArray.getJSONObject(0);
        processInstanceId = jsonObject1.optString("id");
    }

    @Given("a user start the workflow with {string},{string},{string},{string},{string}")
    public void a_user_start_the_workflow_with(String servicePlanNumber, String bookingId, String logisticId, String productCode, String productName) throws IOException {
        requestPayload = readRequestPayload("StartWorkFlow.json", servicePlanNumber, bookingId, logisticId,productCode,productName);
        response= commonPostRequest(requestPayload, configReader.getBaseURIStart(), "/message-process/start/");
        System.out.println("*********************Workflow Started*********************");
    }

    @Given("user verify all the completed task")
    public void user_verify_all_the_completed_task(DataTable dataTable) throws JSONException, InterruptedException {
        List<String> expectedActivityName = new ArrayList<>();
        List<String> actualActivityName;
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> columns : rows) {
            expectedActivityName.add(columns.get("value"));
        }
        responseCompletedTasks= commonGetRequest(configReader.getBaseURIStart()+"/anchor-flow/",processInstanceId,"history/activity-instance");
        actualActivityName = getValuesForGivenKey(responseCompletedTasks.asString(), "activityName");
        customSoftAssertions.compareTwoList(expectedActivityName,actualActivityName);
    }

    @And("verify the booking variable details for {string} {string} {string} {string} {string} {string} {string}")
    public void verify_the_booking_variable(String variableName, String ExpCaptureDataStatus, String validationMessage, String ExpServicePlanNumber, String ExpBookingId, String ExpLogisticId, String variableValue) throws JSONException, IOException, ParseException {
        requestPayload=readRequestPayloadForVariables( "BusinessAndDomainTaskRule.json", variableValue);
        businessRuleResponse = postRuleRequest(requestPayload, "Decision_BusinessRule").getBody().as(BusinessRuleResponse[].class);
        variableId = getIdFromVariable(variableName);
        bookingVariableResponse = getActualVariableDetails(variableId, variableName).getBody().as(BookingVariableResponse.class);
        System.out.println("*********************Verifying the BOOKING VARIABLE details*********************");
        Assert.assertEquals(variableName, bookingVariableResponse.name);
        Assert.assertEquals(Arrays.stream(businessRuleResponse[0].getBusinessRule().getValue().split(":")).toList(), bookingVariableResponse.getValue().getRules().stream().toList());
        Assert.assertEquals(ExpCaptureDataStatus, bookingVariableResponse.getValue().captureData);
        Assert.assertEquals(validationMessage, bookingVariableResponse.getValue().validationMessage);
        Assert.assertEquals(ExpServicePlanNumber, bookingVariableResponse.getValue().getServicePlan().servicePlanNumber);
        Assert.assertEquals(ExpBookingId, bookingVariableResponse.getValue().bookingId);
        Assert.assertEquals(ExpLogisticId, bookingVariableResponse.getValue().logisticId);
    }


    @And("verify the domainTaskRules variable details {string} {string}")
    public void verify_the_domainTaskRules_variable_details(String expVariableName, String variableValue) throws IOException, ParseException, JSONException {
        requestPayload=readRequestPayloadForVariables( "BusinessAndDomainTaskRule.json", variableValue);
        domainTaskRuleResponses = postRuleRequest(requestPayload, "Decision_DomainTaskRule").getBody().as(DomainTaskRuleResponse[].class);
        variableId = getIdFromVariable(expVariableName);
        domainTaskRulesVariableResponse = getActualVariableDetails(variableId, expVariableName).getBody().as(DomainTaskRulesVariableResponse.class);
        System.out.println("*********************Verifying the DOMAINTASKRULES VARIABLE details*********************");
        Assert.assertEquals(Arrays.stream(domainTaskRuleResponses[0].getDomainTaskRule().getValue().split(":")).toList(), domainTaskRulesVariableResponse.getValue());
        Assert.assertEquals(expVariableName, domainTaskRulesVariableResponse.name);
    }


    @And("verify the capture variable details {string} {string}")
    public void verify_the_capture_variable_details(String expVariableName, String flag) throws JSONException {
        variableId = getIdFromVariable(expVariableName);
        captureVariableResponse = getActualVariableDetails(variableId, expVariableName).getBody().as(CaptureVariableResponse.class);
        System.out.println("*********************Verifying the CAPTURE VARIABLE details*********************");
        Assert.assertEquals(expVariableName, captureVariableResponse.name);
        Assert.assertEquals(Boolean.valueOf(flag), captureVariableResponse.value);
    }

    @And("verify the preCapture variable details {string} {string}")
    public void verify_the_preCapture_variable_details(String expVariableName, String flag) throws JSONException {
        variableId = getIdFromVariable(expVariableName);
        preCaptureVariableResponse = getActualVariableDetails(variableId, expVariableName).getBody().as(PreCaptureVariableResponse.class);
        System.out.println("*********************Verifying the PRE-CAPTURE VARIABLE details*********************");
        Assert.assertEquals(expVariableName, preCaptureVariableResponse.name);
        Assert.assertEquals(Boolean.valueOf(flag), preCaptureVariableResponse.value);
    }

    @When("user hit the validation api to resume the workflow with {string},{string},{string},{string},{string}")
    public void user_hit_the_validation_api_to_resume_the_workflow_with_and(String servicePlanNumber, String bookingId, String logisticId, String productCode, String productName) throws IOException {
        requestPayload = readRequestPayload("UI_Validation_Paylload.json", servicePlanNumber, bookingId, logisticId,productCode,productName);
        response= commonPostRequest(requestPayload, configReader.getBaseURIUI(), "/validateBookingBody");
        System.out.println("*********************First : UI-Validation[Manual Intervention]*********************");
        Assert.assertEquals(200, response.getStatusCode());
    }


    @Then("verify the current task running status is {string}")
    public void verify_the_current_task_running_status_is(String taskName) throws InterruptedException {
        response = commonGetRequest(configReader.getBaseURIStart(),  "anchor-flow/process-instance/" + processInstanceId + "/activity-instances");
        activityInstancesResponse = response.getBody().as(ActivityInstancesResponse.class);
        Assert.assertEquals(taskName, activityInstancesResponse.getChildActivityInstances().get(0).getChildActivityInstances().get(0).activityName);
    }

    @When("user hit the fulfilment api to resume the workflow with {string},{string},{string},{string},{string}")
    public void user_hit_the_fulfilment_api_to_resume_the_workflow_with_and(String servicePlanNumber, String bookingId, String logisticId, String productCode, String productName) throws IOException {
        requestPayload = readRequestPayload("UI_Fulfilment_Payload.json", servicePlanNumber, bookingId, logisticId,productCode,productName);
        response= commonPostRequest(requestPayload, configReader.getBaseURIUI(), "/fulfilmentBody");
        System.out.println("*********************Second : UI_Fulfilment[Manual Intervention]*********************");
        Assert.assertEquals(200, response.getStatusCode());
    }

    @After
    public void afterAll() {
        if (customSoftAssertions.getSoftAssert() != null) {
            customSoftAssertions.assertAll();
        }
    }

    public List<String> getValuesForGivenKey(String jsonArrayStr, String key) throws JSONException {
        JSONArray jsonArray = new JSONArray(jsonArrayStr);
        return IntStream.range(0, jsonArray.length())
                .mapToObj(index -> {
                    try {
                        return ((JSONObject) jsonArray.get(index)).optString(key);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    return null;
                })
                .collect(Collectors.toList());
    }

    public String getIdFromVariable(String variableName) throws JSONException {
        response = SerenityRest.given()
                .baseUri("http://localhost:18080/")
                .header("Content-Type", "application/json")
                .get("anchor-flow/variable-instance/?variableName=" + variableName)
                .prettyPeek();
        String jsonArrayResponse = JsonPath.read(response.asString(), "$[?(@.processInstanceId=='" + processInstanceId + "')]").toString();
        JSONArray jsonArray = new JSONArray(jsonArrayResponse);
        JSONObject jsonObject1 = jsonArray.getJSONObject(0);
        return jsonObject1.optString("id");
    }

    public Response postRuleRequest(String payload, String ruleName) {
        return SerenityRest.given()
                .baseUri("http://localhost:18080/")
                .header("Content-Type", "application/json")
                .body(payload)
                .post("anchor-flow/decision-definition/key/" + ruleName + "/evaluate");
    }

    public Response getActualVariableDetails(String variableId, String variableName) {
        return SerenityRest.given()
                .baseUri("http://localhost:18080/")
                .header("Content-Type", "application/json")
                .get("anchor-flow/variable-instance/" + variableId + "/?variableName=" + variableName);
    }

    public Response commonPostRequest(String requestPayload, String baseURI, String resourcePath) throws IOException {

        return SerenityRest.given()
                .baseUri(baseURI)
                .header("Content-Type", "application/json")
                .body(requestPayload)
                .post(resourcePath)
                .prettyPeek();
    }
    public String readRequestPayload(String fileName, String servicePlanNumber, String bookingId, String logisticId, String productCode, String productName) throws IOException {
        File file = null;
        file = ResourceUtils.getFile("src/test/resources/requestpayload/" + fileName);
        String requestPayload = null;
        requestPayload = Files.readString(file.toPath());
        return requestPayload.replace("sNumber", servicePlanNumber).replace("bId", bookingId).replace("lId", logisticId).replace("pCode",productCode).replace("pName",productName);
    }
    public Response commonGetRequest(String baseURI, String resourcePath){
        return SerenityRest
                .given()
                .baseUri(baseURI)
                .when()
                .get(resourcePath)
                .prettyPeek();
    }

    public Response commonGetRequest(String baseURI, String queryParameter, String resourcePath){
        return SerenityRest
                .given()
                .baseUri(baseURI)
                .when()
                .queryParam("processInstanceId", queryParameter)
                .get(resourcePath)
                .prettyPeek();
    }

    public String readRequestPayloadForVariables(String fileName, String ruleVariable) throws IOException {
        File file = null;
        file = ResourceUtils.getFile("src/test/resources/requestpayload/" + fileName);
        String requestPayload = null;
        requestPayload = Files.readString(file.toPath());
        return requestPayload.replace("vName", ruleVariable);
    }
}


