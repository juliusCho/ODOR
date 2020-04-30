/*global */
// eslint-disable-next-line
const API = class {

    static CommonController = {
        setSystemLocale: 'odor/common/setSystemLocale/rest',
        getMessageList: 'odor/common/getMessageList/rest',
        getCodeList: 'odor/common/getCodeList/rest'
    };

    static SessionController = {
        sessionCheck: 'odor/sessionCheck/rest',
        validateLogin: 'odor/validateLogin/rest',
        logout: 'odor/logout/rest'
    };

    static ForumMgmtController = {
        getForumList: 'odor/forum/getForumList/rest',
        getSystemList: 'odor/forum/getSystemList/api'
    }

};