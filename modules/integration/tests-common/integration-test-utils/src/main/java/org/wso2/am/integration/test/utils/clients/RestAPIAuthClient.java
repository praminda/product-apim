/*
 *
 * Copyright (c) 2016, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
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

package org.wso2.am.integration.test.utils.clients;

import io.swagger.client.ApiClient;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.RandomStringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.wso2.am.integration.test.utils.APIManagerIntegrationTestException;
import org.wso2.am.integration.test.utils.base.APIMIntegrationConstants;
import org.wso2.am.integration.test.utils.bean.*;
import org.wso2.carbon.automation.engine.context.AutomationContext;
import org.wso2.carbon.automation.engine.context.TestUserMode;
import org.wso2.carbon.automation.engine.exceptions.AutomationFrameworkException;
import org.wso2.carbon.automation.test.utils.http.client.HttpRequestUtil;

import javax.xml.xpath.XPathExpressionException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Util client to provide an interface to authenticate and manage
 * access tokens for API Manager REST API
 */
public class RestAPIAuthClient {
    private String gatewayNURL;
    private String gatewayURL;

    //constants
    private static final String DCR_PATH = "client-registration/v0.10/register";
    private static final String AUTHORIZATION_KEY = "Authorization";
    private static final String CONTENT_TYPE_KEY = "Content-Type";
    private static final String CONTENT_TYPE_JSON = "application/json";
    private static final String ADMIN_CREDENTIALS_VALUE = "admin:admin";
    private static final String TOKEN_ENDPOINT_SUFFIX = "token";
    private static final String CHARSET = "UTF-8";
    private static final String GRANT_TYPE_PASSWORD = "password";
    private static final long TOKEN_EXPIRY_MIN_LIMIT = 600;

    private static class DCRParams {
        private static final String CALLBACK_URL = "callbackUrl";
        private static final String CLIENT_NAME = "clientName";
        private static final String OWNER = "owner";
        private static final String GRANT_TYPE = "grantType";
        private static final String SAAS_APP = "saasApp";
    }

    private static class TokenAPIParams {
        private static final String GRANT_TYPE = "grant_type";
        private static final String USER_NAME = "username";
        private static final String PASSWORD = "password";
        private static final String SCOPE = "scope";
        public static final String REFRESH_TOKEN = "refresh_token";
    }

    public RestAPIAuthClient(TestUserMode userMode) throws XPathExpressionException {
        APIMURLBean gatewayUrlsMgt, gatewayUrlsWrk;
        AutomationContext gatewayContextMgt, gatewayContextWrk;

        gatewayContextMgt = new AutomationContext(APIMIntegrationConstants.AM_PRODUCT_GROUP_NAME,
                APIMIntegrationConstants.AM_GATEWAY_MGT_INSTANCE, userMode);
        gatewayUrlsMgt = new APIMURLBean(gatewayContextMgt.getContextUrls());

        gatewayContextWrk = new AutomationContext(APIMIntegrationConstants.AM_PRODUCT_GROUP_NAME,
                APIMIntegrationConstants.AM_GATEWAY_WRK_INSTANCE, userMode);
        gatewayUrlsWrk = new APIMURLBean(gatewayContextWrk.getContextUrls());

        this.gatewayNURL = gatewayUrlsWrk.getWebAppURLNhttp();
        this.gatewayURL = gatewayUrlsMgt.getWebAppURLHttp();
    }

    /**
     * Retrieves swagger {@link ApiClient} for store api initialized with an access token
     *
     * @param configBean client configuration details
     * @return initialized Api Client
     * @throws APIManagerIntegrationTestException
     */
    public ApiClient getStoreApiClient(RestAPIClientBean configBean)
            throws APIManagerIntegrationTestException {
        String basePath = this.gatewayURL + "/api/am/store/v0.10";
        ApiClient storeClient = getApiClient(basePath, configBean);

        return storeClient;
    }

    /**
     * Retrieves swagger {@link ApiClient} for publisher api initialized with an access token
     *
     * @param configBean client configuration details
     * @return initialized Api Client
     * @throws APIManagerIntegrationTestException
     */
    public ApiClient getPublisherApiClient(RestAPIClientBean configBean)
            throws APIManagerIntegrationTestException {
        String basePath = this.gatewayURL + "/api/am/publisher/v0.10";
        ApiClient publisherClient = getApiClient(basePath, configBean);

        return publisherClient;
    }

    /**
     * Create OAuth application and retrieve <code>client id</code>
     * and <code>client secret</code>
     * Created application will have a randomly generated name to avoid
     * confusion with other applications created.
     * <p>Users of this client is required to create OAuth application
     * by calling this method before using the client</p>
     *
     * @return {@link RestAPIRegistrationResponse} with OAuth application information
     * @throws APIManagerIntegrationTestException
     */
    public RestAPIRegistrationResponse createOAuthApplication(String clientName) throws APIManagerIntegrationTestException {

        if (clientName == null) {

            //use a random name for client to avoid conflicts in application(s)
            clientName = RandomStringUtils.randomAlphabetic(5);
        }

        Map<String, String> headers = new HashMap<String, String>();
        RestAPIRegistrationRequest registrationRequest = new RestAPIRegistrationRequest();
        registrationRequest.setCallbackURL("www.wso2.com");
        registrationRequest.setClientName(clientName);
        registrationRequest.setTokenScope("Production");
        registrationRequest.setOwner("admin");
        registrationRequest.setSupportedGrantTypes("password refresh_token");
        registrationRequest.setSaaSApp(true);

        try {
            byte[] encodedBytes = Base64.encodeBase64(ADMIN_CREDENTIALS_VALUE.getBytes(CHARSET));
            headers.put(AUTHORIZATION_KEY, "Basic " + new String(encodedBytes, CHARSET));
            headers.put(CONTENT_TYPE_KEY, CONTENT_TYPE_JSON);
            String requestBody = buildAppRegistrationRequest(registrationRequest).toString();

            JSONObject jsonResponse = new JSONObject(
                    HttpRequestUtil.doPost(new URL(gatewayURL + DCR_PATH), requestBody, headers).getData());
            RestAPIRegistrationResponse response = new RestAPIRegistrationResponse();
            response.setResponse(jsonResponse);

            return response;
        } catch (UnsupportedEncodingException unsupportedE) {
            throw new APIManagerIntegrationTestException(
                    "Header encoding was unsuccessful while registering application.", unsupportedE);
        } catch (MalformedURLException malformedE) {
            throw new APIManagerIntegrationTestException("Error in getting the DCR endpoint URL.", malformedE);
        } catch (AutomationFrameworkException automationE) {
            throw new APIManagerIntegrationTestException("Error in sending request to the DCR endpoint.", automationE);
        } catch (JSONException jsonE) {
            throw new APIManagerIntegrationTestException("Error in parsing JSON to get consumer key/secret.", jsonE);
        }
    }

    /**
     * Generates OAuth access token required to invoke the REST API
     * <p>Token will be validated for expiry time and refreshed if expiry time
     * is less that <code>TOKEN_EXPIRY_MIN_LIMIT</code></p>
     * This is done inorder to prevent token from expiring before test case completes
     *
     * @param client   OAuth application details
     * @param scopes   scopes required to create the token
     * @param username username of the user who need the access token
     * @param password password of the user who need the access token
     * @return generated access token or <code>null</code> if failed to
     * generate the token
     * @throws APIManagerIntegrationTestException if an error occurs while
     *                                            generating access token
     */
    public String generateOAuthAccessToken(RestAPIRegistrationResponse client, String[] scopes, String username,
            String password) throws APIManagerIntegrationTestException {

        try {
            RestAPITokenRequest tokenRequest = new RestAPITokenRequest();
            tokenRequest.setGrantType(GRANT_TYPE_PASSWORD);
            tokenRequest.setUserName(username);
            tokenRequest.setPassword(password);

            for (String scope : scopes) {
                tokenRequest.addScope(scope);
            }

            String messageBody = buildTokenAPIRequest(tokenRequest);
            String encodedClientToken = getEncodedCredentials(client.getClientID(), client.getClientSecret());
            URL tokenEndpointURL = new URL(gatewayNURL + TOKEN_ENDPOINT_SUFFIX);
            HashMap<String, String> headers = new HashMap<String, String>();

            headers.put(AUTHORIZATION_KEY, "Basic " + encodedClientToken);
            JSONObject tokenGenDataJson = new JSONObject(HttpRequestUtil.doPost(tokenEndpointURL, messageBody, headers).getData());
            RestAPITokenResponse response = new RestAPITokenResponse();
            response.setResponse(tokenGenDataJson);
            String accessToken = response.getAccessToken();

            if (accessToken != null) {

                // refresh expiring access token
                String refreshToken = response.getRefreshToken();
                Long expiresIn = response.getValidityTime();
                String validatedToken = refreshExpiringToken(expiresIn, refreshToken, scopes, encodedClientToken);

                return validatedToken == null ? accessToken : validatedToken;
            }
        } catch (MalformedURLException malformedE) {
            throw new APIManagerIntegrationTestException("Error getting the URL of token endpoint.", malformedE);
        } catch (AutomationFrameworkException automationE) {
            throw new APIManagerIntegrationTestException("Error sending the request to token endpoint.", automationE);
        } catch (JSONException jsonE) {
            throw new APIManagerIntegrationTestException("Error parsing JSON content from token endpoint.", jsonE);
        } catch (UnsupportedEncodingException unsupE) {
            throw new APIManagerIntegrationTestException("Error encoding client credentials", unsupE);
        }
        return null;
    }

    /**
     * Verifies if <code>expireTime</code> of the current token is within
     * a defined time and refresh the access token to get longer expiry
     * period. This is to allow test cases to have maximum use of the token.
     *
     * @param expireTime         expire time of current token
     * @param refreshToken       refresh token for current access token
     * @param scopes             scopes required with refreshed access token
     * @param encodedClientToken encoded client credentials of OAth application
     * @return refreshed accessed token if refreshed or <code>null</code>
     * if not refreshed
     * @throws APIManagerIntegrationTestException
     */
    private String refreshExpiringToken(long expireTime, String refreshToken, String[] scopes,
            String encodedClientToken) throws APIManagerIntegrationTestException {
        String refreshedToken = null;
        try {

            //check if token expires in short period of time and refresh the token if expiring period is short
            if (expireTime <= TOKEN_EXPIRY_MIN_LIMIT) {
                RestAPITokenRequest refreshRequest = new RestAPITokenRequest();
                refreshRequest.setGrantType(GRANT_TYPE_PASSWORD);
                refreshRequest.setRefreshToken(refreshToken);

                for (String scope : scopes) {
                    refreshRequest.addScope(scope);
                }

                String messageBody = buildRefreshTokenRequest(null);
                URL tokenEndpointURL = new URL(gatewayNURL + TOKEN_ENDPOINT_SUFFIX);
                HashMap<String, String> headers = new HashMap<String, String>();

                headers.put(AUTHORIZATION_KEY, "Basic " + encodedClientToken);
                JSONObject tokenGenDataJson = new JSONObject(HttpRequestUtil.doPost(tokenEndpointURL, messageBody, headers));
                RestAPITokenResponse response = new RestAPITokenResponse();
                response.setResponse(tokenGenDataJson);
                refreshedToken = response.getAccessToken();
            }
        } catch (MalformedURLException malformedE) {
            throw new APIManagerIntegrationTestException("Error in getting the URL of token endpoint.", malformedE);
        } catch (AutomationFrameworkException automationE) {
            throw new APIManagerIntegrationTestException("Error sending the request to token endpoint.", automationE);
        } catch (JSONException jsonE) {
            throw new APIManagerIntegrationTestException("Error parsing JSON content from token endpoint.", jsonE);
        }

        return refreshedToken;
    }

    /**
     * @param appDetails application details
     * @return application registration request with request details
     * @throws JSONException
     */
    private JSONObject buildAppRegistrationRequest(RestAPIRegistrationRequest appDetails) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(DCRParams.CALLBACK_URL, appDetails.getCallbackURL());
        jsonObject.put(DCRParams.CLIENT_NAME, appDetails.getClientName());
        jsonObject.put(DCRParams.OWNER, appDetails.getOwner());
        jsonObject.put(DCRParams.GRANT_TYPE, appDetails.getSupportedGrantTypes());
        jsonObject.put(DCRParams.SAAS_APP, appDetails.isSaaSApp());

        return jsonObject;
    }

    /**
     * @param request token request details
     * @return token api request with required request information
     */
    private String buildTokenAPIRequest(RestAPITokenRequest request) {
        StringBuilder tokenAPIRequest = new StringBuilder();

        String grantType = request.getGrantType();
        if (GRANT_TYPE_PASSWORD.equalsIgnoreCase(grantType)) {
            appendParam(tokenAPIRequest, TokenAPIParams.GRANT_TYPE, GRANT_TYPE_PASSWORD);
            appendParam(tokenAPIRequest, TokenAPIParams.USER_NAME, request.getUserName());
            appendParam(tokenAPIRequest, TokenAPIParams.PASSWORD, request.getPassword());
            String scopes = getScopeListParam(request.getScopes());
            appendParam(tokenAPIRequest, TokenAPIParams.SCOPE, scopes);
        }

        return tokenAPIRequest.toString();
    }

    /**
     * @param request token request details
     * @return refresh token api request with required request information
     */
    private String buildRefreshTokenRequest(RestAPITokenRequest request) {
        StringBuilder tokenAPIRequest = new StringBuilder();
        String grantType = request.getGrantType();

        if (GRANT_TYPE_PASSWORD.equalsIgnoreCase(grantType)) {
            appendParam(tokenAPIRequest, TokenAPIParams.GRANT_TYPE, grantType);
            appendParam(tokenAPIRequest, TokenAPIParams.REFRESH_TOKEN, grantType);
            String scopes = getScopeListParam(request.getScopes());
            appendParam(tokenAPIRequest, TokenAPIParams.SCOPE, scopes);
        }

        return tokenAPIRequest.toString();
    }

    /**
     * Append parameter to HTTP request string
     *
     * @param paramBuilder StringBuilder use to append the parameter
     * @param param        parameter to append
     * @param value        values of the parameter
     */
    private void appendParam(StringBuilder paramBuilder, String param, String value) {
        if (paramBuilder.length() != 0) {
            paramBuilder.append('&');
        }

        paramBuilder.append(param);
        paramBuilder.append('=');
        paramBuilder.append(value);
    }

    /**
     * @param scopes list of scopes
     * @return string with all scopes appended
     */
    private String getScopeListParam(List<String> scopes) {
        StringBuilder scopeString = new StringBuilder();

        if (!scopes.isEmpty()) {
            for (String scope : scopes) {
                if (scopeString.length() != 0) {
                    scopeString.append(' ');
                }
                scopeString.append(scope);
            }
        }

        return scopeString.toString();
    }

    /**
     * Retrieves an initialized {@link ApiClient}
     *
     * @param basePath base path of the API
     *                 @param configBean client configuration details
     * @return swagger api client initialized for provided configurations
     * @throws APIManagerIntegrationTestException
     */
    private ApiClient getApiClient(String basePath, RestAPIClientBean configBean)
            throws APIManagerIntegrationTestException {
        ApiClient storeClient = new ApiClient();
        storeClient.setBasePath(basePath);
        RestAPIRegistrationResponse response = createOAuthApplication(configBean.getClientName());

        String accessToken = generateOAuthAccessToken(response, configBean.getScopes(), configBean.getUsername(),
                configBean.getPassword());
        storeClient.setAccessToken(accessToken);

        return storeClient;
    }

    /**
     * Calculate the Base64 encoded value of clientID and clientSecret
     *
     * @throws UnsupportedEncodingException
     */
    private String getEncodedCredentials(String clientID, String clientSecret) throws UnsupportedEncodingException {
        String tokenBeforeEncode = clientID + ":" + clientSecret;
        byte[] encodedBytes = Base64.encodeBase64(tokenBeforeEncode.getBytes(CHARSET));
        return new String(encodedBytes, CHARSET);
    }

}
