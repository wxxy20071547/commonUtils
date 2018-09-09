package com.kevin.common;

import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

public class MatchStrategy {
    // 运营商 ， SDK版本，省份
    private int[][] channelInitArray = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}, {1, 0, 0}, {0, 1, 1}, {0, 1, 0}, {0, 0, 1},
            {0, 0, 0}};

    private List<String> annelInitInfoList(int provider, int sdkIntVersion, int provinceId) {

        Map<String, List<String>> channelInitInfoMap = Maps.newHashMap();//--TODO
        for (int[] value : channelInitArray) {
            String providerKey = String.valueOf(provider);
            String sdkIntVersionKey = String.valueOf(sdkIntVersion);
            String provinceIdKey = String.valueOf(provinceId);

            if (value[0] == 0) {
                providerKey = "-1";
            }
            if (value[1] == 0) {
                sdkIntVersionKey = "-1";
                ;
            }
            if (value[2] == 0) {
                provinceIdKey = "-1";
            }
            String key = getChannelInitInfoKey(providerKey, sdkIntVersionKey, provinceIdKey);
            List<String> list = channelInitInfoMap.get(key);
            if (list != null) {
                return list;
            }
        }
        return null;
    }

    private String getChannelInitInfoKey(String providerKey, String sdkIntVersionKey, String provinceIdKey) {
        return providerKey + "_" + sdkIntVersionKey + "_" + provinceIdKey;
    }
}
