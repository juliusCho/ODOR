// eslint-disable-next-line no-unused-vars
/*global */
// eslint-disable-next-line
const SCRIPT_VALIDATOR = class {

    constructor(locale = 'ENG') {
        this.locale = locale;
    };

    set locale(locale) {
        this.locale = locale;
    };

    get locale() {
        return this.locale;
    };



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
    };


    static checkRules() {
        return [
            v => v || (this.locale === 'KO' ? '필수 동의 항목입니다.' : 'Required to agree.')
        ];
    };

    static selectRules() {
        return [
            v => this.nullCheck(v) || (this.locale === 'KO' ? '필수 선택 사항입니다.' : 'Required to choose.')
        ];
    };

    static textRules(num = 20) {
        return [
            v => this.nullCheck(v) || (this.locale === 'KO' ? '필수 입력 사항입니다.' : 'Required to fill in.'),
            v => (v?.length || 0) <= num || (this.locale === 'KO' ? '최대 입력 길이를 초과하였습니다.' : 'Exceeded max input length')
        ];
    };

    static emailRules(num) {
        return this.textRules(num).concat(
            [v => /.+@.+\..+/.test(v) || (this.locale === 'KO' ? '이메일 형식에 맞지 않습니다.' : 'Invalid email format')]
        )
    };

    static urlRules(num) {
        return this.textRules(num).concat([
                v => /^(http:\/\/www\.|https:\/\/www\.|http:\/\/|https:\/\/)?[a-z0-9]+([\-\.]{1}[a-z0-9]+)*\.[a-z]{2,5}(:[0-9]{1,5})?(\/.*)?$/.test(v)
                || (this.locale === 'KO' ? 'URL 형식에 맞지 않습니다.' : 'Invalid URL format')
        ]);
    };



}