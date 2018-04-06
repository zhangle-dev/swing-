package com.orderfood.mapper;

import java.util.List;

import com.orderfood.pojo.Order;
import com.orderfood.pojo.OrderMenu;

public interface OrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);
    
    List<OrderMenu> findOrdetailByOrderId(Integer id);
    Order selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
}