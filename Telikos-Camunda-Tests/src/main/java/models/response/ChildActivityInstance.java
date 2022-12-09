package models.response;

import io.cucumber.core.internal.com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChildActivityInstance {
    public String id;
    public String parentActivityInstanceId;
    public String activityId;
    public String activityType;
    public String processInstanceId;
    public String processDefinitionId;
    public ArrayList<ChildActivityInstance> childActivityInstances;
    public ArrayList<Object> childTransitionInstances;
    public ArrayList<String> executionIds;
    public String activityName;
    public ArrayList<Object> incidentIds;
    public ArrayList<Object> incidents;
    public String name;
}
