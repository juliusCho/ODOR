const MEMBERSHIP_PAGES = [
    'MyPage',
    'Login',
    'Join',
    'IdPwFinder'
];

// 임시 vuex 저장소
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
                return window.loginUser?.userId;
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

// Multilanguage Message
const MESSAGE = {
    setMessageList(list = []) {
        window.messageList = COMMON_UTIL
            .range(list.length)
            .map(i =>
                `{messageId: list[${i}]?.messageId,` +
                ` message: list[${i}]?.message}`
            );
    },
    getMessageList() {
        return window.messageList || [];
    },
    getMessage(messageId) {
        return window.messageList.find(v => v.messageId === messageId);
    }
}

// Common Code
const CODE = {
    setCodeList(list = []) {
        window.codeList = COMMON_UTIL
            .range(list.length)
            .map(i =>
                `{codeGroupId: list[${i}]?.codeGroupId,` +
                `{codeId: list[${i}]?.codeId,` +
                ` codeNm: list[${i}]?.codeNm}`
            );
    },
    getCodeListAll() {
        return window.codeList || [];
    },
    getCodeList(codeGroupId) {
        return window.codeList.filter(v => v.codeGroupId === codeGroupId);
    },
    getCode(codeGroupId, codeId) {
        return window.codeList
            .find(
                v => v.codeGroupId === codeGroupId &&
                    v.codeId === codeId
            );
    }
}