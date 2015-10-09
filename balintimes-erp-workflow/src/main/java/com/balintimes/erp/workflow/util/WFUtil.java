package com.balintimes.erp.workflow.util;

import com.balintimes.erp.workflow.model.ERPType;

/**
 * Created by AlexXie on 2015/10/9.
 */
public class WFUtil {

    private static String _BizKey_Separator = ":";

    public static String makeBizKey(ERPType erpType, String bizKey) {
        return erpType + _BizKey_Separator + bizKey;
    }


    public static ERPType splitERPType(String bizKey) {

        String t = bizKey.split(_BizKey_Separator)[0];
        switch (t) {
            case "CRM":
                return ERPType.CRM;
            case "Quote":
                return ERPType.Quote;
        }
        return ERPType.Undefine;
    }

    public static String splitBizKey(String bizKey) {

        return bizKey.split(_BizKey_Separator)[1];
    }

}
