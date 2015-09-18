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
    crm_saler:{
        state:'crm.saler',
        filepath: 'crm/saler/saler',
        url: '/saler'
    },
    crm_customer_list: {
        state: 'crm.saler.customer_list',
        url:'/customer/list',
        controllername: 'CRM_Customer_List_Controller',
        filepath: 'crm/saler/customer/customer.client.list',

        script: [
            "CRM_Customer_Service",
            "CRM_BaseData_Service"
        ]
    },
    crm_customer_edit: {
        state: 'crm.saler.customer_edit',
        url:'/customer/edit',
        params: ["uid","isReg"],
        controllername: 'CRM_Customer_Edit_Controller',
        filepath: 'crm/saler/customer/customer.client.edit',

        script: [
            "CRM_Customer_Service",
            "CRM_BaseData_Service"
        ]
    }
});
