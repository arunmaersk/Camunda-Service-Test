package com.example.anchorflow.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FailureEvent {
  private String bookingId;
  private String logisticId;
  private String bookingStatus;//cancel
  private String reason;//technical failure
}
