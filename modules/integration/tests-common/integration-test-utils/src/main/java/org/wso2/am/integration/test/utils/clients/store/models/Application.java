/**
 * WSO2 API Manager - Store
 * This specifies a **RESTful API** for WSO2 **API Manager** - Store.  Please see [full swagger definition](https://raw.githubusercontent.com/wso2/carbon-apimgt/v6.0.4/components/apimgt/org.wso2.carbon.apimgt.rest.api.store/src/main/resources/store-api.yaml) of the API which is written using [swagger 2.0](http://swagger.io/) specification. 
 *
 * OpenAPI spec version: 0.10.0
 * Contact: architecture@wso2.com
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package org.wso2.am.integration.test.utils.clients.store.models;

import java.util.Objects;
import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.wso2.am.integration.test.utils.clients.store.models.ApplicationKey;


/**
 * Application
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2016-10-07T15:17:10.700+05:30")
public class Application   {
  @SerializedName("applicationId")
  private String applicationId = null;

  @SerializedName("name")
  private String name = null;

  @SerializedName("subscriber")
  private String subscriber = null;

  @SerializedName("throttlingTier")
  private String throttlingTier = null;

  @SerializedName("callbackUrl")
  private String callbackUrl = null;

  @SerializedName("description")
  private String description = null;

  @SerializedName("status")
  private String status = "";

  @SerializedName("groupId")
  private String groupId = null;

  @SerializedName("keys")
  private List<ApplicationKey> keys = new ArrayList<ApplicationKey>();

  public Application applicationId(String applicationId) {
    this.applicationId = applicationId;
    return this;
  }

   /**
   * Get applicationId
   * @return applicationId
  **/
  @ApiModelProperty(example = "01234567-0123-0123-0123-012345678901", value = "")
  public String getApplicationId() {
    return applicationId;
  }

  public void setApplicationId(String applicationId) {
    this.applicationId = applicationId;
  }

  public Application name(String name) {
    this.name = name;
    return this;
  }

   /**
   * Get name
   * @return name
  **/
  @ApiModelProperty(example = "CalculatorApp", required = true, value = "")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Application subscriber(String subscriber) {
    this.subscriber = subscriber;
    return this;
  }

   /**
   * If subscriber is not given user invoking the API will be taken as the subscriber. 
   * @return subscriber
  **/
  @ApiModelProperty(example = "admin", value = "If subscriber is not given user invoking the API will be taken as the subscriber. ")
  public String getSubscriber() {
    return subscriber;
  }

  public void setSubscriber(String subscriber) {
    this.subscriber = subscriber;
  }

  public Application throttlingTier(String throttlingTier) {
    this.throttlingTier = throttlingTier;
    return this;
  }

   /**
   * Get throttlingTier
   * @return throttlingTier
  **/
  @ApiModelProperty(example = "Unlimited", required = true, value = "")
  public String getThrottlingTier() {
    return throttlingTier;
  }

  public void setThrottlingTier(String throttlingTier) {
    this.throttlingTier = throttlingTier;
  }

  public Application callbackUrl(String callbackUrl) {
    this.callbackUrl = callbackUrl;
    return this;
  }

   /**
   * Get callbackUrl
   * @return callbackUrl
  **/
  @ApiModelProperty(example = "", value = "")
  public String getCallbackUrl() {
    return callbackUrl;
  }

  public void setCallbackUrl(String callbackUrl) {
    this.callbackUrl = callbackUrl;
  }

  public Application description(String description) {
    this.description = description;
    return this;
  }

   /**
   * Get description
   * @return description
  **/
  @ApiModelProperty(example = "Sample calculator application", value = "")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Application status(String status) {
    this.status = status;
    return this;
  }

   /**
   * Get status
   * @return status
  **/
  @ApiModelProperty(example = "APPROVED", value = "")
  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Application groupId(String groupId) {
    this.groupId = groupId;
    return this;
  }

   /**
   * Get groupId
   * @return groupId
  **/
  @ApiModelProperty(example = "", value = "")
  public String getGroupId() {
    return groupId;
  }

  public void setGroupId(String groupId) {
    this.groupId = groupId;
  }

  public Application keys(List<ApplicationKey> keys) {
    this.keys = keys;
    return this;
  }

  public Application addKeysItem(ApplicationKey keysItem) {
    this.keys.add(keysItem);
    return this;
  }

   /**
   * Get keys
   * @return keys
  **/
  @ApiModelProperty(example = "null", value = "")
  public List<ApplicationKey> getKeys() {
    return keys;
  }

  public void setKeys(List<ApplicationKey> keys) {
    this.keys = keys;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Application application = (Application) o;
    return Objects.equals(this.applicationId, application.applicationId) &&
        Objects.equals(this.name, application.name) &&
        Objects.equals(this.subscriber, application.subscriber) &&
        Objects.equals(this.throttlingTier, application.throttlingTier) &&
        Objects.equals(this.callbackUrl, application.callbackUrl) &&
        Objects.equals(this.description, application.description) &&
        Objects.equals(this.status, application.status) &&
        Objects.equals(this.groupId, application.groupId) &&
        Objects.equals(this.keys, application.keys);
  }

  @Override
  public int hashCode() {
    return Objects.hash(applicationId, name, subscriber, throttlingTier, callbackUrl, description, status, groupId, keys);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Application {\n");
    
    sb.append("    applicationId: ").append(toIndentedString(applicationId)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    subscriber: ").append(toIndentedString(subscriber)).append("\n");
    sb.append("    throttlingTier: ").append(toIndentedString(throttlingTier)).append("\n");
    sb.append("    callbackUrl: ").append(toIndentedString(callbackUrl)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    groupId: ").append(toIndentedString(groupId)).append("\n");
    sb.append("    keys: ").append(toIndentedString(keys)).append("\n");
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

