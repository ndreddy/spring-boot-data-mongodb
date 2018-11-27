package com.ngrd.cloud.adminapi.aws;

import com.amazonaws.services.apigateway.AmazonApiGateway;
import com.amazonaws.services.apigateway.AmazonApiGatewayClientBuilder;
import com.amazonaws.services.apigateway.model.*;
import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagement;
import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagementClientBuilder;
import com.amazonaws.services.simplesystemsmanagement.model.*;

public class AwsUtil {
    static final AmazonApiGateway apiGwClient = AmazonApiGatewayClientBuilder.defaultClient();
    static final AWSSimpleSystemsManagement ssmClient = AWSSimpleSystemsManagementClientBuilder.defaultClient();
    /**
     * Create an api key for a customer
     *
     * @param name - name of the key
     * @return - Json
     */
    public static ApiKey addApiKey(String name) {
        CreateApiKeyRequest req = new CreateApiKeyRequest()
                .withName(name).withEnabled(true);
        CreateApiKeyResult res = apiGwClient.createApiKey(req);
        return new ApiKey().withName(res.getName()).withId(res.getId()).withValue(res.getValue());
    }


    public static void deleteApiKey(String key) {
        DeleteApiKeyRequest req = new DeleteApiKeyRequest()
                .withApiKey(key);
        DeleteApiKeyResult res = apiGwClient.deleteApiKey(req);
    }


    // *********** Parameter Store ***********
    /**
     * Adds App Url to config store (param store)
     * @param name
     * @param value
     */
    public static void addAppUrl(String name, String value) {
        addParameterFromSSMByName(name, value);
    }

    /**
     * Deletes App Url from store.
     * @param name
     */
    public static void deleteAppUrl(String name) {
        deleteParameterFromSSMByName(name);
    }


    /**
     * Adds and updates param store.
     *
     * @param name
     * @param value
     */
    public static void addParameterFromSSMByName(String name, String value) {
        PutParameterRequest req = new PutParameterRequest()
                .withName(name).withValue(value).withType(ParameterType.String).withOverwrite(true);
        ssmClient.putParameter(req);
    }

    /**
     * Deletes from param store
     * @param name
     */
    public static void deleteParameterFromSSMByName(String name) {
        DeleteParameterRequest req = new DeleteParameterRequest()
                .withName(name);
        ssmClient.deleteParameter(req);
    }
}
