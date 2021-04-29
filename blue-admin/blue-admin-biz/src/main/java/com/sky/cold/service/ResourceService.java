package com.sky.cold.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sky.cold.entity.Resource;
import org.springframework.http.HttpStatus;

import java.util.List;


/**
 * 后台资源表
 *
 * @author wangchao
 * @date 2021-04-01 09:53:25
 */
public interface ResourceService extends IService<Resource> {

    IPage<Resource> getResourceList(Integer pageNum, Integer pageSize, Long categoryId, String resourceName, String resourceUrl);

    Resource getResourceInfo(Long id);

    Boolean saveResourceInfo(Resource resource);

    Boolean updateResourceInfo(Resource resource);

    Boolean deleteResourceInfo(Long id);

    List<Resource> getResourceListAll();

}

