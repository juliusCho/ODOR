/*global */
// eslint-disable-next-line
const API = class {

    static CommonController = {
        setSystemLocale: 'odor/common/locale/rest',
        getMessageList: 'odor/common/messages/rest',
        getCodeList: 'odor/common/codes/rest',
        fileUpload: 'odor/common/file/api',
        displayImage: 'odor/common/image/rest'
    };

    static SessionController = {
        sessionCheck: 'odor/session/rest',
        validateLogin: 'odor/login/rest',
        logout: 'odor/logout/rest'
    };

    static ForumMgmtController = {
        getForumListAll: 'odor/forums/rest',
        getSystemList: 'odor/forums/systems/system',
        cruForum: 'odor/forums/system',
        checkDuplication: 'odor/forums/check/duplication/system',
        deleteForum: 'odor/forums/delete/system'
    };

    static BrandMgmtController = {
        cruBrand: 'odor/brands/system',
        checkDuplication: 'odor/brands/check/duplication/system',
        deleteBrand: 'odor/brands/delete/system',
        crudMapping: 'odor/brands/maps/system'
    };

    static CodeMgmtController = {
        cruCodeGroup: 'odor/codegroups/system',
        checkDuplication: 'odor/codegroups/check/duplication/system',
        deleteCodeGroup: 'odor/codegroups/delete/system',
        cruCode: 'odor/codegroups/codes/system',
        checkDetailDuplication: 'odor/codegroups/codes/check/duplication/system',
        deleteCode: 'odor/codegroups/codes/delete/system',
        getForumCodeList: 'odor/codegroups/codes/forums/system'
    };

    static MessageMgmtController = {
        getMessageList: 'odor/messages/rest',
        cruMessage: 'odor/messages/system',
        checkDuplication: 'odor/messages/check/duplication/system',
        deleteMessageGroup: 'odor/messages/delete/system'
    };

    static CountryMgmtController = {
        cruCountry: 'odor/countries/system',
        checkDuplication: 'odor/countries/check/duplication/system',
        deleteCountry: 'odor/countries/delete/system'
    };

    static CategoryMgmtController = {
        cruCategory: 'odor/categories/system',
        checkDuplication: 'odor/categories/check/duplication/system',
        deleteCategory: 'odor/categories/delete/system'
    };

    static MembershipMgmtController = {
        cruMembership: 'odor/memberships/system',
        checkDuplication: 'odor/memberships/check/duplication/system',
        deleteMembership: 'odor/memberships/delete/system',
        crudMapping: 'odor/memberships/maps/system'
    };

    static UserMgmtController = {
        cruUser: 'odor/users/system',
        checkDuplication: 'odor/users/check/duplication/system',
        checkEmailDuplication: 'odor/users/check/duplication/email/system',
        deleteUser: 'odor/users/delete/system',
        crdBlock: 'odor/users/block/system',
        uploadPhoto: 'odor/users/photo/api'
    };

    static CreatorMgmtController = {
        cruCreator: 'odor/creators/system',
        deleteCreator: 'odor/creators/delete/system'
    };

    static ProductTypeMgmtController = {
        cruProductType: 'odor/producttypes/system',
        checkDuplication: 'odor/producttypes/check/duplication/system',
        deleteProductType: 'odor/producttypes/delete/system'
    };

    static IngredientMgmtController = {
        cruIngredient: 'odor/ingredients/system',
        checkDuplication: 'odor/ingredients/check/duplication/system',
        deleteIngredient: 'odor/ingredients/delete/system'
    };

    static ProductMgmtController = {
        cruProduct: 'odor/products/system',
        checkDuplication: 'odor/products/check/duplication/system',
        deleteProduct: 'odor/products/delete/system',
        crudBrand: 'odor/products/maps/brands/system',
        crudCreator: 'odor/products/maps/creators/system',
        crudIngredient: 'odor/products/maps/ingredients/system',
        crudProductType: 'odor/products/maps/producttypes/system'
    };

    static RatingItemMasterMgmtController = {
        cruRatingItem: '/odor/ratings/system',
        checkDuplication: '/odor/ratings/check/duplication/system',
        deleteRatingItemMaster: '/odor/ratings/delete/system'
    };

    static ForumController = {
        cru: '/odor/articles/api',
        detail: '/odor/articles/one/api',
        delete: '/odor/articles/delete/api'
    };

};