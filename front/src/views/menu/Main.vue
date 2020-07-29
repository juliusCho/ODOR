<template>
    <v-container class="container">
        <font-awesome-icon
            v-if="videoLoading"
            icon="spinner"
            spin
            size="6x"
            style="margin: 50px 0 50px 0;"
        />
        <video v-else
               id="valentino"
               ref="myVid"
               muted
               loop
               autoplay
        >
            {{ $t('common.incompatibleVid') }}
            <source src="@/assets/video/valentino.mp4" type="video/mp4">
        </video>
    </v-container>
</template>

<script>
    export default {
      name: 'Home',
      data() {
        return {
          videoLoading: true
        };
      },
      created() {
        this.videoLoading = true;
      },
      mounted() {
        this.$nextTick(() => {
          this.videoLoading = false;
        });
      }
    }
</script>

<style scoped>
    .container {
      text-align: center;
    }

    #valentino {
      width: 100%;
      height: 100%;
    }
</style>