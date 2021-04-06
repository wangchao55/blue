package com.sky.cold.admin.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.cold.admin.dao.MemberTagDao;
import com.sky.cold.admin.entity.MemberTag;
import com.sky.cold.admin.service.MemberTagService;


@Service("memberTagService")
public class MemberTagServiceImpl extends ServiceImpl<MemberTagDao, MemberTag> implements MemberTagService {



}