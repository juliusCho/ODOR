<template>
  <RightTopAlert
      status="error"
      :msg="msg"
      :show="show"
      @hideDisplay="show = false"
  >
  </RightTopAlert>
</template>

<script>
import RightTopAlert from "@/views/components/RightTopAlert";

export default {
  name: "ApiErrorAlert",
  components: {
    RightTopAlert
  },
  props: {
    response: {
      type: Object,
      default: () => {},
      required: true
    }
  },
  data() {
    return {
      show: false,
      msg: ''
    };
  },
  watch: {
    response() {
      this.initData();
      this.$emit('response', this.apiResponse());
    }
  },
  methods: {
    initData() {
        this.show = false;
        this.msg = '';
    },
    apiResponse() {
      if (this.response?.status) return true;

      switch(this.response.status) {
        case 200: // 성공
        case 201: // 자원 완전수정/입력 성공
        case 202: // 요청 성공 but 서버 is still loading
        case 204: // 삭제 성공
          return true;
        case 400:
          this.msg = this.$t('common.apiBadRequest');
          this.show = true;
          return false;
        case 401:
        case 403:
          this.msg = this.$t('common.apiUnauthorized');
          this.show = true;
          return false;
        case 404:
          this.msg = this.$t('common.apiNotFound');
          this.show = true;
          return false;
        case 405:
          this.msg = this.$t('common.apiMethodNotAllowed');
          this.show = true;
          return false;
        case 409:
          this.msg = this.$t('common.apiConflict');
          this.show = true;
          return false;
        case 429:
          this.msg = this.$t('common.apiTooManyRequests');
          this.show = true;
          return false;
        case 500:
        case 502:
        case 503:
          this.msg = this.$t('common.apiServerErrors');
          this.show = true;
          return false;
        default:
          return false;
      }
    }
  }
}
</script>

<style scoped>

</style>