package com.sky.service;

import com.sky.dto.*;
import com.sky.entity.Employee;
import com.sky.result.PageResult;
import com.sky.vo.DishVO;

import java.util.List;

public interface DishService {

    void saveWithFlavors(DishDTO dishDTO);

    PageResult page(DishPageQueryDTO pageQueryDTO);

    void delete(List<Long> ids);

    DishVO getById(Long id);

    void updateWithFlavors(DishDTO dishDTO);
}
