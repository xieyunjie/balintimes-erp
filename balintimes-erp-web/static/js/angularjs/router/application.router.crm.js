/**
 * Created by AlexXie on 2015/8/28.
 */

angular.module('app').constant("CRMROUTER", {
    crm: {
        abstract: true,
        state: 'crm',
        url: "/crm",
        filepath: "app"
    },
    crm_index: {
        state: 'crm.index',
        filepath: 'crm/index',
        url: '/index'
    },

    //crm_base:{
    //    state:'crm.base',
    //    filepath: 'crm/base/base',
    //    url: '/base'
    //},
    //crm_base_service:{
    //    state:'crm.base.service',
    //    filepath:'/services/services',
    //    url:'/base/services'
    //},
    crm_saler: {
        state: 'crm.saler',
        filepath: 'crm/saler/saler',
        url: '/saler'
    },
    crm_customer_list: {
        state: 'crm.saler.customer_list',
        url: '/customer/list',
        controllername: 'CRM_Customer_List_Controller',
        filepath: 'crm/saler/customer/customer.client.list',

        script: [
            "CRM_Customer_Service",
            "CRM_BaseData_Service"
        ]
    },
    crm_customer_edit: {
        state: 'crm.saler.customer_edit',
        url: '/customer/edit',
        params: ["uid", "followUid", "isReg"],
        controllername: 'CRM_Customer_Edit_Controller',
        filepath: 'crm/saler/customer/customer.client.edit',

        script: [
            "CRM_Customer_Service",
            "CRM_BaseData_Service",
            "CRM_base_Service"
        ]
    },

    crm_customer_create_edit: {
        state: 'crm.saler.customer_create_edit',
        url: '/customer/createedit',
        controllername: 'CRM_Customer_Create_Edit_Controller',
        filepath: 'crm/saler/customer/customer.client.createedit',

        script: [
            "CRM_Customer_Service",
            "CRM_BaseData_Service",
            "CRM_base_Service"
        ]
    },

    crm_remarks_list: {
        state: 'crm.saler.remarks_list',
        url: '/remarks/list',
        controllername: 'CRM_Customer_Remarks_List_Controller',
        filepath: 'crm/saler/remarks/customer.remarks.list',

        script: [
            "CRM_Follow_Remarks_Service",
            "CRM_BaseData_Service",
            "CRM_base_Service"
        ]
    },

    crm_remarks_edit: {
        state: 'crm.saler.remarks_edit',
        url: '/remarks/edit',
        controllername: 'CRM_Customer_Remarks_Edit_Controller',
        filepath: 'crm/saler/remarks/customer.remarks.edit',
        params: ["customeruid", "followuid", "isreg"],
        script: [
            "CRM_Follow_Remarks_Service",
            "CRM_BaseData_Service",
            "CRM_Customer_Service",
            "CRM_base_Service"
        ]
    },

    crm_remarks_cu_edit: {
        state: 'crm.saler.remarks_cu_edit',
        url: '/remarks/cuedit',
        controllername: 'CRM_Customer_Remarks_CU_Edit_Controller',
        filepath: 'crm/saler/remarks/customer.remarks.cuedit',
        params: ["uid","isreg", "isreadonly"],
        script: [
            "CRM_Follow_Remarks_Service",
            "CRM_BaseData_Service",
            "CRM_Customer_Service",
            "CRM_base_Service"
        ]
    }
});
