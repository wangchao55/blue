package com.sky.cold.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sky.cold.dao.MemberReceiveAddressDao;
import com.sky.cold.entity.MemberReceiveAddress;
import com.sky.cold.service.MemberReceiveAddressService;


@Service("memberReceiveAddressService")
public class MemberReceiveAddressServiceImpl extends ServiceImpl<MemberReceiveAddressDao, MemberReceiveAddress> implements MemberReceiveAddressService {



}