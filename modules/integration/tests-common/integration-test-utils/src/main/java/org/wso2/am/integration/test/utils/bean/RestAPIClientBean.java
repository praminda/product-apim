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

/**
 * Bean to hold the configuration parameters of API generation client request
 * username and password defaults to 'admin' if not provided
 */
public class RestAPIClientBean {

    //scopes needed with the access token
    private String[] scopes;

    //username of the user who is requesting the access token
    private String username;

    //password of the user who is requesting the access token
    private String password;

    //OAuth application name
    private String clientName;

    public RestAPIClientBean() {
        username = "admin";
        password = "admin";
    }

    public String[] getScopes() {
        return scopes;
    }

    public void setScopes(String[] scopes) {
        this.scopes = scopes;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (username == null || "".equals(username)) {
            this.username = "admin";
        } else {
            this.username = username;
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password == null || "".equals(password)) {
            this.password = "admin";
        } else {
            this.password = password;
        }
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = "".equals(clientName) ? null : clientName;
    }
}
