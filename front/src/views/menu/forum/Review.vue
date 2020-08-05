<template>
  <v-container>
    <v-row>
      <v-spacer/>
      <v-col>
        <ForumSearchBar :searchBtns="searchBtns" @search="search"/>
      </v-col>
      <v-spacer/>
    </v-row>
    <v-row>
      <div class="gridTitle" style="margin-top: 20px;">
        {{ $t('forum.review.hotReview') }}
      </div>
    </v-row>
    <v-row style="justify-content: center;">
      <DataGrid
          :data="hotReviews"
          :columns="gridCol"
          :filterKey="hotReviewsSearch"
      ></DataGrid>
    </v-row>
    <v-row>
      <div class="gridTitle">
        {{ $t('forum.review.newReview') }}
      </div>
    </v-row>
    <v-row style="justify-content: center;">
      <DataGrid
          :data="newReviews"
          :columns="gridCol"
          :filterKey="newReviewsSearch"
      ></DataGrid>
    </v-row>
  </v-container>
</template>

<script>
import ForumSearchBar from "@/views/components/ForumSearchBar";
import DataGrid from "@/views/components/DataGrid";

export default {
  name: 'Review',
  components: {
    ForumSearchBar,
    DataGrid
  },
  props: {
    forumKey: {
      type: Number,
      default: 0,
      required: true
    },
    categoryId: {
      type: String,
      default: '',
      required: true
    }
  },
  mounted() {
    // console.log('REVIEW');
    // console.log(this.forumKey);
    // console.log(this.categoryId);
    this.search();
  },
  data() {
    return {
      searchBtns: [
        {id: 'productName', label: this.$t('forum.review.searchByProductNm')},
        {id: 'brandName', label: this.$t('forum.review.searchByBrandNm')},
        {id: 'reviewTitle', label: this.$t('forum.review.searchByReviewTitle')},
        {id: 'reviewContent', label: this.$t('forum.review.searchByReviewContent')}
      ],

      gridCol: [
        {width: 150, key: 'brandName', label: this.$t('forum.review.colBrand')},
        {width: 200, key: 'productName', label: this.$t('forum.review.colProduct')},
        {width: '*', key: 'title', label: this.$t('forum.review.colTitle')},
        {width: 120, key: 'creatorName', label: this.$t('forum.review.colWriter')},
        {width: 150, key: 'updateDtTime', label: this.$t('forum.review.colDate')}
      ],
      hotReviews: [],
      hotReviewsSearch: '',
      newReviews: [],
      newReviewsSearch: ''
    };
  },
  methods: {
    search(searchOption = {}) {
      axios.post(
          API.ForumController.list + '?forumType=review',
          Object.assign(searchOption,
              {categoryId: this.categoryId, forumKey: this.forumKey})
      )
      .then(res => {
        this.hotReviews = res.data;
      });
    }
  }
}
</script>

<style scoped>
.gridTitle {
  margin: 50px 0 20px 10px;
  font-size: 30px;
  font-weight: 300;
}
</style>