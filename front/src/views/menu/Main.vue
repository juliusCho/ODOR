<template>
   <v-container>
    <video-bg :sources="['@/src/assets/video/valentino.mp4']" :img="'@/src/assets/img/logo.svg'">

    </video-bg>
   </v-container>

</template>

<script>
    export default {
        name: 'Home',
        data() {
            console.log('/front/src/assets/video/valentino.mp4');
            return {

            }
        }
    }
</script>
