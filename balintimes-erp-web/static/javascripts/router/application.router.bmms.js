/**
 * Created by AlexXie on 2015/8/28.
 */
//此处有个小问题，由于bmmrouter和crmrouter的abstract state不相同，相互跳转时，界面会刷屏。好处是这个两个模块可以使用不同风格界面
angular.module('app').constant("BMMROUTER", {
    bmms: {
        abstract: true,
        state: 'bmms',
        url: 'app'//可以与crm不一样
    },
    bmms_index: {
        state: 'index',
        url: 'bmms/index'
    },
    bmms_line_list: {
        state: 'line_list',
        controllername: 'BMMS_Line_List_Controller',
        url: 'bmms/line/line.client.list',

        script: ["BMM_Line_Service"]
    },
    bmms_line_edit: {
        state: 'line_edit',
        params: ["uid"],
        controllername: 'BMMS_Line_Edit_Controller',
        url: 'bmms/line/line.client.edit',

        script: ["BMM_Line_Service"]
    }
});