package com.natureblessings.orderService.service;

import com.natureblessings.orderService.client.ProductClient;
import com.natureblessings.orderService.dto.OrderDTO;
import com.natureblessings.orderService.dto.OrderItemDTO;
import com.natureblessings.orderService.dto.ProductDTO;
import com.natureblessings.orderService.entity.Order;
import com.natureblessings.orderService.entity.OrderItem;
import com.natureblessings.orderService.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductClient productClient;

    public OrderServiceImpl(OrderRepository orderRepository,
                            ProductClient productClient) {
        this.orderRepository = orderRepository;
        this.productClient = productClient;
    }

    // 1️⃣ Create Order
    @Override
    public OrderDTO saveOrder(OrderDTO orderDTO) {

        List<OrderItem> orderItems = orderDTO.getItems().stream()
                .map(itemDTO -> {

                    ProductDTO product =
                            productClient.getProductById(itemDTO.getProductId());

                    return OrderItem.builder()
                            .productId(product.getId())
                            .productName(product.getName())
                            .price(product.getPrice())
                            .quantity(itemDTO.getQuantity())
                            .build();
                })
                .toList();

        double totalPrice = orderItems.stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();

        Order order = Order.builder()
                .items(orderItems)
                .totalPrice(totalPrice)
                .build();

        orderItems.forEach(item -> item.setOrder(order));

        Order savedOrder = orderRepository.save(order);

        return mapToDTO(savedOrder);
    }

    // 2️⃣ Get All Orders
    @Override
    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    // 3️⃣ Get Order By ID
    @Override
    public OrderDTO getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
        return mapToDTO(order);
    }

    // 4️⃣ Delete Order
    @Override
    public void deleteOrder(Long id) {

        if (!orderRepository.existsById(id)) {
            throw new RuntimeException("Order not found with id: " + id);
        }

        orderRepository.deleteById(id);
    }

    // 🔁 Entity → DTO Mapper
    private OrderDTO mapToDTO(Order order) {
        return OrderDTO.builder()
                .orderId(order.getId())
                .totalPrice(order.getTotalPrice())
                .items(
                        order.getItems().stream()
                                .map(item -> OrderItemDTO.builder()
                                        .productId(item.getProductId())
                                        .quantity(item.getQuantity())
                                        .build())
                                .toList()
                )
                .build();
    }
}
