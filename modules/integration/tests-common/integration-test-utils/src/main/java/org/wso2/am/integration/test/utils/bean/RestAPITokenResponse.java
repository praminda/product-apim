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

package org.wso2.am.integration.test.utils.bean;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Bean used to store information returned when generating an access token
 */
public class RestAPITokenResponse {
    private static class JSONResponseKeys {
        private static final String SCOPE = "scope";
        private static final String TOKEN_TYPE = "token_type";
        private static final String VALIDITY_PERIOD = "expires_in";
        private static final String REFRESH_TOKEN = "refresh_token";
        private static final String ACCESS_TOKEN = "access_token";
    }

    private List<String> scopes = new ArrayList<String>();
    private String tokenType;
    private long validityTime;
    private String refreshToken;
    private String accessToken;

    public void setResponse(JSONObject response) throws JSONException {
        String scopeString = response.getString(JSONResponseKeys.SCOPE);
        scopes = Arrays.asList(scopeString.split(" "));
        tokenType = response.getString(JSONResponseKeys.TOKEN_TYPE);
        validityTime = response.getLong(JSONResponseKeys.VALIDITY_PERIOD);
        refreshToken = response.getString(JSONResponseKeys.REFRESH_TOKEN);
        accessToken = response.getString(JSONResponseKeys.ACCESS_TOKEN);
    }

    public List<String> getScopes() {
        return scopes;
    }

    public String getTokenType() {
        return tokenType;
    }

    public long getValidityTime() {
        return validityTime;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public String getAccessToken() {
        return accessToken;
    }
}
