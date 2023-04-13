package tr.nttdata.poc.minicommerce.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import tr.nttdata.poc.minicommerce.model.Customer;
import tr.nttdata.poc.minicommerce.model.login.LoginRequest;

@FeignClient("MINICOMMERCECUSTOMER")
public interface CustomerFeignClient {

    @GetMapping(value = "/customers/{id}", consumes = "application/json")
    Customer getCustomerById(@PathVariable("id") String id);

    @GetMapping(value = "/customers/email/{email}", consumes = "application/json")
    Customer getCustomerByEmail(@PathVariable("email") String email);

    @PutMapping(value = "/customers/{id}", consumes = "application/json", produces = "application/json")
    void updateCustomer(@PathVariable("id") String id, @RequestBody Customer customer);

    @DeleteMapping(value = "/customers/{id}")
    void deleteCustomer(@PathVariable("id") String id);

    @PostMapping(value = "/login", consumes = "application/json")
    String loginUser(@RequestBody LoginRequest loginRequest);

    @PostMapping(value = "/register", consumes = "application/json", produces = "application/json")
    Customer registerUser(@RequestBody Customer customer);

}
