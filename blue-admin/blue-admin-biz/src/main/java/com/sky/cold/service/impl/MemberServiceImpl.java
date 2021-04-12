package com.sky.cold.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.cold.dao.MemberDao;
import com.sky.cold.entity.Member;
import com.sky.cold.service.MemberService;
import org.springframework.stereotype.Service;


@Service("memberService")
public class MemberServiceImpl extends ServiceImpl<MemberDao, Member> implements MemberService {



}