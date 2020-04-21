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
    setId(id) {
        window.id = id;
    },
    getId() {return window.id;}
};