Feature: To test the camunda workflow e2e

  @test1
  Scenario Outline: To test the camunda happy flow for Intermodal
    Given a user start the workflow with "<servicePlanNumber>","<bookingId>","<logisticId>","<productCode>","<productName>"
    Then the status code should be "<statusCode>"
    When user hit the VerifyCurrentRunningStatus api to fetch the process instance id with "<businessKey>"
    And verify the booking variable details for "<bookingVariable_Name>" "<captureDataStatus>" "<validationMessage>" "<servicePlanNumber>" "<bookingId>" "<logisticId>" "<variableValue>"
    And verify the domainTaskRules variable details "<domainTaskRulesVariable_Name>" "<variableValue>"
    And verify the capture variable details "<captureVariable_Name>" "<captureFlag>"
    And verify the preCapture variable details "<preCaptureVariable_Name>" "<preCaptureFlag>"
    When user hit the validation api to resume the workflow with "<servicePlanNumber>","<bookingId>","<logisticId>","<productCode>","<productName>"
    Then verify the current task running status is "<firstManualIntervention_TaskName>"
    When user hit the fulfilment api to resume the workflow with "<servicePlanNumber>","<bookingId>","<logisticId>","<productCode>","<productName>"
    And user verify all the completed task
      | key          | value                                                   |
      | activityName | Booking Process Started on listening to Booking topic   |
      | activityName | Read And Process Domain Task /Business Rule             |
      | activityName | Resume\n Capture\nAutomated\nTask                       |
      | activityName | Record Booking                                          |
      | activityName | Resume Fulfilment\nUser Task                            |
      | activityName | validate                                                |
      | activityName | capture                                                 |
      | activityName | Booking\n(Confirm/Reject) Response                      |
      | activityName | Check With Fullfillment                                 |
      | activityName | Send Notification                                       |
      | activityName | Booking Domain Workflow Transaction Boundary Subprocess |
      | activityName | Resume\nValidate\nUserTask                              |
      | activityName | End Transaction Boundary                                |
      | activityName | Start Transaction Boundary                              |
      | activityName | Booking Processed                                       |
      | activityName | Booking Processed                                       |
      | activityName | Based On the Domain Task Rule                           |
      | activityName | Parallel gateway                                        |
      | activityName | null                                                    |
      | activityName | Based On the Domain Task Rule                           |
      | activityName | Parallel Merge gateway                                  |
      | activityName | Parallel Merge gateway                                  |
    Examples:
      | servicePlanNumber | bookingId | logisticId | productCode | productName | statusCode | businessKey | captureDataStatus | validationMessage | captureFlag | preCaptureFlag | bookingVariable_Name | domainTaskRulesVariable_Name | captureVariable_Name | preCaptureVariable_Name | firstManualIntervention_TaskName | variableValue |
      | SP20              | B20       | L20        | IM          | InterModal  | 200        | B20         | initiated         | Unknown           | true        | false          | booking              | domainTaskRules              | capture              | preCapture              | Resume Fulfilment\nUser Task     | InterModal    |

  @test2
  Scenario Outline: To test the camunda happy flow for CHB
    Given a user start the workflow with "<servicePlanNumber>","<bookingId>","<logisticId>","<productCode>","<productName>"
    Then the status code should be "<statusCode>"
    When user hit the VerifyCurrentRunningStatus api to fetch the process instance id with "<businessKey>"
    And verify the booking variable details for "<bookingVariable_Name>" "<captureDataStatus>" "<validationMessage>" "<servicePlanNumber>" "<bookingId>" "<logisticId>" "<variableValue>"
    And verify the domainTaskRules variable details "<domainTaskRulesVariable_Name>" "<variableValue>"
    And verify the capture variable details "<captureVariable_Name>" "<captureFlag>"
    And verify the preCapture variable details "<preCaptureVariable_Name>" "<preCaptureFlag>"
    When user hit the validation api to resume the workflow with "<servicePlanNumber>","<bookingId>","<logisticId>","<productCode>","<productName>"
    Then verify the current task running status is "<firstManualIntervention_TaskName>"
    When user hit the fulfilment api to resume the workflow with "<servicePlanNumber>","<bookingId>","<logisticId>","<productCode>","<productName>"
    And user verify all the completed task
      | key          | value                                                   |
      | activityName | Booking Process Started on listening to Booking topic   |
      | activityName | Read And Process Domain Task /Business Rule             |
      | activityName | Record Booking                                          |
      | activityName | Resume Fulfilment\nUser Task                            |
      | activityName | validate                                                |
      | activityName | Pre Capture                                             |
      | activityName | Booking\n(Confirm/Reject) Response                      |
      | activityName | Check With Fullfillment                                 |
      | activityName | Send Notification                                       |
      | activityName | Booking Domain Workflow Transaction Boundary Subprocess |
      | activityName | Resume\nValidate\nUserTask                              |
      | activityName | End Transaction Boundary                                |
      | activityName | Start Transaction Boundary                              |
      | activityName | Booking Processed                                       |
      | activityName | Booking Processed                                       |
      | activityName | Based On the Domain Task Rule                           |
      | activityName | Parallel gateway                                        |
      | activityName | null                                                    |
      | activityName | Based On the Domain Task Rule                           |
      | activityName | Parallel Merge gateway                                  |
      | activityName | Parallel Merge gateway                                  |
    Examples:
      | servicePlanNumber | bookingId | logisticId | productCode | productName | statusCode | businessKey | captureDataStatus |validationMessage| captureFlag | preCaptureFlag | bookingVariable_Name | domainTaskRulesVariable_Name | captureVariable_Name | preCaptureVariable_Name | firstManualIntervention_TaskName | variableValue |
      | SP84              | B84       | L84        | CHB         | CHB         | 200        | B84         | Unknown           |Unknown          | false       | true           | booking              | domainTaskRules              | capture              | preCapture              | Resume Fulfilment\nUser Task     | CHB           |







