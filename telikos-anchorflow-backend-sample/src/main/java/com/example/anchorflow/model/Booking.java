package com.example.anchorflow.model;

import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Booking  implements Serializable {
 private  ServicePlan servicePlan;
 private String bookingId;
 private String logisticId;
 private String captureData;//unKnown/initiated//updated
 private String bookingStatus;//submitted/confirm/reject/cancel
 private List<String> rules;///empty//businessRuleList
 private String validationMessage;//unknown//validation success/ validation failure
 private Boolean isFulfilment;//false//true//false


}


