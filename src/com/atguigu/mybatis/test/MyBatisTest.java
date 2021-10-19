package com.atguigu.mybatis.test;

import com.atguigu.mybatis.bean.Department;
import com.atguigu.mybatis.bean.Employee;
import com.atguigu.mybatis.dao.*;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author biao
 * @create -${Year}-10-07-22:42
 */
public class MyBatisTest {
    public SqlSessionFactory getSqlSessionFactory() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }
        /**
         * 1、根据xml配置文件（全局配置文件即mybatis-config.xml）创建一个SqlSessionFactory对象 有数据源一些运行环境信息
         * 2、sql映射文件；配置了每一个sql，以及sql的封装规则等。
         * 3、将sql映射文件注册在全局配置文件中
         * 4、写代码：
         * 		1）、根据全局配置文件得到SqlSessionFactory；
         * 		2）、使用sqlSession工厂，获取到sqlSession对象使用他来执行增删改查
         * 			一个sqlSession就是代表和数据库的一次会话，用完关闭
         * 		3）、使用sql的唯一标志来告诉MyBatis执行哪个sql。sql都是保存在sql映射文件中的。
         *
         * @throws IOException
         */
        @Test
        public void test() throws Exception {
        SqlSession openSession = getSqlSessionFactory().openSession();
            try {
            Employee employee = openSession.selectOne("getEmpById", 1);
            System.out.println(employee);
        }finally {
            openSession.close();
        }
    }

    @Test
    public void test01() throws Exception {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper employeeMapper = openSession.getMapper(EmployeeMapper.class);
            System.out.println(employeeMapper.getClass());//class com.sun.proxy.$Proxy4
            Employee employee = employeeMapper.getEmpById(2,"sam");
            System.out.println(employee);
        }finally {
            openSession.close();
        }
    }
    @Test
    public void test2() throws Exception {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapperAnnotation mapper = openSession.getMapper(EmployeeMapperAnnotation.class);
            Employee employee = mapper.getEmpById(2);
            System.out.println(employee);
        }finally {
            openSession.close();
        }
    }
    @Test
    public void test3() throws Exception {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        //选择了一个不会自动提交的会话
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
            Employee employee = new Employee(null, "赵六", "12334@qq.com", "男",null);
            mapper.addEmp(employee);
            System.out.println(employee.getId());

//            mapper.updateEmp(new Employee(2,"sam","12345678@qq.com",null));
//            boolean b = mapper.deleteEmpById(1);
//            System.out.println(b);
            openSession.commit();
        }finally{
            openSession.close();
        }
    }

    @Test
    public void test4() throws Exception {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
//            Map<String,Object> map = new HashMap<>();
//            map.put("id",2);
//            map.put("lastName","sam");
//            Employee employee = mapper.getEmpByMap(map);
            List<Employee> employeeList = mapper.getEmpByLastNameLike("赵六");
            for (Employee employee : employeeList)
            System.out.println(employee);
            Map<String, Object> map = mapper.getEmpByIdReturnMap(2);
            System.out.println(map);

        }finally {
            openSession.close();
        }
    }
    @Test
    public void test5() throws Exception {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = openSession.getMapper(EmployeeMapper.class);
            Map<Integer, Employee> map = mapper.getEmpByLastName("赵六");
            System.out.println(map);
        }finally {
            openSession.close();
        }
    }
    @Test
    public void test6() throws Exception {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapperPlus mapper = openSession.getMapper(EmployeeMapperPlus.class);
//            Employee map = mapper.getEmpById(2);
//            System.out.println(map);
            Employee employee = mapper.getEmpAndDept(2);
            System.out.println(employee);
            System.out.println(employee.getDept());
        }finally {
            openSession.close();
        }
    }
    @Test
    public void test7() throws Exception {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
//            DepartmentMapper mapper = openSession.getMapper(DepartmentMapper.class);
//            Department department = mapper.getDeptById(1);
//            System.out.println(department);
            EmployeeMapperPlus mapper = openSession.getMapper(EmployeeMapperPlus.class);
            Employee employee = mapper.getEmpByIdStep(2);
            System.out.println(employee);
            //System.out.println(employee.getDept());
        }finally {
            openSession.close();
        }
    }
    @Test
    public void test8() throws Exception {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            DepartmentMapper mapper = openSession.getMapper(DepartmentMapper.class);
//            Department department = mapper.getDeptByIdPlus(2);
//            System.out.println(department);
//            System.out.println(department.getEmps());
            Department department = mapper.getDeptByIdStep(2);
            System.out.println(department.getDepartmentName());
            //System.out.println(department.getEmps());
        }finally {
            openSession.close();
        }
    }

    @Test
    public void testDynamicSql() throws Exception {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();
        try{
            EmployeeMapperDynamicSQL mapper = openSession.getMapper(EmployeeMapperDynamicSQL.class);
           // Employee employee = new Employee(null,"%赵%","12334@qq.com",null);
//            List<Employee> employees = mapper.getEmpsByConditionIf(employee);
            //List<Employee> employees = mapper.getEmpsByConditionTrim(employee);
//            for (Employee emp:employees) {
//                System.out.println(emp);
//            }
            Employee employee = new Employee(3, "李四", "777@qq.com", "1");
            mapper.updateEmp(employee);
            openSession.commit();
        }finally {
            openSession.close();
        }
    }

    @Test
    public void foreachInsert() throws Exception {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession openSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapperDynamicSQL mapper = openSession.getMapper(EmployeeMapperDynamicSQL.class);
            ArrayList<Employee> emps = new ArrayList<>();
            emps.add(new Employee(null,"阿狸","2345@qq.com","0",new Department(2)));
            emps.add(new Employee(null,"阿强","2345@qq.com","1",new Department(2)));
            mapper.getEmpByConditionForeachInsert(emps);
            System.out.println("push git");
            System.out.println("你好啊，少年");
            System.out.println("我很好，谢谢关心");
            openSession.commit();
        }finally {
            openSession.close();
        }
    }

}
