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

import org.wso2.am.integration.test.utils.clients.publisher.models.DocumentList;
import org.wso2.am.integration.test.utils.clients.publisher.models.Error;
import org.wso2.am.integration.test.utils.clients.publisher.models.Document;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DocumentCollectionApi {
    private ApiClient apiClient;

    public DocumentCollectionApi() {
        this(Configuration.getDefaultApiClient());
    }

    public DocumentCollectionApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /* Build call for apisApiIdDocumentsGet */
    private com.squareup.okhttp.Call apisApiIdDocumentsGetCall(String apiId, Integer limit, Integer offset, String accept, String ifNoneMatch, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;
        
        // verify the required parameter 'apiId' is set
        if (apiId == null) {
            throw new ApiException("Missing the required parameter 'apiId' when calling apisApiIdDocumentsGet(Async)");
        }
        

        // create path and map variables
        String localVarPath = "/apis/{apiId}/documents".replaceAll("\\{format\\}","json")
        .replaceAll("\\{" + "apiId" + "\\}", apiClient.escapeString(apiId.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        if (limit != null)
        localVarQueryParams.addAll(apiClient.parameterToPairs("", "limit", limit));
        if (offset != null)
        localVarQueryParams.addAll(apiClient.parameterToPairs("", "offset", offset));

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        if (accept != null)
        localVarHeaderParams.put("Accept", apiClient.parameterToString(accept));
        if (ifNoneMatch != null)
        localVarHeaderParams.put("If-None-Match", apiClient.parameterToString(ifNoneMatch));

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
     * Get a list of documents of an API
     * This operation can be used to retrive a list of documents belonging to an API by providing the id of the API. 
     * @param apiId **API ID** consisting of the **UUID** of the API. The combination of the provider of the API, name of the API and the version is also accepted as a valid API ID. Should be formatted as **provider-name-version**.  (required)
     * @param limit Maximum size of resource array to return.  (optional, default to 25)
     * @param offset Starting point within the complete list of items qualified.  (optional, default to 0)
     * @param accept Media types acceptable for the response. Default is application/json.  (optional, default to application/json)
     * @param ifNoneMatch Validator for conditional requests; based on the ETag of the formerly retrieved variant of the resourec.  (optional)
     * @return DocumentList
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public DocumentList apisApiIdDocumentsGet(String apiId, Integer limit, Integer offset, String accept, String ifNoneMatch) throws ApiException {
        ApiResponse<DocumentList> resp = apisApiIdDocumentsGetWithHttpInfo(apiId, limit, offset, accept, ifNoneMatch);
        return resp.getData();
    }

    /**
     * Get a list of documents of an API
     * This operation can be used to retrive a list of documents belonging to an API by providing the id of the API. 
     * @param apiId **API ID** consisting of the **UUID** of the API. The combination of the provider of the API, name of the API and the version is also accepted as a valid API ID. Should be formatted as **provider-name-version**.  (required)
     * @param limit Maximum size of resource array to return.  (optional, default to 25)
     * @param offset Starting point within the complete list of items qualified.  (optional, default to 0)
     * @param accept Media types acceptable for the response. Default is application/json.  (optional, default to application/json)
     * @param ifNoneMatch Validator for conditional requests; based on the ETag of the formerly retrieved variant of the resourec.  (optional)
     * @return ApiResponse&lt;DocumentList&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<DocumentList> apisApiIdDocumentsGetWithHttpInfo(String apiId, Integer limit, Integer offset, String accept, String ifNoneMatch) throws ApiException {
        com.squareup.okhttp.Call call = apisApiIdDocumentsGetCall(apiId, limit, offset, accept, ifNoneMatch, null, null);
        Type localVarReturnType = new TypeToken<DocumentList>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Get a list of documents of an API (asynchronously)
     * This operation can be used to retrive a list of documents belonging to an API by providing the id of the API. 
     * @param apiId **API ID** consisting of the **UUID** of the API. The combination of the provider of the API, name of the API and the version is also accepted as a valid API ID. Should be formatted as **provider-name-version**.  (required)
     * @param limit Maximum size of resource array to return.  (optional, default to 25)
     * @param offset Starting point within the complete list of items qualified.  (optional, default to 0)
     * @param accept Media types acceptable for the response. Default is application/json.  (optional, default to application/json)
     * @param ifNoneMatch Validator for conditional requests; based on the ETag of the formerly retrieved variant of the resourec.  (optional)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call apisApiIdDocumentsGetAsync(String apiId, Integer limit, Integer offset, String accept, String ifNoneMatch, final ApiCallback<DocumentList> callback) throws ApiException {

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

        com.squareup.okhttp.Call call = apisApiIdDocumentsGetCall(apiId, limit, offset, accept, ifNoneMatch, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<DocumentList>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
    /* Build call for apisApiIdDocumentsPost */
    private com.squareup.okhttp.Call apisApiIdDocumentsPostCall(String apiId, Document body, String contentType, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = body;
        
        // verify the required parameter 'apiId' is set
        if (apiId == null) {
            throw new ApiException("Missing the required parameter 'apiId' when calling apisApiIdDocumentsPost(Async)");
        }
        
        // verify the required parameter 'body' is set
        if (body == null) {
            throw new ApiException("Missing the required parameter 'body' when calling apisApiIdDocumentsPost(Async)");
        }
        
        // verify the required parameter 'contentType' is set
        if (contentType == null) {
            throw new ApiException("Missing the required parameter 'contentType' when calling apisApiIdDocumentsPost(Async)");
        }
        

        // create path and map variables
        String localVarPath = "/apis/{apiId}/documents".replaceAll("\\{format\\}","json")
        .replaceAll("\\{" + "apiId" + "\\}", apiClient.escapeString(apiId.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        if (contentType != null)
        localVarHeaderParams.put("Content-Type", apiClient.parameterToString(contentType));

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
        return apiClient.buildCall(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }

    /**
     * Add a new document to an API
     * This operation can be used to add a new documentation to an API. This operation only adds the metadata of a document. To add the actual content we need to use **Upload the content of an API document ** API once we obtain a document Id by this operation. 
     * @param apiId **API ID** consisting of the **UUID** of the API. The combination of the provider of the API, name of the API and the version is also accepted as a valid API ID. Should be formatted as **provider-name-version**.  (required)
     * @param body Document object that needs to be added  (required)
     * @param contentType Media type of the entity in the body. Default is application/json.  (required)
     * @return Document
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public Document apisApiIdDocumentsPost(String apiId, Document body, String contentType) throws ApiException {
        ApiResponse<Document> resp = apisApiIdDocumentsPostWithHttpInfo(apiId, body, contentType);
        return resp.getData();
    }

    /**
     * Add a new document to an API
     * This operation can be used to add a new documentation to an API. This operation only adds the metadata of a document. To add the actual content we need to use **Upload the content of an API document ** API once we obtain a document Id by this operation. 
     * @param apiId **API ID** consisting of the **UUID** of the API. The combination of the provider of the API, name of the API and the version is also accepted as a valid API ID. Should be formatted as **provider-name-version**.  (required)
     * @param body Document object that needs to be added  (required)
     * @param contentType Media type of the entity in the body. Default is application/json.  (required)
     * @return ApiResponse&lt;Document&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<Document> apisApiIdDocumentsPostWithHttpInfo(String apiId, Document body, String contentType) throws ApiException {
        com.squareup.okhttp.Call call = apisApiIdDocumentsPostCall(apiId, body, contentType, null, null);
        Type localVarReturnType = new TypeToken<Document>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Add a new document to an API (asynchronously)
     * This operation can be used to add a new documentation to an API. This operation only adds the metadata of a document. To add the actual content we need to use **Upload the content of an API document ** API once we obtain a document Id by this operation. 
     * @param apiId **API ID** consisting of the **UUID** of the API. The combination of the provider of the API, name of the API and the version is also accepted as a valid API ID. Should be formatted as **provider-name-version**.  (required)
     * @param body Document object that needs to be added  (required)
     * @param contentType Media type of the entity in the body. Default is application/json.  (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call apisApiIdDocumentsPostAsync(String apiId, Document body, String contentType, final ApiCallback<Document> callback) throws ApiException {

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

        com.squareup.okhttp.Call call = apisApiIdDocumentsPostCall(apiId, body, contentType, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<Document>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
}
