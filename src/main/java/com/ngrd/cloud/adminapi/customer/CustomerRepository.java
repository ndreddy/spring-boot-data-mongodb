package com.ngrd.cloud.adminapi.customer;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {

    Customer findByName(String name);

    List<Customer> findByApiKey(String apiKey);

}