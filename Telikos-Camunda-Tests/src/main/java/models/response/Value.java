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
public class Value{
    public ServicePlan servicePlan;
    public String bookingId;
    public String logisticId;
    public String captureData;
    public String bookingStatus;
    public ArrayList<String> rules;
    public String validationMessage;
    public boolean isFulfilment;
}
