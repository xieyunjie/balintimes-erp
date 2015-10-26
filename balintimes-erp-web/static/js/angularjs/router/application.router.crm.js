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

    crm_customer_detail: {
        state: 'crm.saler.customer_detail',
        url: '/customer/detail',
        controllername: 'CRM_Customer_Detail_Controller',
        filepath: 'crm/saler/customer/customer.client.readonly',
        params: ["customeruid", "followupuid", "isreg"],
        script: [
            "CRM_Customer_Service",
            "CRM_BaseData_Service",
            "attachment_directive",
            "customerdetail_directive",
            "remarks_directive",
            "contracts_directive"
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

    crm_remarks_customerlist: {
        state: 'crm.saler.remarks_customerlist',
        url: '/remarks/customerlist',
        controllername: 'CRM_Customer_Remarks_CustomerList_Controller',
        filepath: 'crm/saler/remarks/customer.remarks.cuslist',
        params: ["customeruid", "followupuid", "isreg"],
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
        params: ["uid", "isreg", "isreadonly", "ic"],
        script: [
            "CRM_Follow_Remarks_Service",
            "CRM_BaseData_Service",
            "CRM_Customer_Service",
            "CRM_base_Service"
        ]
    },

    crm_contract_create_edit: {
        state: 'crm.saler.contract_create_edit',
        url: '/contract/create',
        controllername: 'CRM_Contract_Create_Controller',
        filepath: 'crm/saler/contract/customer.contract.create',
        params: ["customeruid", "followupuid", "isreg"],
        script: [
            "CRM_Customer_Service",
            "CRM_Contract_Service",
            "angular-file-upload"
        ]
    },

    crm_contract_list: {
        state: 'crm.saler.contract_list',
        url: '/contract/list',
        controllername: 'CRM_Contract_List_Controller',
        filepath: 'crm/saler/contract/customer.contract.list',
        params: ["customeruid", "followupuid", "isreg"],
        script: [
            "CRM_Contract_Service"
        ]
    },

    crm_contract_edit_edit: {
        state: 'crm.saler.contract_edit_edit',
        url: '/contract/edit',
        controllername: 'CRM_Contract_Edit_Controller',
        filepath: 'crm/saler/contract/customer.contract.edit',
        params: ["uid", "isreg", "isreadonly", "customeruid", "followupuid"],
        script: [
            "CRM_Customer_Service",
            "CRM_Contract_Service",
            "angular-file-upload"
        ]
    },

    crm_attachment_create_edit: {
        state: 'crm.saler.attachment_create_edit',
        url: '/attachment/create',
        controllername: 'CRM_Attachment_Create_Controller',
        filepath: 'crm/saler/attachment/customer.attachment.create',
        params: ["customeruid", "followupuid", "isreg", "ic"],//ic 是否从客户界面进入1:是，0：列表
        script: [
            "CRM_Customer_Service",
            "CRM_Attachment_Service",
            "angular-file-upload"
        ]
    },

    crm_attachment_list: {
        state: 'crm.saler.attachment_list',
        url: '/attachment/list',
        controllername: 'CRM_Attachment_List_Controller',
        filepath: 'crm/saler/attachment/customer.attachment.list',
        params: ["customeruid", "followupuid", "isreg"],
        script: [
            "CRM_Attachment_Service"
        ]
    }

});
