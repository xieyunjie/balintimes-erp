/**
 * Created by AlexXie on 2015/8/28.
 */
//此处有个小问题，由于bmmrouter和crmrouter的abstract state不相同，相互跳转时，界面会刷屏。好处是这个两个模块可以使用不同风格界面
angular.module('app').constant("BMMROUTER", {
    bmms: {
        abstract: true,
        state: 'bmms',
        url: "/bmms",
        filepath: "app"
    },
    bmms_index: {
        state: 'bmms.index',
        filepath: 'bmms/index',
        url: '/index'
    },
    bmms_base: {
        state: 'bmms.base',
        filepath: 'bmms/base/base',
        url: '/base'
    },
    bmms_base_line_list: {
        state: 'bmms.base.line_list',
        url: '/line/list',
        controllername: 'BMMS_Line_List_Controller',
        filepath: 'bmms/base/line/line.client.list',
        script: ["BMMS_Line_Service"]
    },
    bmms_base_line_edit: {
        state: 'bmms.base.line_edit',
        url: '/line/edit',
        params: ["uid"],
        controllername: 'BMMS_Line_Edit_Controller',
        filepath: 'bmms/base/line/line.client.edit',
        script: ["BMMS_Line_Service", "CRM_BaseData_Service"]
    },
    bmms_base_bustype_list: {
        state: 'bmms.base.bustype_list',
        url: '/bustype/list',
        controllername: 'BMMS_Base_BusType_List_Controller',
        filepath: 'bmms/base/bustype/bustype.client.list',
        script: ["BMMS_Line_Service"]
    },

    bmms_media: {
        state: 'bmms.media',
        url: '/media',
        filepath: 'bmms/media/media'
    },
    bmms_media_bus_list: {
        state: 'bmms.media.bus_list',
        url: '/bus/list',
        controllername: 'BMMS_Media_Bus_List_Controller',
        filepath: 'bmms/media/bus/bus.client.list'
    }

});