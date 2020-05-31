<template>
    <v-menu
            ref="menu"
            v-model="menu"
            :close-on-content-click="false"
            :nudge-right="40"
            :nudge-top="20"
            transition="scale-transition"
            offset-y
            min-width="290px"
    >
        <template v-slot:activator="{ on }">
            <v-text-field
                    v-model="inputDate"
                    :label="label"
                    readonly
                    v-on="on"
                    prepend-icon="mdi-calendar-today"
                    :rules="rules"
            >
            </v-text-field>
        </template>
        <v-date-picker
                ref="picker"
                v-model="dateData"
                @input="save"
                :max="max + '-01-01'"
                :min="min + '-01-01'"
                reactive
                no-title
        ></v-date-picker>
    </v-menu>
</template>

<script>
    export default {
        name: "YearPicker",
        props: {
            date: {
                type: Number,
                default: Number(new Date().toISOString().substr(0, 4)),
                required: true
            },
            label: {
                type: String,
                default: 'Year',
                required: false
            },
            max: {
                type: Number,
                default: Number(new Date().toISOString().substr(0, 4)),
                required: false
            },
            min: {
                type: Number,
                default: 1000,
                required: false
            },
            rules: {
                type: Array,
                default: () => [true],
                required: false
            }
        },
        data() {
            return {
                menu: false,
                dateData: '2019-12-27',
                inputDate: 2019
            };
        },
        mounted() {
            this.dateData = (this.date || new Date().toISOString().substr(0, 4)) + '-01-01';
        },
        watch: {
            date() {
                console.log(this.date);
                if (!this.date) return;

                this.dateData = this.date + '-01-01';
            },
            async dateData() {
                if (!this.dateData) return;

                if (this.dateData.length === 4) {
                    this.dateData += '-01-01';
                } else {
                    this.inputDate = Number(this.dateData.substr(0, 4));
                    await this.$emit('bind', this.inputDate);
                }
            },
            menu(val) {
                val && this.$nextTick(() => (this.$refs.picker.activePicker = 'YEAR'));
            }
        },
        methods: {
            save (date) {
                this.$refs.menu.save(date);
                this.$refs.picker.activePicker = 'YEAR'
                this.menu = false;
            }
        }
    }
</script>