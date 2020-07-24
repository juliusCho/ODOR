<template>
    <v-container class="container">
        <v-row>
            <v-spacer/>
            <v-col>
                <div class="searchBox rounded">
                    <div v-for="btn in searchBtns" :key="btn.id">
                        <button type="button"
                                v-if="searchClicked === btn.id"
                                class="btn clickedSearch rounded"
                                @click="removeSearchClick"
                        >
                            {{ btn.label }}
                        </button>
                        <button type="button"
                                v-else
                                class="btn unclickedSearch rounded"
                                @click="clickSearchBtn(btn.id)"
                        >
                            {{ btn.label }}
                        </button>
                    </div>
                    <v-icon style="margin-right: 5px;">
                        mdi-magnify
                    </v-icon>
                    <input type="text" class="searchInput"/>
                </div>
            </v-col>
            <v-spacer/>
        </v-row>
    </v-container>
</template>

<script>
    export default {
        name: 'Review',
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
                ],
                searchClicked: ''
            };
        },
        methods: {
            removeSearchClick() {
                this.searchClicked = '';
            },
            clickSearchBtn(btn) {
                this.searchClicked = btn;
            }
        }
    }
</script>

<style scoped>
    .rounded {
        border-radius: 25px;
    }

    .searchBox {
        display: flex;
        border: 2px solid #488aff;
        height: 40px;
        align-items: center;
    }

    .searchBox *:active, *:focus {
        outline: 0;
        border: none;
        -moz-outline-style: none;
    }

    .btn {
        margin: 0 5px 0 0;
        width: 120px;
        height: 36px;
    }

    .unclickedSearch {
        color: #518aff;
        background-color: white;
    }

    .unclickedSearch:hover {
        color: white;
        background-color: #83b0ff;
    }

    .clickedSearch {
        color: white;
        background-color: #488aff;
    }

    .searchInput {
        width: 200px;
        height: 36px;
    }
</style>