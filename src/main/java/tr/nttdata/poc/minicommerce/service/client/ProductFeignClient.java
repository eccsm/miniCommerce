package tr.nttdata.poc.minicommerce.service.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import tr.nttdata.poc.minicommerce.model.Product;

@FeignClient("MINICOMMERCEPRODUCT")
public interface ProductFeignClient {
    @GetMapping(value = "/products", consumes = "application/json")
    List<Product> getProducts();

    @GetMapping(value = "/products/{id}", consumes = "application/json")
    Product getProductById(@PathVariable("id") Long id);

    @PostMapping(value = "/products", consumes = "application/json", produces = "application/json")
    Product createProduct(@RequestBody Product product);

    @PutMapping(value = "/products/{id}", consumes = "application/json", produces = "application/json")
    Product updateProduct(@PathVariable("id") Long id, @RequestBody Product product);

    @DeleteMapping(value = "/products/{id}")
    void deleteProduct(@PathVariable("id") Long id);
}
