package com.sky.mapper;

import com.sky.entity.SetmealDish;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SetMealDishMapper {

    /**
     * 
     * @param dishIds
     * @return
     */
    List<Long> getSetMealIdByDishIds(List<Long> dishIds);

    /**
     * 插入套餐菜品
     * @param setmealDishes
     */
    void insertSetmealDish(List<SetmealDish> setmealDishes);

    /**
     * 删除套餐对应的菜品
     * @param ids
     */
    void deleteBatches(List<Long> ids);
}
