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
        getForumList: 'odor/forums/system',
        checkDuplication: 'odor/forums/check/duplication/system',
        deleteForum: 'odor/forums/delete/system',
        updateForum: 'odor/forums/update/system',
        insertForum: 'odor/forums/insert/system'
    };

    static BrandMgmtController = {
        getBrandList: 'odor/brands/system',
        checkDuplication: 'odor/brands/check/duplication/system',
        insertBrand: 'odor/brands/insert/system',
        updateBrand: 'odor/brands/update/system',
        deleteBrand: 'odor/brands/delete/system',
        getMappedCategoryList: 'odor/brands/maps/system',
        deleteMapping: 'odor/brands/maps/delete/system',
        saveMapping: 'odor/brands/map/update/system'
    };

    static CodeMgmtController = {
        getCodeGroupList: 'odor/codegroups/system',
        checkDuplication: 'odor/codegroups/check/duplication/system',
        insertCodeGroup: 'odor/codegroups/insert/system',
        updateCodeGroup: 'odor/codegroups/update/system',
        deleteCodeGroup: 'odor/codegroups/delete/system',
        getCodeListSystem: 'odor/codegroups/codes/system',
        checkDetailDuplication: 'odor/codegroups/codes/check/duplication/system',
        insertCode: 'odor/codegroups/codes/insert/system',
        updateCode: 'odor/codegroups/codes/update/system',
        deleteCode: 'odor/codegroups/codes/delete/system',
        getForumCodeList: 'odor/codegroups/codes/forum/system'
    };

    static MessageMgmtController = {
        getMessageList: 'odor/messages/rest',
        insertMessage: 'odor/messages/insert/system',
        getMessageListSystem: 'odor/messages/system',
        checkDuplication: 'odor/messages/check/duplication/system',
        updateMessage: 'odor/messages/update/system',
        deleteMessageGroup: 'odor/messages/delete/system'
    };

    static CountryMgmtController = {
        getCountryList: 'odor/countries/system',
        checkDuplication: 'odor/countries/check/duplication/system',
        insertCountry: 'odor/countries/insert/system',
        updateCountry: 'odor/countries/update/system',
        deleteCountry: 'odor/countries/delete/system'
    };

    static CategoryMgmtController = {
        getCategoryList: 'odor/categories/system',
        checkDuplication: 'odor/categories/check/duplication/system',
        insertCategory: 'odor/categories/insert/system',
        updateCategory: 'odor/categories/update/system',
        deleteCategory: 'odor/categories/delete/system'
    };

    static MembershipMgmtController = {
        getMembershipList: 'odor/memberships/system',
        checkDuplication: 'odor/memberships/check/duplication/system',
        deleteMembership: 'odor/memberships/delete/system',
        updateMembership: 'odor/memberships/update/system',
        insertMembership: 'odor/memberships/insert/system',
        getMappedForumList: 'odor/memberships/maps/system',
        deleteMapping: 'odor/memberships/maps/delete/system',
        saveMapping: 'odor/memberships/maps/update/system'
    };

    static UserMgmtController = {
        getUserList: 'odor/users/system',
        checkDuplication: 'odor/users/check/duplication/system',
        checkEmailDuplication: 'odor/users/check/email/duplication/system',
        insertUser: 'odor/users/insert/system',
        updateUser: 'odor/users/update/system',
        deleteUser: 'odor/users/delete/system',
        unblockUser: 'odor/users/block/execute/system',
        blockUser: 'odor/users/block/cancel/system',
        getBlockInfo: 'odor/users/block/system',
        uploadPhoto: 'odor/users/photo/update/api'
    };

    static CreatorMgmtController = {
        getCreatorList: 'odor/creators/system',
        insertCreator: 'odor/creators/insert/system',
        updateCreator: 'odor/creators/update/system',
        deleteCreator: 'odor/creators/delete/system'
    };

    static ProductTypeMgmtController = {
        getProductTypeList: 'odor/producttypes/system',
        checkDuplication: 'odor/producttypes/check/duplication/system',
        deleteProductType: 'odor/producttypes/delete/system',
        updateProductType: 'odor/producttypes/update/system',
        insertProductType: 'odor/producttypes/insert/system'
    };

    static IngredientMgmtController = {
        getIngredientList: 'odor/ingredients/system',
        checkDuplication: 'odor/ingredients/check/duplication/system',
        deleteIngredient: 'odor/ingredients/delete/system',
        updateIngredient: 'odor/ingredients/update/system',
        insertIngredient: 'odor/ingredients/insert/system'
    };

    static ProductMgmtController = {
        getProductList: 'odor/products/system',
        checkDuplication: 'odor/products/check/duplication/system',
        insertProduct: 'odor/products/insert/system',
        updateProduct: 'odor/products/update/system',
        deleteProduct: 'odor/products/delete/system',
        getMappedBrandList: 'odor/products/maps/brands/system',
        getMappedCreatorList: 'odor/products/maps/creators/system',
        getMappedIngredientList: 'odor/products/maps/ingredients/system',
        getMappedProductTypeList: 'odor/products/maps/producttypes/system',
        saveBrandMapping: 'odor/products/maps/brands/update/system',
        saveCreatorMapping: 'odor/products/maps/creators/update/system',
        saveIngredientMapping: 'odor/products/maps/ingredients/update/system',
        saveProductTypeMapping: 'odor/products/maps/producttypes/update/system',
        deleteBrandMapping: 'odor/products/maps/brands/delete/system',
        deleteCreatorMapping: 'odor/products/maps/creators/delete/system',
        deleteIngredientMapping: 'odor/products/maps/ingredients/delete/system',
        deleteProductTypeMapping: 'odor/products/maps/producttypes/delete/system'
    };

    static RatingItemMasterMgmtController = {
        getRatingItemMasterList: '/odor/ratings/system',
        checkDuplication: '/odor/ratings/check/duplication/system',
        deleteRatingItemMaster: '/odor/ratings/delete/system',
        updateRatingItemMaster: '/odor/ratings/update/system',
        insertRatingItemMaster: '/odor/ratings/insert/system'
    };

    static ForumController = {
        list: '/odor/articles/api',
        detail: '/odor/articles/one/api',
        create: '/odor/articles/insert/api',
        update: '/odor/articles/update/api',
        delete: '/odor/articles/delete/api'
    };

};