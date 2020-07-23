<template>
    <v-container>
        <video-player
                :source="videoSource"
                :type="videoType"
                :height="videoHeight"
                :width="videoWidth"
        ></video-player>
    </v-container>
</template>

<script>
    import VideoPlayer from "@/views/components/VideoPlayer";

    export default {
        name: 'Home',
        components: {
            VideoPlayer
        },
        data () {
            return {
                videoSource: 'valentino.mp4',
                videoType: 'video/mp4',
                videoWidth: '100%',
                videoHeight: '100%'
            };
        }
    }
</script>

<style scoped>
</style>