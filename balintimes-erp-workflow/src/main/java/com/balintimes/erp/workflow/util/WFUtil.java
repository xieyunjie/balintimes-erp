package com.balintimes.erp.workflow.util;

/**
 * Created by AlexXie on 2015/10/9.
 */
public class WFUtil {

    private static String _BizKey_Separator = ":";

    public static String makeComplexKey(String erpType, String bizKey) {
        return erpType + _BizKey_Separator + bizKey;
    }


    public static String splitERPType(String complexKey) {
        return complexKey.substring(0, complexKey.lastIndexOf(_BizKey_Separator));
    }

    public static String splitBizKey(String complexKey) {
        return complexKey.substring(complexKey.lastIndexOf(_BizKey_Separator) + 1, complexKey.length());
    }
}
