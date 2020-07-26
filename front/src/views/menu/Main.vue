<template>
    <v-container>
        <video id="valentino"
               muted
               loop
               autoplay
        >
            Video is now onloading...
            <source src="@/assets/video/valentino.mp4" type="video/mp4">
        </video>
    </v-container>
</template>

<script>
    export default {
        name: 'Home',
        data () {
            return {
            };
        }
    }
</script>

<style scoped>
    #valentino {
        width: 100%;
        height: 100%;
    }
</style>