package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.result.PageResult;

public interface DishService {

    void saveWithFlavors(DishDTO dishDTO);
}
