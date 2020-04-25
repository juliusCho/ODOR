<template>
    <div v-if="menu.length > 0">
        <v-menu transition="scroll-y-transition">
            <template v-slot:activator="{ on }">
                <v-btn v-on="on" small text>{{ menuName }}</v-btn>
            </template>
            <v-list style="margin-top: 35px;">
                <v-list-item
                        v-for="sub in menu"
                        :key="sub[keyCol]"
                        link
                        @click="goTo('')"
                >
                    <v-list-item-title
                            v-text="sub[labelCol]"
                    />
                </v-list-item>
            </v-list>
        </v-menu>
    </div>
</template>

<script>
    export default {
        name: 'MenuWithChildren',
        props: {
            menuName: {
                type: String,
                default: '',
                required: true
            },
            menu: {
                type: Array,
                default: () => {return [];},
                required: true
            },
            keyCol: {
                type: String,
                default: '',
                required: true
            },
            labelCol: {
                type: String,
                default: '',
                required: true
            }
        },
        methods: {
            goTo(page) {
                this.$emit('goTo', page);
            }
        }
    }
</script>