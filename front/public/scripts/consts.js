// ODOR Program Global instance
class ODOR {

    constructor() {
        this.odorLoginUser = {};
        this.odorMessageList = [];
        this.odorCodeList = [];
    }00

    set odorLoginUser(user) {
        this.odorLoginUser = SCRIPT_VALIDATOR.nullCheck(user?.userId) ? user : {};
    }

    get odorLoginUser() {
        return this.odorLoginUser;
    }

    set odorMessageList(list) {
        this.odorMessageList = SCRIPT_VALIDATOR.nullCheck(list) ? list : [];
    }

    get odorMessageList() {
        return this.odorMessageList;
    }

    set odorCodeList(list) {
        this.odorCodeList = SCRIPT_VALIDATOR.nullCheck(list) ? list : [];
    }

    get odorCodeList() {
        return this.odorCodeList;
    }

}




const MEMBERSHIP_PAGES = [
    'MyPage',
    'Login',
    'Join',
    'IdPwFinder'
];

// 임시 vuex 저장소
const TMP_SESSION = {
    setLognUser(user = {}) {
        ODOR.odorLoginUser = user;
    },
    emptyUser() {
        if ('odorLoginUser' in ODOR) {
            delete ODOR.odorLoginUser;
        }
    },
    getId() {
        return ODOR.odorLoginUser?.userId;
    },
    getLoginUser() {
        if (!SCRIPT_VALIDATOR.nullCheck(this.getId())) {
            return {};
        }
        return ODOR.odorLoginUser;
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
        ODOR.odorMessageList = newList.filter(v => v.messageId && v.message);
    },
    getMessageList() {
        return ODOR.odorMessageList || [];
    },
    getMessage(messageId = '') {
        messageId = messageId.toString();
        return ODOR.odorMessageList.find(v => v.messageId === messageId)?.message || '';
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
        ODOR.odorCodeList = newList.filter(v => v.codeGroupId && v.codeId && v.codeName);
    },
    getCodeListAll() {
        return ODOR.odorCodeList || [];
    },
    getCodeList(codeGroupId) {
        return ODOR.odorCodeList.filter(v => v.codeGroupId === codeGroupId);
    },
    getCodeName(codeGroupId, codeId = '') {
        codeId = codeId.toString();
        return ODOR.odorCodeList
            .find(
                v =>
                    v.codeGroupId === codeGroupId &&
                    v.codeId === codeId
            )?.codeName;
    }
}