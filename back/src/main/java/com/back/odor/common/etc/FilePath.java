package com.back.odor.common.etc;

import lombok.Getter;

@Getter
public enum FilePath {

    USER_PROFILE("profile"),
    PRODUCT("product"),
    PHOTO("photo");


    private String dir;

    FilePath(String dir) {
        this.dir = dir;
    }

}
