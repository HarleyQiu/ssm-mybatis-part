package org.example.mapper;

import org.example.pojo.Order;

public interface OrderMapper {
    Order queryOrderById(Integer orderId);

}
