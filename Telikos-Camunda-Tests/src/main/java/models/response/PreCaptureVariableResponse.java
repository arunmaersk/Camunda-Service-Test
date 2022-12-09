package models.response;

import io.cucumber.core.internal.com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PreCaptureVariableResponse {
    public String type;
    public boolean value;
    public ValueInfo valueInfo;
    public String id;
    public String name;
    public String processDefinitionId;
    public String processInstanceId;
    public String executionId;
    public Object caseInstanceId;
    public Object caseExecutionId;
    public Object taskId;
    public Object batchId;
    public String activityInstanceId;
    public Object errorMessage;
    public Object tenantId;
}
