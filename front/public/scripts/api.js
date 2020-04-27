/*global */
// eslint-disable-next-line
const API = class {

    static CommonController = {
        setSystemLocale: 'odor/common/setSystemLocale/rest'
    };

    static SessionController = {
        sessionCheck: 'odor/sessionCheck/api',
        validateLogin: 'odor/validateLogin/api',
        logout: 'odor/logout/rest'
    };

    static ForumMgmtController = {
        getForumList: 'odor/forum/getForumList/rest',
        getSystemList: 'odor/forum/getSystemList/api'
    }

};