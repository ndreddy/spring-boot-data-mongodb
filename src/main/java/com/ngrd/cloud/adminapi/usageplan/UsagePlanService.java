package com.ngrd.cloud.adminapi.usageplan;

import com.amazonaws.services.apigateway.AmazonApiGateway;
import com.amazonaws.services.apigateway.AmazonApiGatewayClient;
import com.amazonaws.services.apigateway.model.*;
import com.amazonaws.services.apigateway.model.UsagePlan;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsagePlanService {
    static final AmazonApiGateway apiGwClient = AmazonApiGatewayClient.builder().defaultClient();

    public List<UsagePlan> getUsagePlans() {
        GetUsagePlansRequest req = new GetUsagePlansRequest();
        GetUsagePlansResult res = apiGwClient.getUsagePlans(req);
        return res.getItems();
    }

    public CreateUsagePlanResult createUsagePlan(CreateUsagePlanRequest req) {
        CreateUsagePlanResult res = apiGwClient.createUsagePlan(req);
        return res;
    }

    public CreateUsagePlanKeyResult createUsagePlanKey(CreateUsagePlanKeyRequest req) {
        return apiGwClient.createUsagePlanKey(req);
    }

    public DeleteUsagePlanKeyResult createUsagePlanKey(DeleteUsagePlanKeyRequest req) {
        return apiGwClient.deleteUsagePlanKey(req);
    }

    public String createUsagePlanKey(String keyId, String usagePlanId) {
        CreateUsagePlanKeyRequest req = new CreateUsagePlanKeyRequest()
                .withKeyId(keyId).withKeyType("API_KEY")
                .withUsagePlanId(usagePlanId);
        CreateUsagePlanKeyResult res = apiGwClient.createUsagePlanKey(req);
        return res.getId();

    }

    public String getUsagePlanKey(String keyId) {
        GetUsagePlanKeyRequest req = new GetUsagePlanKeyRequest()
                .withKeyId(keyId);

        GetUsagePlanKeyResult res = apiGwClient.getUsagePlanKey(req);
        return res.getValue();
    }

    public DeleteUsagePlanKeyResult deleteUsagePlanKey(String usagePlanKeyId, String usagePlanId) {
        DeleteUsagePlanKeyRequest req = new DeleteUsagePlanKeyRequest()
                .withKeyId(usagePlanKeyId).withUsagePlanId(usagePlanId);
        return apiGwClient.deleteUsagePlanKey(req);
    }
}
