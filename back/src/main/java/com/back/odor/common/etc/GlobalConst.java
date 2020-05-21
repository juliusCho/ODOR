package com.back.odor.common.etc;

import com.back.odor.menu.system.forummgmt.vo.ForumVO;

import java.util.ArrayList;
import java.util.List;

public class GlobalConst {

    public static final String VO_PACKAGE =
        "com.back.odor.menu.forum.odorgram.vo,"  +
        "com.back.odor.menu.forum.review.vo,"  +
        "com.back.odor.menu.forum.etc.vo,"  +
        "com.back.odor.menu.perfume.vo," +
        "com.back.odor.menu.system.forummgmt.vo," +
        "com.back.odor.menu.system.categorymgmt.vo," +
        "com.back.odor.menu.system.messagemgmt.vo," +
        "com.back.odor.menu.system.codemgmt.vo," +
        "com.back.odor.menu.system.usermgmt.vo," +
        "com.back.odor.menu.system.membershipmgmt.vo," +
        "com.back.odor.menu.system.brandmgmt.vo," +
        "com.back.odor.menu.system.producttypemgmt.vo," +
        "com.back.odor.menu.system.countrymgmt.vo," +
        "com.back.odor.menu.system.creatormgmt.vo," +
        "com.back.odor.menu.system.ingredientmgmt.vo," +
        "com.back.odor.menu.system.productmgmt.vo," +
        "com.back.odor.menu.system.ratingitemmgmt.vo"
    ;

    public static final String[] SYSTEM_MENU = {
        "MessageMgmt",
        "CountryMgmt",
        "CategoryMgmt",
        "CodeMgmt",
        "ForumMgmt",
        "MembershipMgmt",
        "UserMgmt",
        "BrandMgmt",
        "CreatorMgmt",
        "ProductTypeMgmt",
        "IngredientMgmt",
        "ProductMgmt",
        "RatingItemMgmt"
    };

    public static final String[] LOCALE_LIST = {
        "KO", "ENG"
    };

}
