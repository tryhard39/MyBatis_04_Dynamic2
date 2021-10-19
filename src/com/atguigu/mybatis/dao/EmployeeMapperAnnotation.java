package com.atguigu.mybatis.dao;

import com.atguigu.mybatis.bean.Employee;
import org.apache.ibatis.annotations.Select;

/**
 * @author biao
 * @create -${Year}-10-11-23:38
 */
public interface EmployeeMapperAnnotation {
    @Select("select * from tbl_employee where id = #{id}")
    public Employee getEmpById(Integer id);
}
