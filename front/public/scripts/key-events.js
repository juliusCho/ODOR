/*global */
// eslint-disable-next-line

let ACTION_TARGET = e => {};

const ENTER_ACTION = (func = () => {}) => {

    if (typeof func !== 'function') {
        return;
    }
    document.removeEventListener('keyup', ACTION_TARGET);

    ACTION_TARGET = e => {
        switch (e.key) {
            case 'Enter' :
                func();
                break;
            default :
                break;
        }
    };

    document.addEventListener('keyup', ACTION_TARGET);

};
