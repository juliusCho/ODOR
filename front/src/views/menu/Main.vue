<template>
    <v-container>
        <video-bg :sources="['@/assets/video/valentino.mp4']" :img="'@/assets/img/logo.svg'">
            왜안나오냐냐ㅑㅑㅑㅑ<br>
            딴걸로 바꾸장S2
        </video-bg>
    </v-container>
</template>

<script>
    import VideoBg from 'vue-videobg';

    export default {
        name: 'Home',
        components: {
            VideoBg
        },
        data() {
            return {}
        }
    }
</script>
