/*global */
// eslint-disable-next-line
const ENTER_ACTION = func => {

    document.addEventListener('keyup', e => {
        switch (e.key) {
            case 'Enter' :
                if (typeof func !== 'function') {
                    return;
                }
                func();
                break;
            default :
                break;
        }
    });

};
