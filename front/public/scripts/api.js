/*global */
// eslint-disable-next-line
const API = class {

    static CommonController = {
        setSystemLocale: 'rest/common/setSystemLocale'
    };

    static SessionController = {
        sessionCheck: 'rest/sessionCheck',
        validateLogin: 'rest/validateLogin',
        logout: 'rest/logout'
    };

    static ForumMgmtController = {
        getForumList: 'api/forum/getForumList',
        getSystemList: 'api/forum/getSystemList'
    }

};