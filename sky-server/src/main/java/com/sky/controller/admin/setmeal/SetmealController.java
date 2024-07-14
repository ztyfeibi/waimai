package com.sky.controller.admin.setmeal;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.SetmealService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/admin/setmeal")
@RestController
@ApiOperation("套餐接口")
@Slf4j
public class SetmealController {

    @Autowired
    SetmealService setMealService;

    @GetMapping("/page")
    @ApiOperation("分页查询")
    public Result<PageResult> page(SetmealPageQueryDTO setmealPageQueryDTO) {
        PageResult pageResult = setMealService.page(setmealPageQueryDTO);

        return Result.success(pageResult);
    }

    @PostMapping
    @ApiOperation("新增套餐")
    public Result save(@RequestBody SetmealDTO setmealDTO){
        setMealService.insert(setmealDTO);
        return Result.success();
    }

    @DeleteMapping
    @ApiOperation("删除套餐")
    public Result delete(@RequestParam List<Long> ids){
        setMealService.deleteBatches(ids);
        return Result.success();
    }
}
