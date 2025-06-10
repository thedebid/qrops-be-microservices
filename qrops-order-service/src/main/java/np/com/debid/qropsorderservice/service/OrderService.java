package np.com.debid.qropsorderservice.service;


import np.com.debid.qropsorderservice.dto.OrderRequest;
import np.com.debid.qropsorderservice.dto.OrderResponse;
import np.com.debid.qropsorderservice.entity.Order;
import np.com.debid.qropsorderservice.repository.OrderRepository;
import np.com.debid.restrocommons.dto.ValidateDTO;
import np.com.debid.restrocommons.exception.CustomException;
import np.com.debid.restrocommons.service.RestaurantClient;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static np.com.debid.qropsorderservice.constant.Constant.ErrorCodes.ORDER_NOT_FOUND_ERROR_CODE;
import static np.com.debid.qropsorderservice.constant.Constant.Messages.ORDER_NOT_FOUND;
import static np.com.debid.restrocommons.constant.Constant.ErrorCodes.UNAUTHORIZED_ACCESS_IN_RESTAURANT_DATA_ERROR_CODE;
import static np.com.debid.restrocommons.constant.Constant.Messages.UNAUTHORIZED_ACCESS_IN_RESTAURANT_DATA;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private RestaurantClient restaurantClient;
    @Autowired
    ModelMapper modelMapper;

    public Order createOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setTableNumber(orderRequest.getTableNumber());
        order.setRestaurantId(orderRequest.getRestaurantId());

        return orderRepository.save(order);
    }

    public OrderResponse getOrdersById(Long id) {
        Order order = getOrder(id);
        return OrderResponse.builder()
                .id(order.getId())
                .restaurantId(order.getRestaurantId())
                .build();
    }

    private Order getOrder(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new CustomException(ORDER_NOT_FOUND, 404, ORDER_NOT_FOUND_ERROR_CODE));
    }

    public List<OrderResponse> getAllOrdersByRestaurant(UUID tenantId) {
        List<Order> orders = orderRepository.findByRestaurantId(tenantId);
        return orders.stream()
                .map(order -> modelMapper.map(order, OrderResponse.class))
                .map(order -> new OrderResponse(order.getId(), order.getRestaurantId()))
                .toList();
    }


    public void validateRestaurantWithUser(Long userId, UUID tenantId) {
        ValidateDTO validateDTO = new ValidateDTO();
        validateDTO.setTenantId(tenantId);
        validateDTO.setUserId(userId);
        if (!this.restaurantClient.validateRestaurant(validateDTO)) {
            // Restaurant ID and User ID mismatch
            throw new CustomException(UNAUTHORIZED_ACCESS_IN_RESTAURANT_DATA, 404, UNAUTHORIZED_ACCESS_IN_RESTAURANT_DATA_ERROR_CODE);
        }
    }
}
