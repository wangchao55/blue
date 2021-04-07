package com.sky.cold.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.cold.dao.MemberTagDao;
import com.sky.cold.entity.MemberTag;
import com.sky.cold.service.MemberTagService;


@Service("memberTagService")
public class MemberTagServiceImpl extends ServiceImpl<MemberTagDao, MemberTag> implements MemberTagService {



}