/**
 * Created by AlexXie on 2015/8/28.
 */

angular.module('app').constant("CRMROUTER", {
    crm: {
        abstract: true,
        state: 'crm',
        url: 'app'
    },
    crm_index: {
        state: 'index',
        url: 'crm/index'
    },
    crm_customer_list: {
        state: 'customer_list',
        controllername: 'CRM_Customer_List_Controller',
        url: 'crm/customer/customer.client.list'
    },
    crm_customer_edit: {
        state: 'customer_edit',
        params: ["uid"],
        controllername: 'CRM_Customer_Edit_Controller',
        url: 'crm/customer/customer.client.edit'
    }
});
