package com.ngrd.cloud.adminapi.customer;

import com.amazonaws.services.apigateway.model.ApiKey;
import com.ngrd.cloud.adminapi.aws.AwsUtil;
import com.ngrd.cloud.adminapi.usageplan.UsagePlanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static com.ngrd.cloud.adminapi.aws.AwsUtil.addApiKey;
import static com.ngrd.cloud.adminapi.aws.AwsUtil.addAppUrl;
import static com.ngrd.cloud.adminapi.aws.AwsUtil.deleteApiKey;


@Service
public class CustomerService {
    Logger logger = LoggerFactory.getLogger(CustomerService.class);


    @Autowired
    private UsagePlanService usagePlanService;

    @Autowired
    private CustomerRepository repository;


    public List<Customer> getCustomers() {
        return repository.findAll();
    }

    /**
     * Adds new customer on boarding.
     *
     * @param customer
     * @return
     */
    public Customer addCustomer(Customer customer) {
        logger.debug("On-boarding new customer = " + customer.toString());

        // 1. Create a new API Key
        logger.debug("On-boarding new customer - creating API Key ");
        ApiKey apiKey = addApiKey(customer.getName());
        customer.setApiKeyId(apiKey.getId());
        customer.setApiKey(apiKey.getValue());
        logger.info("On-boarding new customer - created API Key " + apiKey.getValue());

        // 2. Create usage plan association with API Key
        String usagePlanKeyId = usagePlanService.createUsagePlanKey(customer.getApiKeyId(), customer.getUsagePlanId());
        customer.setUsagePlanKeyId(usagePlanKeyId);
        logger.info("On-boarding new customer - created Usage Plan Key " + usagePlanKeyId);

        //3. Create AppUrl config
        addAppUrl(customer.getApiKey(), customer.getAppUrl());
        logger.info("On-boarding new customer - configured App Url ");

        // Save to Mongo DB
        repository.save(customer);
        logger.info("Saved customer to repository " + customer);
        return customer;
    }

    /**
     * Updates existing customer
     *
     * @param customer
     * @return
     */
    public Customer updateCustomer(Customer customer) {
        logger.debug("Updating existing customer  " + customer);
        Optional<Customer> old = repository.findById(customer.getId());

        // If old customer exists update Usage plan
        old.ifPresent(v -> {
            logger.debug("Customer exists " + old);
            if (!v.getUsagePlanId().equals(customer.getUsagePlanId())) {
                logger.debug("Usage plan changed, deleting old usage plan.");
                usagePlanService.deleteUsagePlanKey(v.getApiKeyId(), v.getUsagePlanId());
                String usagePlanKeyId = usagePlanService.createUsagePlanKey(customer.getApiKeyId(), customer.getUsagePlanId());
                customer.setUsagePlanKeyId(usagePlanKeyId);
                logger.info("Usage plan changes is processed.");
            }

            // Updates App URL
            if (!v.getAppUrl().equals(customer.getAppUrl())) {
                addAppUrl(v.getApiKey(), customer.getAppUrl());
            }

        });

        old.orElseThrow(NoSuchElementException::new);
        return repository.save(customer);
    }

    public void deleteCustomer(String customerId) {
        logger.debug("Deleting existing customer  " + customerId);
        Optional<Customer> old = repository.findById(customerId);
        old.ifPresent(v -> {
            logger.debug("Customer exists " + old);
//            logger.debug("Deleting usage plan association");
//            usagePlanService.deleteUsagePlanKey(v.getApiKeyId(), v.getUsagePlanId());
            logger.debug("Deleting API Key");
            deleteApiKey(v.getApiKeyId());
            logger.info("Deleted API Key and Usage Plan association");
            AwsUtil.deleteAppUrl(v.getApiKey());
            logger.info("Deleted App URL from config");
        });
        repository.deleteById(customerId);
    }


}
