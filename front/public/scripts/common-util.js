// eslint-disable-next-line no-unused-vars
/*global */
// eslint-disable-next-line
const COMMON_UTIL = class {

    // 배열 객체 중복 제거
    static removeArrDuplicate = arr => arr.reduce((tmp, now) => tmp.includes(now) ? tmp : [now, ...tmp], []).reverse();

}

