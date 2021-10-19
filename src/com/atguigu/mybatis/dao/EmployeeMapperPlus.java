package com.atguigu.mybatis.dao;

import com.atguigu.mybatis.bean.Employee;

import java.util.List;
import java.util.Map;

/**
 * @author biao
 * @create -${Year}-10-13-12:28
 */
public interface EmployeeMapperPlus {
    public Employee getEmpById(Integer id);
    public Employee getEmpAndDept(Integer id);
    public Employee getEmpByIdStep(Integer id);
    public List<Employee> getEmpByDeptId(Integer id);
}
