//-*- coding =utf-8 -*-
//@Time : 2023/2/11
//@Author: 邓闽川
//@File  SignUtil.java
//@software:IntelliJ IDEA
package com.yupi.project.common;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.SecureUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;

/**
 * 签名工具类
 * @author 邓闽川
 */
@Slf4j
public class SignUtil {
    /**
     * 判断签名是否正确
     * @param sign 待检查签名
     * @param args 签名生成参数
     * @return Boolean
     */

    private static final String SALT="DAPI";
    private static final Integer LIMIT=10000;
    //防重放优化
    private static final HashSet<Integer> nonceSet;

    private static final HashSet<Integer> randomSet;
    static {
        nonceSet=new HashSet<>();
        randomSet =new HashSet<>();
    }
    public static boolean checkSign(String sign,Integer nonce,String ...args){
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0;i<args.length;i++){
            stringBuilder.append(args[i]);
        }
        //todo:参数不变md5加密后的数不会变，要制造会变化的sign值
        stringBuilder.append(SALT);
        String sign2 = SecureUtil.md5(stringBuilder.toString());
        log.info("sign:{}",sign);
        boolean equals = sign.equals(sign2);
        //签名不匹配
        if(!equals) return false;
        //重复签名无效
        synchronized (nonceSet) {
            if (!nonceSet.add(nonce)) return false;
        }
        return true;

    }
    //获取nonce随机数
    public static synchronized Integer getNonce(){
        if(nonceSet.size()>500|| randomSet.size()>500){
            nonceSet.clear();
            randomSet.clear();
        }
        int nonce = RandomUtil.randomInt(LIMIT);
        //获取检验用随机数,nonceSet防重放，randomPool防止不同用户分到相同随机数
        while(nonceSet.contains(nonce)|| randomSet.contains(nonce)){
            nonce=RandomUtil.randomInt(LIMIT);
        }
        randomSet.add(nonce);
        return nonce;

    }
}
