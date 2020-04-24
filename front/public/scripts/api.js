/*global */
// eslint-disable-next-line
const API = class {

    static SessionController = {
        sessionCheck: 'rest/sessionCheck',
        validateLogin: 'rest/validateLogin',
        logout: 'rest/logout'
    };

    static UserMgmtController = {
        getUserList: 'api/getUserList'
    };
};


const MEMBERSHIP_PAGES = [
    'MyPage',
    'Login',
    'Join',
    'IdPwFinder'
];


const TMP_SESSION = {
    setLognUser(user = {}) {
        if ('userId' in user) {
            window.loginUser = !SCRIPT_VALIDATOR.nullCheck(user.userId)
                            ? {} : user;
        }
    },
    emptyUser() {
        if ('loginUser' in window) {
            delete window.loginUser;
        }
    },
    getId() {
        if ('loginUser' in window) {
            if ('userId' in window.loginUser) {
                return window.loginUser.userId;
            }
            return '';
        }
        return '';
    },
    getLoginUser() {
        if (!SCRIPT_VALIDATOR.nullCheck(this.getId())) {
            return {};
        }
        return window.loginUser;
    }
};