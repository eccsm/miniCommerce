package tr.nttdata.poc.minicommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import tr.nttdata.poc.minicommerce.config.ServiceConfig;
import tr.nttdata.poc.minicommerce.model.Cart;
import tr.nttdata.poc.minicommerce.model.CartItem;
import tr.nttdata.poc.minicommerce.model.Customer;
import tr.nttdata.poc.minicommerce.model.Order;
import tr.nttdata.poc.minicommerce.model.Product;
import tr.nttdata.poc.minicommerce.model.Properties;
import tr.nttdata.poc.minicommerce.model.login.LoginRequest;
import tr.nttdata.poc.minicommerce.service.client.CustomerFeignClient;
import tr.nttdata.poc.minicommerce.service.client.OrderFeignClient;
import tr.nttdata.poc.minicommerce.service.client.ProductFeignClient;

@RestController
@RequestMapping("/api")
public class MiniCommerceController {

    @Autowired
    ServiceConfig serviceConfig;

    @Autowired
    ProductFeignClient productFeignClient;

    @Autowired
    OrderFeignClient orderFeignClient;

    @Autowired
    CustomerFeignClient customerFeignClient;

    @GetMapping("/properties")
    public String getPropertyDetails() throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        Properties properties = new Properties(serviceConfig.getMsg(), serviceConfig.getBuildVersion(),
                serviceConfig.getMailDetails(), serviceConfig.getActiveBranches());
        String jsonStr = ow.writeValueAsString(properties);
        return jsonStr;
    }

    @GetMapping("/products")
    public List<Product> products() {
        return productFeignClient.getProducts();
    }

    @GetMapping(value = "/products/{id}")
    Product getProductById(@PathVariable("id") Long id) {
        return productFeignClient.getProductById(id);
    }

    @PostMapping(value = "/products", consumes = "application/json", produces = "application/json")
    Product createProduct(@RequestBody Product product) {
        return productFeignClient.createProduct(product);
    }

    @PutMapping(value = "/products/{id}", consumes = "application/json", produces = "application/json")
    Product updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        return productFeignClient.updateProduct(id, product);

    }

    @DeleteMapping(value = "/products/{id}")
    void deleteProduct(@PathVariable("id") Long id) {
        productFeignClient.deleteProduct(id);
    }

    @GetMapping(value = "/customers/{id}")
    Customer getCustomerById(@PathVariable("id") String id) {
        return customerFeignClient.getCustomerById(id);
    }

    @GetMapping(value = "/customers/email/{email}")
    Customer getCustomerByEmail(@PathVariable("email") String email) {
        return customerFeignClient.getCustomerByEmail(email);
    }

    @PutMapping(value = "/customers/{id}")
    void updateCustomer(@PathVariable("id") String id, @RequestBody Customer customer) {
        customerFeignClient.updateCustomer(id, customer);
    }

    @DeleteMapping(value = "/customers/{id}")
    void deleteCustomer(@PathVariable("id") String id) {
        customerFeignClient.deleteCustomer(id);
    }

    @PostMapping(value = "/login")
    String loginUser(@RequestBody LoginRequest loginRequest) {
        return customerFeignClient.loginUser(loginRequest);
    }

    @PostMapping(value = "/register")
    Customer registerUser(@RequestBody Customer customer) {
        return customerFeignClient.registerUser(customer);
    }

    @PostMapping(value ="/orders")
    Order createOrder(@RequestBody Order order) {
        return orderFeignClient.createOrder(order);
    }

    @GetMapping(value ="/orders")
    List<Order> getAllOrders() {
        return orderFeignClient.getAllOrders();
    }

    @GetMapping(value ="/orders/cart")
    Cart getCart() {
        return orderFeignClient.getCart();
    }

    @GetMapping(value ="/orders/{orderId}")
    Cart getOrderById(@PathVariable String orderId) {
        return orderFeignClient.getOrderById(orderId);
    }

    @PutMapping(value ="/orders/{orderId}")
    Cart updateOrder(@PathVariable String orderId, @RequestBody Order updatedOrder) {
        return orderFeignClient.updateOrder(orderId, updatedOrder);
    }

    @PostMapping(value ="/ordersaddToCart")
    CartItem addToCart(@RequestBody CartItem cartItem) {
        return orderFeignClient.addToCart(cartItem);
    }

    @PostMapping(value ="/ordersremoveFromCart")
    void removeFromCart(@RequestBody CartItem cartItem) {
        orderFeignClient.removeFromCart(cartItem);
    }

    @PostMapping(value ="/orders/checkout/{customerId}")
    Cart checkout(@PathVariable String customerId) {
        return orderFeignClient.checkout(customerId);
    }
}
