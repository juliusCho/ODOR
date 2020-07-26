<template>
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
        <button type="button" @click="search">
            <v-icon style="margin-right: 5px;">
                mdi-magnify
            </v-icon>
        </button>
        <input type="text" class="searchInput" v-model="searchWord"/>
    </div>
</template>

<script>
    export default {
        name: "ForumSearchBar",
        props: {
            searchBtns: {
                type: Array,
                default: () => [],
                required: true
            },
            type: {
                type: String,
                default: 'search',   // search: 검색어와 더불어 조건 / sort: 정렬
                required: false
            }
        },
        mounted() {
            ENTER_ACTION(this.search);
        },
        data() {
            return {
                searchClicked: '',
                searchWord: ''
            }
        },
        methods: {
            removeSearchClick() {
                this.searchClicked = '';
            },
            clickSearchBtn(btn) {
                this.searchClicked = btn;

                if (this.type === 'sort') {
                    this.$emit('sort', btn);
                }
            },
            search() {
                this.$emit('search', {btn: this.searchClicked, word: this.searchWord});
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