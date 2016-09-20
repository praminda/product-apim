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

/**
 * Bean used to store information returned when registering a new OAuth Application
 */
public class RestAPIRegistrationResponse {

    private static class JSONResponseKeys {
        private static final String CALLBACK_URL = "callBackURL";
        private static final String JSON_STRING = "jsonString";
        private static final String CLIENT_NAME = "clientName";
        private static final String CLIENT_ID = "clientId";
        private static final String CLIENT_SECRET = "clientSecret";
    }

    private String callbackURL;
    private String clientName;
    private String clientID;
    private String clientSecret;
    private JSONObject jsonString = null;

    public String getCallbackURL() {
        return callbackURL;
    }

    public JSONObject getJsonString() {
        return jsonString;
    }

    public String getClientName() {
        return clientName;
    }

    public String getClientID() {
        return clientID;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setResponse(JSONObject response) throws JSONException {
        callbackURL = response.getString(JSONResponseKeys.CALLBACK_URL);
        jsonString = response.getJSONObject(JSONResponseKeys.JSON_STRING);
        clientName = response.getString(JSONResponseKeys.CLIENT_NAME);
        clientID =  response.getString(JSONResponseKeys.CLIENT_ID);
        clientSecret = response.getString(JSONResponseKeys.CLIENT_SECRET);
    }
}
