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
        getSystemList: 'odor/forum/getSystemList/system'
    };

    static BrandMgmtController = {
        getBrandList: 'odor/brand/getBrandList/system'
    };

    static CodeMgmtController = {
        getCodeGroupList: 'odor/code/getCodeGroupList/system',
        checkDuplication: 'odor/code/checkDuplication/system',
        insertCodeGroup: 'odor/code/insertCodeGroup/system',
        updateCodeGroup: 'odor/code/updateCodeGroup/system',
        deleteCodeGroup: 'odor/code/deleteCodeGroup/system',
        getCodeListSystem: 'odor/code/getCodeListSystem/system',
        checkDetailDuplication: 'odor/code/checkDetailDuplication/system',
        insertCode: 'odor/code/insertCode/system',
        updateCode: 'odor/code/updateCode/system',
        deleteCode: 'odor/code/deleteCode/system'
    };

    static MessageMgmtController = {
        getMessageList: 'odor/msg/getMessageList/system',
        insertMessage: 'odor/msg/insertMessage/system',
        getMessageListSystem: 'odor/msg/getMessageListSystem/system',
        checkDuplication: 'odor/msg/checkDuplication/system',
        updateMessage: 'odor/msg/updateMessage/system',
        deleteMessageGroup: 'odor/msg/deleteMessageGroup/system'
    };

    static CountryMgmtController = {
        getCountryListAll: 'odor/country/getCountryListAll/system',
        getCountryList: 'odor/country/getCountryList/system',
        checkDuplication: 'odor/country/checkDuplication/system',
        insertCountry: 'odor/country/insertCountry/system',
        updateCountry: 'odor/country/updateCountry/system',
        deleteCountry: 'odor/country/deleteCountry/system'
    };

};