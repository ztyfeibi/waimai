package com.sky.mapper;

import com.sky.entity.DishFlavor;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DishFlavorMapper {

    /**
     * 批量插入口味
     * @param dishFlavors
     */
    void insertBatch(List<DishFlavor> dishFlavors);

    @Delete("delete from dish_flavor where dish_id = #{dishId};")
    void deleteByDishId(Long dishId);

    /**
     * 批量删除
     * @param ids
     */
    void deleteBatchesByDishId(List<Long> ids);

    /**
     * 获取口味
     * @param dishId
     * @return
     */
    @Select("select * from dish_flavor where dish_id = #{dishId};")
    List<DishFlavor> getByDishId(Long dishId);
}
