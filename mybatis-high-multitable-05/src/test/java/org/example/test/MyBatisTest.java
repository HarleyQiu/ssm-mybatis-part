package org.example.test;


import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.example.mapper.CustomerMapper;
import org.example.mapper.OrderMapper;
import org.example.pojo.Customer;
import org.example.pojo.Order;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

@Slf4j
public class MyBatisTest {

    private SqlSession session;

    // junit会在每一个@Test方法前执行@BeforeEach方法
    @BeforeEach
    public void init() throws IOException {
        session = new SqlSessionFactoryBuilder()
                .build(
                        Resources.getResourceAsStream("mybatis-config.xml"))
                .openSession();
    }

    @Test
    public void testRelationshipToOne() {
        OrderMapper orderMapper = session.getMapper(OrderMapper.class);
        // 查询Order对象，检查是否同时查询了关联的Customer对象
        Order order = orderMapper.queryOrderById(1);
        System.out.println("order = " + order);
    }

    @Test
    public void testRelationshipToMulti() {

        CustomerMapper customerMapper = session.getMapper(CustomerMapper.class);
        List<Customer> customers = customerMapper.queryList();
        for (Customer customer : customers) {
            System.out.println("customers = " + customer);
            List<Order> orderList = customer.getOrderList();
            System.out.println("orderList = " + orderList);
        }
    }

    // junit会在每一个@Test方法后执行@@AfterEach方法
    @AfterEach
    public void clear() {
        session.commit();
        session.close();
    }
}