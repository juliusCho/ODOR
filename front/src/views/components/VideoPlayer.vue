<template>
    <div style="text-align: center;">
        <video  id="myVid"
                :muted="muted"
                loop
                autoplay
               :style="`width: ${width};, height: ${height};`"
        >
            <template v-if-="source === 'valentino'">
                <source src="@/assets/video/valentino.mp4" :type="'video/' + type">
            </template>
        </video>
    </div>
</template>

<script>
    export default {
        name: "VideoPlayer",
        props: {
            source: {
                type: String,
                default: 'valentino.mp4',
                required: true
            },
            type: {
                type: String,
                default: 'video/mp4',
                required: false
            },
            width: {
                type: String,
                default: '100%',
                required: false
            },
            height: {
                type: String,
                default: '100%',
                required: false
            },
            muted: {
                type: Boolean,
                default: false,
                required: false
            },
            volume: {
                type: Number,
                default: 0.2,
                required: false
            }
        },
        mounted() {
            document.getElementById('myVid').volume = this.volume;
        }
    }
</script>