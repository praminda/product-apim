/**
 * WSO2 API Manager - Publisher API
 * This specifies a **RESTful API** for WSO2 **API Manager** - Publisher.  Please see [full swagger definition](https://raw.githubusercontent.com/wso2/carbon-apimgt/v6.0.4/components/apimgt/org.wso2.carbon.apimgt.rest.api.publisher/src/main/resources/publisher-api.yaml) of the API which is written using [swagger 2.0](http://swagger.io/) specification. 
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


package org.wso2.am.integration.test.utils.clients.publisher.api;

import org.wso2.am.integration.test.utils.clients.publisher.ApiCallback;
import org.wso2.am.integration.test.utils.clients.publisher.ApiClient;
import org.wso2.am.integration.test.utils.clients.publisher.ApiException;
import org.wso2.am.integration.test.utils.clients.publisher.ApiResponse;
import org.wso2.am.integration.test.utils.clients.publisher.Configuration;
import org.wso2.am.integration.test.utils.clients.publisher.Pair;
import org.wso2.am.integration.test.utils.clients.publisher.ProgressRequestBody;
import org.wso2.am.integration.test.utils.clients.publisher.ProgressResponseBody;

import com.google.gson.reflect.TypeToken;

import java.io.IOException;

import org.wso2.am.integration.test.utils.clients.publisher.models.Application;
import org.wso2.am.integration.test.utils.clients.publisher.models.Error;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApplicationIndividualApi {
    private ApiClient apiClient;

    public ApplicationIndividualApi() {
        this(Configuration.getDefaultApiClient());
    }

    public ApplicationIndividualApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /* Build call for applicationsApplicationIdGet */
    private com.squareup.okhttp.Call applicationsApplicationIdGetCall(String applicationId, String accept, String ifNoneMatch, String ifModifiedSince, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'applicationId' is set
        if (applicationId == null) {
            throw new ApiException("Missing the required parameter 'applicationId' when calling applicationsApplicationIdGet(Async)");
        }
        

        // create path and map variables
        String localVarPath = "/applications/{applicationId}".replaceAll("\\{format\\}","json")
        .replaceAll("\\{" + "applicationId" + "\\}", apiClient.escapeString(applicationId.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        if (accept != null)
        localVarHeaderParams.put("Accept", apiClient.parameterToString(accept));
        if (ifNoneMatch != null)
        localVarHeaderParams.put("If-None-Match", apiClient.parameterToString(ifNoneMatch));
        if (ifModifiedSince != null)
        localVarHeaderParams.put("If-Modified-Since", apiClient.parameterToString(ifModifiedSince));

        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) localVarHeaderParams.put("Accept", localVarAccept);

        final String[] localVarContentTypes = {
            "application/json"
        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        if(progressListener != null) {
            apiClient.getHttpClient().networkInterceptors().add(new com.squareup.okhttp.Interceptor() {
                @Override
                public com.squareup.okhttp.Response intercept(com.squareup.okhttp.Interceptor.Chain chain) throws IOException {
                    com.squareup.okhttp.Response originalResponse = chain.proceed(chain.request());
                    return originalResponse.newBuilder()
                    .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                    .build();
                }
            });
        }

        String[] localVarAuthNames = new String[] { "publisher_auth" };
        return apiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }

    /**
     * Get details of an application
     * This operation can be used to retrieve details of an individual application specifying the application id in the URI. 
     * @param applicationId **Application Identifier** consisting of the UUID of the Application.  (required)
     * @param accept Media types acceptable for the response. Default is application/json.  (optional, default to application/json)
     * @param ifNoneMatch Validator for conditional requests; based on the ETag of the formerly retrieved variant of the resourec.  (optional)
     * @param ifModifiedSince Validator for conditional requests; based on Last Modified header of the formerly retrieved variant of the resource.  (optional)
     * @return Application
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public Application applicationsApplicationIdGet(String applicationId, String accept, String ifNoneMatch, String ifModifiedSince) throws ApiException {
        ApiResponse<Application> resp = applicationsApplicationIdGetWithHttpInfo(applicationId, accept, ifNoneMatch, ifModifiedSince);
        return resp.getData();
    }

    /**
     * Get details of an application
     * This operation can be used to retrieve details of an individual application specifying the application id in the URI. 
     * @param applicationId **Application Identifier** consisting of the UUID of the Application.  (required)
     * @param accept Media types acceptable for the response. Default is application/json.  (optional, default to application/json)
     * @param ifNoneMatch Validator for conditional requests; based on the ETag of the formerly retrieved variant of the resourec.  (optional)
     * @param ifModifiedSince Validator for conditional requests; based on Last Modified header of the formerly retrieved variant of the resource.  (optional)
     * @return ApiResponse&lt;Application&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<Application> applicationsApplicationIdGetWithHttpInfo(String applicationId, String accept, String ifNoneMatch, String ifModifiedSince) throws ApiException {
        com.squareup.okhttp.Call call = applicationsApplicationIdGetCall(applicationId, accept, ifNoneMatch, ifModifiedSince, null, null);
        Type localVarReturnType = new TypeToken<Application>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Get details of an application (asynchronously)
     * This operation can be used to retrieve details of an individual application specifying the application id in the URI. 
     * @param applicationId **Application Identifier** consisting of the UUID of the Application.  (required)
     * @param accept Media types acceptable for the response. Default is application/json.  (optional, default to application/json)
     * @param ifNoneMatch Validator for conditional requests; based on the ETag of the formerly retrieved variant of the resourec.  (optional)
     * @param ifModifiedSince Validator for conditional requests; based on Last Modified header of the formerly retrieved variant of the resource.  (optional)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call applicationsApplicationIdGetAsync(String applicationId, String accept, String ifNoneMatch, String ifModifiedSince, final ApiCallback<Application> callback) throws ApiException {

        ProgressResponseBody.ProgressListener progressListener = null;
        ProgressRequestBody.ProgressRequestListener progressRequestListener = null;

        if (callback != null) {
            progressListener = new ProgressResponseBody.ProgressListener() {
                @Override
                public void update(long bytesRead, long contentLength, boolean done) {
                    callback.onDownloadProgress(bytesRead, contentLength, done);
                }
            };

            progressRequestListener = new ProgressRequestBody.ProgressRequestListener() {
                @Override
                public void onRequestProgress(long bytesWritten, long contentLength, boolean done) {
                    callback.onUploadProgress(bytesWritten, contentLength, done);
                }
            };
        }

        com.squareup.okhttp.Call call = applicationsApplicationIdGetCall(applicationId, accept, ifNoneMatch, ifModifiedSince, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<Application>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
}
