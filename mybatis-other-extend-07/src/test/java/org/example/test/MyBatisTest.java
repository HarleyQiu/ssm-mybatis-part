package org.example.test;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.example.mapper.EmployeeMapper;
import org.example.pojo.Employee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

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

    //测试if where标签
    @Test
    public void test_01() {
        EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
        PageHelper.startPage(2, 2);

        List<Employee> list = mapper.queryList();

        PageInfo<Employee> employeePageInfo = new PageInfo<>(list);

        System.out.println("employeePageInfo.getPageNum() = " + employeePageInfo.getPageNum());
        System.out.println("employeePageInfo.getPageSize() = " + employeePageInfo.getPageSize());
    }


    // junit会在每一个@Test方法后执行@@AfterEach方法
    @AfterEach
    public void clear() {
        session.commit();
        session.close();
    }
}
