package com.linlibang.pay.module.consumer.dao;

import com.linlibang.pay.module.consumer.entity.ConsumerPo;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface ConsumerDao extends Mapper<ConsumerPo> {
}
