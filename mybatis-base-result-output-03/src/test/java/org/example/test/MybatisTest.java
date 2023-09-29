package org.example.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.example.mapper.EmployeeMapper;
import org.example.pojo.Employee;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * projectName: com.atguigu.test
 *
 * @author: 赵伟风
 * description:
 */
public class MybatisTest {

    @Test
    public void testQueryEmpNameAndSalary() throws IOException {
        InputStream ips = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(ips);
        SqlSession session = sqlSessionFactory.openSession(true);

        EmployeeMapper employeeMapper = session.getMapper(EmployeeMapper.class);
        Map<String, Object> resultMap = employeeMapper.selectEmpNameAndMaxSalary();
        resultMap.forEach((key, value) -> System.out.println("key = " + key + " value = " + value));

        List<Employee> employeeList = employeeMapper.queryAll();
        for (Employee employee : employeeList) {
            System.out.println("employee = " + employee);
        }
    }

    @Test
    public void test_01() throws IOException {

        InputStream ips = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(ips);
        //openSession自动开启事务,不会自动提交 !  sqlSession.commit();
        //openSession(true)自动开启事务,自动提交事务!  不需要sqlSession.commit();
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);

        Employee employee = new Employee();
        employee.setEmpSalary(8888.0);
        employee.setEmpName("二狗子1");
        System.out.println(employee.getEmpId());

        System.out.println("----------------------");

        int rows = mapper.insertEmp(employee);
        System.out.println(employee.getEmpId());
        System.out.println("rows = " + rows);

        //5.释放资源 和 提交事务
        //sqlSession.commit(); //DML语句
        sqlSession.close();

    }
}
