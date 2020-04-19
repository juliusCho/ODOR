/*global */
// eslint-disable-next-line
const API = class {

    static SessionController = {
        sessionCheck: 'rest/sessionCheck',
        validateLogin: 'rest/validateLogin'
    };

    static UserMgmtController = {
        getUserList: 'api/getUserList'
    };
};

const TMP_SESSION = class {
    setId(id) {
        window.id = id;
    }
    getId() {return window.id}
};