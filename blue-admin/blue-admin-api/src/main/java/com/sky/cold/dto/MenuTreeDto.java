package com.sky.cold.dto;

import com.sky.cold.entity.Menu;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangchao
 * @date 2021-04-25 2:48 下午
 */
@Data
public class MenuTreeDto {
    private Long id;
    private String name; // 菜单名称
    private Long parentId; // 父id
    private Integer level; // 层级
    private List<MenuTreeDto> children = new ArrayList<>(); // 子节点
}
