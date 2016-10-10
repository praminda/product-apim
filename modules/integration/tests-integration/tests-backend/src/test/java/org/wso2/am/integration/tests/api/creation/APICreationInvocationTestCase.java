/*
*  Copyright (c) 2015, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
*
*  WSO2 Inc. licenses this file to you under the Apache License,
*  Version 2.0 (the "License"); you may not use this file except
*  in compliance with the License.
*  You may obtain a copy of the License at
*
*  http://www.apache.org/licenses/LICENSE-2.0
*
*  Unless required by applicable law or agreed to in writing,
*  software distributed under the License is distributed on an
*  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
*  KIND, either express or implied.  See the License for the
*  specific language governing permissions and limitations
*  under the License.
*/
package org.wso2.am.integration.tests.api.creation;

import org.wso2.am.integration.test.utils.clients.publisher.api.APICollectionApi;
import org.wso2.am.integration.test.utils.clients.publisher.api.APIIndividualApi;
import org.wso2.am.integration.test.utils.clients.publisher.models.API;
import org.wso2.am.integration.test.utils.clients.store.api.ApplicationIndividualApi;
import org.wso2.am.integration.test.utils.clients.store.api.SubscriptionIndividualApi;
import org.wso2.am.integration.test.utils.clients.store.models.Application;
import org.wso2.am.integration.test.utils.clients.store.models.ApplicationKey;
import org.wso2.am.integration.test.utils.clients.store.models.ApplicationKeyGenerateRequest;
import org.wso2.am.integration.test.utils.clients.store.models.Subscription;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.Assert;
import org.testng.annotations.*;
import org.wso2.am.integration.test.utils.base.APIMIntegrationBaseTest;
import org.wso2.am.integration.test.utils.bean.*;
import org.wso2.am.integration.test.utils.clients.RestAPIAuthClient;
import org.wso2.carbon.automation.engine.context.TestUserMode;
import org.wso2.carbon.automation.test.utils.http.client.HttpRequestUtil;
import org.wso2.carbon.automation.test.utils.http.client.HttpResponse;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class APICreationInvocationTestCase extends APIMIntegrationBaseTest {
    private final Log log = LogFactory.getLog(APICreationInvocationTestCase.class);
    private org.wso2.am.integration.test.utils.clients.store.ApiClient apiStore;
    private org.wso2.am.integration.test.utils.clients.publisher.ApiClient apiPublisher;
    private String apiName = "TestSampleApi1";
    private String apiContext = "testSampleApi1";
    private String appName = "sample-application1";
    private String contentTypeJson = "application/json";
    private String applicationId;
    private String apiId;
    private Map<String, String> requestHeaders = new HashMap<String, String>();

    @Factory(dataProvider = "userModeDataProvider")
    public APICreationInvocationTestCase(TestUserMode userMode) {
        this.userMode = userMode;
    }

    @BeforeClass(alwaysRun = true)
    public void setEnvironment() throws Exception {
        super.init(userMode);

        RestAPIClientBean bean = new RestAPIClientBean();
        bean.setUsername(user.getUserName());
        bean.setPassword(user.getPassword());
        bean.setScopes(new String[] { "apim:subscribe" });

        apiStore = new RestAPIAuthClient(userMode).getStoreApiClient(bean);
        bean.setScopes(new String[] { "apim:api_publish", "apim:api_create" });
        apiPublisher = new RestAPIAuthClient(userMode).getPublisherApiClient(bean);
    }

    @Test(groups = { "wso2.am" }, description = "Sample API creation")
    public void testAPICreation() throws Exception {
        APICollectionApi apiCollectionApi = new APICollectionApi(apiPublisher);
        String endpoint = gatewayUrlsMgt.getWebAppURLHttp() + "/am/sample/pizzashack/v1/api/";
        List<String> tiers = new ArrayList<String>();
        tiers.add("Unlimited");
        List<String> transport = new ArrayList<String>();
        transport.add("http");
        transport.add("https");

        API api = new API();
        api.setName(apiName);
        api.setContext(apiContext);
        api.setVersion("1.0.0");
        api.setDescription("APICreationInvocationTestCase api");
        api.setIsDefaultVersion(false);
        api.setVisibility(API.VisibilityEnum.PUBLIC);
        api.setTiers(tiers);
        api.setTransport(transport);
        api.setApiDefinition(
                "{\"paths\":{\"/*\":{\"get\":{\"responses\":{\"200\":{\"description\":\"\"}}}}},\"schemes\":[\"https\"],\"produces\":[\"application/json\"],\"swagger\":\"2.0\",\"definitions\":{},\"consumes\":[\"application/json\"],\"info\":{\"title\":\"PizzaShackAPI\",\"description\":\"This document describe a RESTFul API for Pizza Shack online pizza delivery store.\\n\",\"license\":{\"name\":\"Apache 2.0\",\"url\":\"http://www.apache.org/licenses/LICENSE-2.0.html\"},\"contact\":{\"email\":\"architecture@pizzashack.com\",\"name\":\"John Doe\",\"url\":\"http://www.pizzashack.com\"},\"version\":\"1.0.0\"}}");
        api.setEndpointConfig("{\"production_endpoints\":{\"url\":\"" + endpoint
                + "\",\"config\":null},\"sandbox_endpoints\":{\"url\":\"" + endpoint
                + "\",\"config\":null},\"endpoint_type\":\"http\"}");

        API newApi = apiCollectionApi.apisPost(api, contentTypeJson);
        apiId = newApi.getId();
    }

    @Test(groups = { "wso2.am" }, description = "Sample API Publishing", dependsOnMethods = "testAPICreation")
    public void testAPIPublishing() throws Exception {
        APIIndividualApi apiIndividualApi = new APIIndividualApi(apiPublisher);
        apiIndividualApi.apisChangeLifecyclePost("Publish", apiId, "", "", "");
    }

    @Test(groups = { "wso2.am" }, description = "Sample Application Creation", dependsOnMethods = "testAPIPublishing")
    public void testApplicationCreation() throws Exception {
        ApplicationIndividualApi appApi = new ApplicationIndividualApi(apiStore);
        Application app = new Application();
        app.setName(appName);
        app.setThrottlingTier(APIThrottlingTier.UNLIMITED.getState());
        app.setCallbackUrl("");
        app.setDescription("sample app description created by client");

        Application newApp = appApi.applicationsPost(app, contentTypeJson);
        applicationId = newApp.getApplicationId();
    }

    @Test(groups = { "wso2.am" }, description = "API Subscription", dependsOnMethods = "testApplicationCreation")
    public void testAPISubscription() throws Exception {
        SubscriptionIndividualApi subscriptionApi = new SubscriptionIndividualApi(apiStore);
        Subscription subscription = new Subscription();
        subscription.setApplicationId(applicationId);
        subscription.setApiIdentifier(apiId);
        subscription.setTier("Unlimited");

        subscriptionApi.subscriptionsPost(subscription, contentTypeJson);
    }

    @Test(groups = { "wso2.am" }, description = "Application Key Generation", dependsOnMethods = "testAPISubscription")
    public void testApplicationKeyGeneration() throws Exception {
        ApplicationIndividualApi appApi = new ApplicationIndividualApi(apiStore);
        ApplicationKeyGenerateRequest keyGenerateRequest = new ApplicationKeyGenerateRequest();
        List<String> domains = new ArrayList();
        domains.add("ALL");
        keyGenerateRequest.setAccessAllowDomains(domains);
        keyGenerateRequest.setValidityTime("3600");
        keyGenerateRequest.setKeyType(ApplicationKeyGenerateRequest.KeyTypeEnum.PRODUCTION);

        ApplicationKey appKey = appApi
                .applicationsGenerateKeysPost(applicationId, keyGenerateRequest, contentTypeJson, "", "");
        Assert.assertNotNull("Access Token not found " + appKey, appKey.getToken().getAccessToken());
        requestHeaders.put("Authorization", "Bearer " + appKey.getToken().getAccessToken());
    }

    @Test(groups = { "wso2.am" }, description = "Sample API creation",
          dependsOnMethods = "testApplicationKeyGeneration")
    public void testAPIInvocation() throws Exception {
        requestHeaders.put("Accept", "application/json");

        HttpResponse serviceResponse = HttpRequestUtil
                .doGet(getAPIInvocationURLHttp(apiContext + "/1.0.0/menu"), requestHeaders);
        log.info(serviceResponse.getData());
        assertEquals(serviceResponse.getResponseCode(), Response.Status.OK.getStatusCode(),
                "Response code mismatched when api invocation");
        assertTrue(serviceResponse.getData().contains("price"), "Response data mismatched when api invocation");

    }

    @AfterClass(alwaysRun = true)
    public void destroy() throws Exception {
        ApplicationIndividualApi appApi = new ApplicationIndividualApi(apiStore);
        appApi.applicationsApplicationIdDelete(applicationId, "", "");
        super.cleanUp();
    }

    @DataProvider
    public static Object[][] userModeDataProvider() {
        return new Object[][] { new Object[] { TestUserMode.SUPER_TENANT_ADMIN },
                new Object[] { TestUserMode.TENANT_ADMIN }, };
    }
}
