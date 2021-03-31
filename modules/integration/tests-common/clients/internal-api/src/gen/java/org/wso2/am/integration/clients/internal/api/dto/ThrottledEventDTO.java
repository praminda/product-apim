/*
 * Internal Utility API
 * This API allows you to access internal data.
 *
 * OpenAPI spec version: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package org.wso2.am.integration.clients.internal.api.dto;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;

/**
 * ThrottledEventDTO
 */

public class ThrottledEventDTO {
  @SerializedName("throttle_key")
  private String throttleKey = null;

  @SerializedName("last_updated_time")
  private String lastUpdatedTime = null;

  @SerializedName("throttle_state")
  private String throttleState = null;

  public ThrottledEventDTO throttleKey(String throttleKey) {
    this.throttleKey = throttleKey;
    return this;
  }

   /**
   * throttle key.
   * @return throttleKey
  **/
  @ApiModelProperty(value = "throttle key.")
  public String getThrottleKey() {
    return throttleKey;
  }

  public void setThrottleKey(String throttleKey) {
    this.throttleKey = throttleKey;
  }

  public ThrottledEventDTO lastUpdatedTime(String lastUpdatedTime) {
    this.lastUpdatedTime = lastUpdatedTime;
    return this;
  }

   /**
   * Last time decision updated.
   * @return lastUpdatedTime
  **/
  @ApiModelProperty(value = "Last time decision updated.")
  public String getLastUpdatedTime() {
    return lastUpdatedTime;
  }

  public void setLastUpdatedTime(String lastUpdatedTime) {
    this.lastUpdatedTime = lastUpdatedTime;
  }

  public ThrottledEventDTO throttleState(String throttleState) {
    this.throttleState = throttleState;
    return this;
  }

   /**
   * throttle state.
   * @return throttleState
  **/
  @ApiModelProperty(value = "throttle state.")
  public String getThrottleState() {
    return throttleState;
  }

  public void setThrottleState(String throttleState) {
    this.throttleState = throttleState;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ThrottledEventDTO throttledEvent = (ThrottledEventDTO) o;
    return Objects.equals(this.throttleKey, throttledEvent.throttleKey) &&
        Objects.equals(this.lastUpdatedTime, throttledEvent.lastUpdatedTime) &&
        Objects.equals(this.throttleState, throttledEvent.throttleState);
  }

  @Override
  public int hashCode() {
    return Objects.hash(throttleKey, lastUpdatedTime, throttleState);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ThrottledEventDTO {\n");
    
    sb.append("    throttleKey: ").append(toIndentedString(throttleKey)).append("\n");
    sb.append("    lastUpdatedTime: ").append(toIndentedString(lastUpdatedTime)).append("\n");
    sb.append("    throttleState: ").append(toIndentedString(throttleState)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}

