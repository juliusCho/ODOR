const COMMON_UTIL = class {

    // === for (let i = 0; i < length; i++) { arg.push('msg ' + i) }
    // usage : range(length).map(i => `msg ${i}`)
    static range = length => Array.from({length}, (_, i) => i);
}