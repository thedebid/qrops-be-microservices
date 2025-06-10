package np.com.debid.qropsorderservice.controller;

import jakarta.validation.Valid;
import np.com.debid.qropsorderservice.dto.OrderRequest;
import np.com.debid.qropsorderservice.dto.OrderResponse;
import np.com.debid.qropsorderservice.entity.Order;
import np.com.debid.qropsorderservice.service.OrderService;
import np.com.debid.restrocommons.util.ResponseUtil;
import np.com.debid.restrocommons.util.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

import static np.com.debid.qropsorderservice.constant.Constant.Messages.ORDERS_FETCHED;
import static np.com.debid.qropsorderservice.constant.Constant.Messages.ORDER_CREATED;
import static np.com.debid.qropsorderservice.constant.Constant.Messages.ORDER_FETCHED;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<ResponseWrapper<Order>> createOrder(@Valid @RequestBody OrderRequest orderRequest) {
        return ResponseUtil.successResponse(ORDER_CREATED, orderService.createOrder(orderRequest));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ResponseWrapper<OrderResponse>> getOrderByCategpry(@PathVariable Long id) {
        return ResponseUtil.successResponse(ORDER_FETCHED, orderService.getOrdersById(id));
    }

    @GetMapping("/get/restaurant")
    public ResponseEntity<ResponseWrapper<List<OrderResponse>>> getAllCategoriesByRestaurant(@RequestHeader("userId") Long userId, @RequestHeader("X-TenantID") String tenantId) {
        UUID tenantUUID = UUID.fromString(tenantId);
        this.orderService.validateRestaurantWithUser(userId, tenantUUID);
        return ResponseUtil.successResponse(ORDERS_FETCHED, orderService.getAllOrdersByRestaurant(tenantUUID));
    }
}
