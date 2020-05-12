const switchers = {
    activate: true,
    beforeCreate: true,
    created: true,
    beforeMount: true,
    mounted: true,
    destroyed: true
};

export default {
    install(Vue, options) {
        Object.assign(switchers, options);

        Vue.mixin({
            beforeCreate() {
                if (switchers.activate)
                    console.log(`${this.$options.name} about to create`);
            },
            created() {
                if (switchers.activate)
                    console.log(`${this.$options.name} created`);
            },
            beforeMount() {
                if (switchers.activate)
                    console.log(`${this.$options.name} about to mount`);
            },
            mounted() {
                if (switchers.activate)
                    console.log(`${this.$options.name} mounted`);
            },
            destroyed() {
                if (switchers.activate)
                    console.log(`${this.$options.name} destroyed`);
            }
        })
    }
}