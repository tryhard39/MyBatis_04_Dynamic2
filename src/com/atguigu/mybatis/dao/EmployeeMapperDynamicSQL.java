package com.atguigu.mybatis.dao;

import com.atguigu.mybatis.bean.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author biao
 * @create -${Year}-10-17-9:51
 */
public interface EmployeeMapperDynamicSQL {
    public List<Employee> getEmpsByConditionIf(Employee employee);
    public List<Employee> getEmpsByConditionTrim(Employee employee);
    public void updateEmp(Employee employee);
    public List<Employee> getEmpByConditionForeach(@Param("ids") List<Integer>ids);
    public void  getEmpByConditionForeachInsert(@Param("em") List<Employee> emps);

}
