package com.sky.cold.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.cold.dao.MemberTagDao;
import com.sky.cold.entity.MemberTag;
import com.sky.cold.service.MemberTagService;
import org.springframework.stereotype.Service;


@Service("memberTagService")
public class MemberTagServiceImpl extends ServiceImpl<MemberTagDao, MemberTag> implements MemberTagService {



}