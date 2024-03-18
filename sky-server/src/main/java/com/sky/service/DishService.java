package com.sky.service;

import com.sky.dto.*;
import com.sky.entity.Employee;
import com.sky.result.PageResult;

import java.util.List;

public interface DishService {

    void saveWithFlavors(DishDTO dishDTO);

    PageResult page(DishPageQueryDTO pageQueryDTO);

    void delete(List<Long> ids);
}
