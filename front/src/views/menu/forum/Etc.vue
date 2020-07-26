<template>
    <v-container>
        <v-row>
            <v-spacer/>
            <v-col>
                <ForumSearchBar :searchBtns="searchBtns" @search="search"/>
            </v-col>
            <v-spacer/>
        </v-row>
    </v-container>
</template>

<script>
    import ForumSearchBar from "@/views/components/ForumSearchBar";

    export default {
        name: 'Etc',
        components: {
            ForumSearchBar
        },
        props: {
            forumKey: {
                type: Number,
                default: 0,
                required: false
            },
            categoryId: {
                type: String,
                default: '',
                required: false
            }
        },
        mounted() {
            console.log('ETC');
            console.log(this.forumKey);
            console.log(this.categoryId);
        },
        data() {
            return {
                searchBtns: [
                    {id: 'title', label: this.$t('forum.etc.searchByTitle')},
                    {id: 'content', label: this.$t('forum.etc.searchByContent')}
                ]
            }
        },
        methods: {
            search(searchOption) {
            }
        }
    }
</script>

<style scoped>

</style>