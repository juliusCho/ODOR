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
        getSystemList: 'odor/forum/getSystemList/system',
        getForumListAll: 'odor/forum/getForumListAll/system',
        getForumListSystem: 'odor/forum/getForumListSystem/system',
        checkDuplication: 'odor/forum/checkDuplication/system',
        deleteForum: 'odor/forum/deleteForum/system',
        updateForum: 'odor/forum/updateForum/system',
        insertForum: 'odor/forum/insertForum/system'
    };

    static BrandMgmtController = {
        getBrandList: 'odor/brand/getBrandList/system',
        getBrandListAll: 'odor/brand/getBrandListAll/system',
        checkDuplication: 'odor/brand/checkDuplication/system',
        insertBrand: 'odor/brand/insertBrand/system',
        updateBrand: 'odor/brand/updateBrand/system',
        deleteBrand: 'odor/brand/deleteBrand/system',
        getMappedCategoryList: 'odor/brand/getMappedCategoryList/system',
        deleteMapping: 'odor/brand/deleteMapping/system',
        saveMapping: 'odor/brand/saveMapping/system'
    };

    static CodeMgmtController = {
        getCodeGroupList: 'odor/code/getCodeGroupList/system',
        getCodeGroupListAll: 'odor/code/getCodeGroupListAll/system',
        checkDuplication: 'odor/code/checkDuplication/system',
        insertCodeGroup: 'odor/code/insertCodeGroup/system',
        updateCodeGroup: 'odor/code/updateCodeGroup/system',
        deleteCodeGroup: 'odor/code/deleteCodeGroup/system',
        getCodeListSystem: 'odor/code/getCodeListSystem/system',
        checkDetailDuplication: 'odor/code/checkDetailDuplication/system',
        insertCode: 'odor/code/insertCode/system',
        updateCode: 'odor/code/updateCode/system',
        deleteCode: 'odor/code/deleteCode/system',
        getForumCodeList: 'odor/code/getForumCodeList/system'
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

    static CategoryMgmtController = {
        getCategoryListAll: 'odor/category/getCategoryListAll/system',
        getCategoryList: 'odor/category/getCategoryList/system',
        checkDuplication: 'odor/category/checkDuplication/system',
        insertCategory: 'odor/category/insertCategory/system',
        updateCategory: 'odor/category/updateCategory/system',
        deleteCategory: 'odor/category/deleteCategory/system'
    };

    static MembershipMgmtController = {
        getMembershipListAll: 'odor/membership/getMembershipListAll/system',
        getMembershipList: 'odor/membership/getMembershipList/system',
        checkDuplication: 'odor/membership/checkDuplication/system',
        deleteMembership: 'odor/membership/deleteMembership/system',
        updateMembership: 'odor/membership/updateMembership/system',
        insertMembership: 'odor/membership/insertMembership/system',
        getMappedForumList: 'odor/membership/getMappedForumList/system',
        deleteMapping: 'odor/membership/deleteMapping/system',
        saveMapping: 'odor/membership/saveMapping/system'
    };

    static UserMgmtController = {
        getUserListAll: 'odor/user/getUserListAll/system',
        getUserList: 'odor/user/getUserList/system',
        checkDuplication: 'odor/user/checkDuplication/system',
        checkEmailDuplication: 'odor/user/checkEmailDuplication/system',
        insertUser: 'odor/user/insertUser/system',
        updateUser: 'odor/user/updateUser/system',
        deleteUser: 'odor/user/deleteUser/system',
        unblockUser: 'odor/user/unblockUser/system',
        blockUser: 'odor/user/blockUser/system',
        getBlockInfo: 'odor/user/getBlockInfo/system'
    };

    static CreatorMgmtController = {
        getCreatorListAll: 'odor/creator/getCreatorListAll/system',
        getCreatorList: 'odor/creator/getCreatorList/system',
        insertCreator: 'odor/creator/insertCreator/system',
        updateCreator: 'odor/creator/updateCreator/system',
        deleteCreator: 'odor/creator/deleteCreator/system'
    };

};