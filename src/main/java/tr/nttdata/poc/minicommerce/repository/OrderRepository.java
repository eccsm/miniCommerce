package tr.nttdata.poc.minicommerce.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import tr.nttdata.poc.minicommerce.model.Order;



public interface OrderRepository extends MongoRepository<Order, String> {
    List<Order> findByCustomerId(String customerId);
}
