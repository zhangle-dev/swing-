package com.orderfood.mapper;

import java.util.List;

import com.orderfood.pojo.Order;
import com.orderfood.pojo.OrderMenu;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface OrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);
    
    List<OrderMenu> findOrdetailByOrderId(Integer id);
    Order selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    List<Order> findOrders();

    List<OrderMenu> findMenuNum(@Param("date") String date,@Param("date1") String date1);
}