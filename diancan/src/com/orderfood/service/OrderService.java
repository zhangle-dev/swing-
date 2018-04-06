package com.orderfood.service;

import com.orderfood.mapper.OrderDetailMapper;
import com.orderfood.mapper.OrderMapper;
import com.orderfood.mapper.UserMapper;
import com.orderfood.pojo.Order;
import com.orderfood.pojo.OrderDetail;
import com.orderfood.pojo.OrderMenu;
import com.orderfood.util.SqlSessionUtil;
import com.orderfood.util.StringUtil;
import com.zl.page.ItemCom;
import org.apache.ibatis.session.SqlSession;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by Administrator on 2018/4/6 0006.
 */
public class OrderService {

    public List<Order> findOrders() throws Exception {
        SqlSession session = SqlSessionUtil.getSqlSession();
        OrderMapper mapper = session.getMapper(OrderMapper.class);
        List<Order> list=mapper.findOrders();
        session.commit();
        session.close();
        return list;
    }

    public void update(Order order) throws Exception {
        SqlSession session = SqlSessionUtil.getSqlSession();
        OrderMapper mapper = session.getMapper(OrderMapper.class);
        mapper.updateByPrimaryKey(order);
        session.commit();
        session.close();
    }

    public void removeOrder(Order remove) throws Exception {
        SqlSession session = SqlSessionUtil.getSqlSession();
        OrderMapper mapper = session.getMapper(OrderMapper.class);
        mapper.deleteByPrimaryKey(remove.getId());
        session.commit();
        session.close();
    }

    public List<OrderMenu> findOrdeDetail(Integer id) throws Exception {
        SqlSession session = SqlSessionUtil.getSqlSession();
        OrderMapper mapper = session.getMapper(OrderMapper.class);
        List<OrderMenu> list = mapper.findOrdetailByOrderId(id);
        session.commit();
        session.close();
        return list;
    }

    public void save(Order order, List<ItemCom> list) {
        try(SqlSession session = SqlSessionUtil.getSqlSession();) {
            OrderMapper orderMapper = session.getMapper(OrderMapper.class);
             orderMapper.insert(order);
            int id = order.getId();
            OrderDetailMapper orderDetailMapper = session.getMapper(OrderDetailMapper.class);
            List<OrderDetail> orderDetails = list.stream().map(t -> {
                OrderDetail orde = new OrderDetail();
                orde.setMenuId(t.getMenuId());
                orde.setNum(t.getNums());
                orde.setOrderId(id);
                orderDetailMapper.insert(orde);
                return orde;
            }).collect(toList());
//            orderDetailMapper.insertBatch(orderDetails);
            session.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public List<OrderMenu> findMenuDetail(Date value,boolean woa) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(woa ? "yyyy-MM-dd"  : "yyyy-MM");
        String date = simpleDateFormat.format(value);
        List<OrderMenu> list=null;
        try (SqlSession sqlSession = SqlSessionUtil.getSqlSession()) {
            OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
            if (woa) {
                list = orderMapper.findMenuNum(date, date);
            }else {
                list = orderMapper.findMenuNum(date+"-"+ StringUtil.getDays(value),date+"-01");
            }
            sqlSession.commit();
            sqlSession.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
