<template>
    <form @submit.prevent="onSubmit">
        <div v-for="(input, index) in inputObj" :key="index">
            <label>
                <input type="text" :value="input" @keyup="onKeyUp" :placeholder="'입력하라'" autofocus/>
            </label>
        </div>
        <button v-show="inputObj.length" @click="validate">Submit</button>
    </form>
</template>

<script>
    import { v } from '@/views/utils/script-validator'

    export default {
        name: "InputValidator",
        props: {
            inputObj: {
                type: Array,
                default: () => [],
                required: true
            }
        },
        mounted() {
            this.validation();
        },
        methods: {
            validation() {
                if (!v.nullCheck(this.inputObj)) return;

                this.inputObj.forEach(input => {

                });
            }
        }
    }
</script>

<style scoped>

</style>