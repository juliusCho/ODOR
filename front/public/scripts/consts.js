const MEMBERSHIP_PAGES = [
    'MyPage',
    'Login',
    'Join',
    'IdPwFinder'
];

// 임시 vuex 저장소
const TMP_SESSION = {
    setLognUser(user = {}) {
        window.odorLoginUser = SCRIPT_VALIDATOR.nullCheck(user?.userId) ? user : {};
    },
    emptyUser() {
        if ('odorLoginUser' in window) {
            delete window.odorLoginUser;
        }
    },
    getId() {
        return window?.odorLoginUser?.userId;
    },
    getLoginUser() {
        if (!SCRIPT_VALIDATOR.nullCheck(this.getId())) {
            return {};
        }
        return window.odorLoginUser;
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
        window.odorMessageList = newList.filter(v => v.messageId && v.message);
    },
    getMessageList() {
        return window.odorMessageList || [];
    },
    getMessage(messageId = '') {
        messageId = messageId.toString();
        return window.odorMessageList.find(v => v.messageId === messageId)?.message || '';
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
        window.odorCodeList = newList.filter(v => v.codeGroupId && v.codeId && v.codeName);
    },
    getCodeListAll() {
        return window.odorCodeList || [];
    },
    getCodeList(codeGroupId) {
        return window.odorCodeList.filter(v => v.codeGroupId === codeGroupId);
    },
    getCodeName(codeGroupId, codeId = '') {
        codeId = codeId.toString();
        return window.odorCodeList
            .find(
                v => v.codeGroupId === codeGroupId &&
                    v.codeId === codeId
            )?.codeName;
    }
}