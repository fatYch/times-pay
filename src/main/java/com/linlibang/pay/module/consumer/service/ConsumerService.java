package com.linlibang.pay.module.consumer.service;

import com.google.common.collect.Lists;
import com.linlibang.pay.module.consumer.dao.ConsumerDao;
import com.linlibang.pay.module.consumer.entity.ConsumerPo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsumerService {

    private static List<String> sourceList = Lists.newArrayList();

    @Autowired
    private ConsumerDao consumerDao;

    /**
     * 保存消费者
     * @param consumerPo
     */
    public void saveConsumer(ConsumerPo consumerPo){
        if(StringUtils.isBlank(consumerPo.getId())){
            consumerPo.preInsert();
            consumerDao.insert(consumerPo);
        }else {
            consumerPo.preUpdate();
            consumerDao.updateByPrimaryKey(consumerPo);
        }
        refreshSourceList();
    }


    /**
     * 检验来源是否正确
     * @param source
     * @return
     */
    private boolean checkSource(String source){
        if(CollectionUtils.isEmpty(sourceList)){
            synchronized (sourceList){
                if(CollectionUtils.isEmpty(sourceList)){
                    refreshSourceList();
                }
            }
        }
        return sourceList.contains(source);
    }

    /**
     * 刷新来源列表
     */
    private void refreshSourceList(){
        sourceList = Lists.newArrayList();
        ConsumerPo consumerPo = new ConsumerPo();
        consumerPo.setStatus("1");
        List<ConsumerPo> consumerPos = consumerDao.select(consumerPo);
        consumerPos.stream().forEach((consumerPo1)-> sourceList.add(consumerPo1.getSource()));
    }





}
