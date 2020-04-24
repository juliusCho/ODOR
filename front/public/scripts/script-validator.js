// eslint-disable-next-line no-unused-vars
/*global */
// eslint-disable-next-line
const SCRIPT_VALIDATOR = class {
    static nullCheck(...args) {
        let result = true;

        for (let i = 0, ii = args.length; i < ii; i++) {
            let input = args[i];
            if (!result) {
                break;
            }
            switch (typeof input) {
                case 'object' :
                    if (input === []) result = input.length > 0;
                    break;
                case 'number' :
                case 'bigint' :
                    result = input !== 0;
                    break;
                case 'boolean' :
                    break;
                case 'string' :
                    result = input !== '';
                    break;
                case 'symbol' :
                    result = this.nullCheck(input.toString());
                    break;
                default :   // undefined, function
                    result = false;
                    break;
            }
        }
        return result;
    }
}