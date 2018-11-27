package com.ngrd.cloud.adminapi.usageplan;

import com.amazonaws.services.apigateway.model.*;
import com.amazonaws.services.apigateway.model.UsagePlan;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/usageplans")
public class UsagePlanController {
    UsagePlanService service;

    public UsagePlanController(UsagePlanService service) {
        this.service = service;
    }

    @GetMapping
    public List<UsagePlan> getUsagePlan(){
        return service.getUsagePlans();
    }

    @PostMapping
    public CreateUsagePlanResult createUsagePlan(@RequestBody CreateUsagePlanRequest usagePlan){
        return service.createUsagePlan(usagePlan);
    }

    @PostMapping("/{keyId}/keys")
    public CreateUsagePlanKeyResult createUsagePlanKey(@RequestBody CreateUsagePlanKeyRequest key){
        return service.createUsagePlanKey(key);
    }
}
