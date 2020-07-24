<template>
    <v-container>
        <video-player
                :source="videoSource"
                :type="videoType"
                height="80%"
                width="80%"
                :muted="false"
                :volume="0.2"
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
                videoSource: 'valentino',
                videoType: 'mp4'
            };
        }
    }
</script>

<style scoped>
</style>