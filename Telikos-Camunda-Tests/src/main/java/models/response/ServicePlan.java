package models.response;

import io.cucumber.core.internal.com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ServicePlan{
    public Product product;
    public String servicePlanNumber;
    public boolean isMultiCountryConsolidation;
    public boolean hasDistributionCentre;
    public boolean hasOriginMilkRun;
    public String cargoServiceType;
}
