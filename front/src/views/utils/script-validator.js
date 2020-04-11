// eslint-disable-next-line no-unused-vars
export class v {
    static nullCheck(input) {
        let result = true;

        switch (typeof input) {
            case 'undefined' :
                result = false;
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
            case 'function' :
                result = false;
                break;
            default :   // object
                if (input === []) result = input.length > 0;
                break;
        }
        return result;
    }
}