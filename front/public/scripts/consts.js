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
        let newList = [];
        for (let i in list) {
            newList.push({
                messageId: list[i].messageId,
                message: list[i].message
            });
        }
        window.messageList = newList.filter(v => v.messageId && v.message);
    },
    getMessageList() {
        return window.messageList || [];
    },
    getMessage(messageId) {
        return window.messageList.find(v => v.messageId === messageId)?.message || '';
    }
}

// Common Code
const CODE = {
    setCodeList(list = []) {
        let newList = [];
        for (let i in list) {
            newList.push({
                codeGroupId: list[i].codeGroupId,
                codeId: list[i].codeId,
                codeName: list[i].codeMessage || list[i].codeName
            });
        }
        window.codeList = newList.filter(v => v.codeGroupId && v.codeId && v.codeName);
    },
    getCodeListAll() {
        return window.codeList || [];
    },
    getCodeList(codeGroupId) {
        return window.codeList.filter(v => v.codeGroupId === codeGroupId);
    },
    getCodeName(codeGroupId, codeId) {
        return window.codeList
            .find(
                v => v.codeGroupId === codeGroupId &&
                    v.codeId === codeId
            )?.codeName;
    }
}