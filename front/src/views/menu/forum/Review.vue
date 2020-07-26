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
        name: 'Review',
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
            console.log('REVIEW');
            console.log(this.forumKey);
            console.log(this.categoryId);
        },
        data() {
            return {
                searchBtns: [
                    {id: 'productName', label: this.$t('forum.review.searchByProductNm')},
                    {id: 'brandName', label: this.$t('forum.review.searchByBrandNm')},
                    {id: 'reviewTitle', label: this.$t('forum.review.searchByReviewTitle')},
                    {id: 'reviewContent', label: this.$t('forum.review.searchByReviewContent')}
                ]
            };
        },
        methods: {
            search(searchOption) {
                console.log(searchOption);
            }
        }
    }
</script>

<style scoped>

</style>