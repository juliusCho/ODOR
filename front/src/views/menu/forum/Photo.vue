<template>
    <v-container>
        <v-row>
            <v-spacer/>
            <v-col>
                <ForumSearchBar
                        :searchBtns="searchBtns"
                        @search="search"
                        :type="'sort'"
                        @sort="sorting"
                />
            </v-col>
            <v-spacer/>
        </v-row>
    </v-container>
</template>

<script>
    import ForumSearchBar from "@/views/components/ForumSearchBar";

    export default {
        name: 'Photo',
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
            // console.log('PHOTO');
            // console.log(this.forumKey);
            // console.log(this.categoryId);
        },
        data() {
            return {
                searchBtns: [
                    {id: 'latest', label: this.$t('forum.photo.searchByLatest')},
                    {id: 'popular', label: this.$t('forum.photo.searchByPopular')},
                    {id: 'myFeed', label: this.$t('forum.photo.searchByMine')}
                ]
            }
        },
        methods: {
            search(searchOption) {
                let btn = searchOption.btn;
                let word = searchOption.word;

                console.log('클릭한 버튼: ' + btn);
                console.log('검색어: ' + word);
            },
            sorting(btn) {
                console.log('정렬기준: ' + btn);
            }
        }
    }
</script>

<style scoped>

</style>