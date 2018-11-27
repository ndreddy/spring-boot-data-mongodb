package com.ngrd.cloud.adminapi.customer;

import com.ngrd.cloud.adminapi.usageplan.UsagePlanService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/customers")
public class CustomerController {

    private CustomerService customerService;
    private UsagePlanService usagePlanService;

    public CustomerController(CustomerService customerService, UsagePlanService usagePlanService) {
        this.customerService = customerService;
        this.usagePlanService = usagePlanService;
    }

    @GetMapping
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    @PostMapping
    public Customer addCustomer(@RequestBody Customer customer) {
        return customerService.addCustomer(customer);
    }

    @PutMapping
    public Customer updateCustomer(@RequestBody Customer customer) {
        return customerService.updateCustomer(customer);
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable String id) {
        customerService.deleteCustomer(id);
    }
}
