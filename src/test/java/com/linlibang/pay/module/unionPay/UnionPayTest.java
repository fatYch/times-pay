package com.linlibang.pay.module.unionPay;

import com.alibaba.fastjson.JSON;
import com.linlibang.pay.utils.SHA256Util;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UnionPayTest {

    @Test
    public void testSha256(){
        System.out.println(SHA256Util.getSHA256StrJava("A"));
        System.out.println(JSON.toJSONString(new String("18836c093c8d293a4423d2973b5e8aa9927ae1f3b2032b442a5f1a691fc3cb4f").getBytes()));
    }

    @Test
    public void testSign(){
        UnionPayUtil.getOpenBodySign("A");
    }
}
