package tr.nttdata.poc.minicommerce.service.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import tr.nttdata.poc.minicommerce.model.Cart;
import tr.nttdata.poc.minicommerce.model.CartItem;
import tr.nttdata.poc.minicommerce.model.Order;

@FeignClient("MINICOMMERCEORDER")
public interface OrderFeignClient {
    @PostMapping(value = "orders", consumes = "application/json", produces = "application/json")
    Order createOrder(@RequestBody Order order);

    @GetMapping(value = "/orders", consumes = "application/json")
    List<Order> getAllOrders();

    @GetMapping(value = "/orders/cart", consumes = "application/json")
    Cart getCart();

    @GetMapping(value = "/orders/{orderId}", consumes = "application/json")
    Cart getOrderById(@PathVariable("orderId") String orderId);

    @PutMapping(value = "/orders/{orderId}", consumes = "application/json", produces = "application/json")
    Cart updateOrder(@PathVariable("orderId") String orderId, @RequestBody Order updatedOrder);

    @PostMapping(value = "/orders/addToCart", consumes = "application/json", produces = "application/json")
    CartItem addToCart(@RequestBody CartItem cartItem);

    @PostMapping(value = "/orders/removeFromCart", consumes = "application/json", produces = "application/json")
    void removeFromCart(@RequestBody CartItem cartItem);

    @PostMapping(value = "/orders/checkout/{customerId}", produces = "application/json")
    Cart checkout(@PathVariable("orderId") String customerId);

}
