/*global */
// eslint-disable-next-line

let ACTION_TARGET = e => {};

const ENTER_ACTION = (func = () => {}) => {

    document.removeEventListener('keyup', ACTION_TARGET);

    if (typeof func !== 'function') {
        return;
    }

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