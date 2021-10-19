package com.atguigu.mybatis.dao;

import com.atguigu.mybatis.bean.Employee;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EmployeeMapper {
	//查询
	public Employee getEmpById(@Param("id") Integer id, @Param("lastName") String lastName);
	//添加，插入
	public long addEmp(Employee employee);
	//更新
	public boolean updateEmp(Employee employee);
	//删除
	public boolean deleteEmpById(Integer id);

	//参数使用map
	public Employee getEmpByMap(Map<String, Object> map);
//	参数使用list
	public List<Employee> getEmpByLastNameLike(String lastName);
	public Map<String,Object> getEmpByIdReturnMap(Integer id);
	@MapKey("id")
	public Map<Integer,Employee> getEmpByLastName(String lastName);


}
