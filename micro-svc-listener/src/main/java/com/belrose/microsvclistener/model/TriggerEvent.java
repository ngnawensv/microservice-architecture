package com.belrose.microsvclistener.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TriggerEvent  implements Serializable {
  private String eventName;
  private String eventState;
  private String agreTransaction;
  @JsonProperty("timestamp")
  private Long timestamp;
}
