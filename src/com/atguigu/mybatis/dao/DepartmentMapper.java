package com.atguigu.mybatis.dao;

import com.atguigu.mybatis.bean.Department;

/**
 * @author biao
 * @create -${Year}-10-15-20:36
 */
public interface DepartmentMapper {
    public Department getDeptById(Integer id);
    public Department getDeptByIdPlus(Integer id);
    public Department getDeptByIdStep(Integer id);

}
