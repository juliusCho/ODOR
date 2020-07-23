<template>
    <div>
        <div class="player">
            <video controls
                   :style="`width: ${width};, height: ${height};`"
                   :src="srcPrefix + source" :type="type"
            >
            </video>
<!--            <div class="controls">-->
<!--                <button class="play" data-icon="P" aria-label="play pause toggle"></button>-->
<!--                <button class="stop" data-icon="S" aria-label="stop"></button>-->
<!--                <div class="timer">-->
<!--                    <div></div>-->
<!--                    <span aria-label="timer">00:00</span>-->
<!--                </div>-->
<!--                <button class="rwd" data-icon="B" aria-label="rewind"></button>-->
<!--                <button class="fwd" data-icon="F" aria-label="fast forward"></button>-->
<!--            </div>-->
        </div>
    </div>
</template>

<script>
    export default {
        name: "VideoPlayer",
        props: {
            source: {
                type: String,
                default: '',
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
            }
        },
        data() {
            return {
                srcPrefix: '@/assets/video/'
            };
        },
        methods: {
            aaa(source) {
                console.log(source);
                return this.srcPrefix + source.src;
            }
        }
    }
</script>

<style scoped>
    .controls {
        visibility: hidden;
        opacity: 0.5;
        width: 400px;
        border-radius: 10px;
        position: absolute;
        bottom: 20px;
        left: 50%;
        margin-left: -200px;
        background-color: black;
        box-shadow: 3px 3px 5px black;
        transition: 1s all;
        display: flex;
    }

    .player:hover .controls, player:focus .controls {
        opacity: 1;
    }

    @font-face {
        font-family: 'HeydingsControlsRegular';
        /*src: url('fonts/heydings_controls-webfont.eot');*/
        /*src: url('fonts/heydings_controls-webfont.eot?#iefix') format('embedded-opentype'),*/
        /*url('fonts/heydings_controls-webfont.woff') format('woff'),*/
        /*url('fonts/heydings_controls-webfont.ttf') format('truetype');*/
        font-weight: normal;
        font-style: normal;
    }

    button:before {
        /*font-family: HeydingsControlsRegular;*/
        font-size: 20px;
        position: relative;
        content: attr(data-icon);
        color: #aaa;
        text-shadow: 1px 1px 0px black;
    }

    .timer {
        line-height: 38px;
        font-size: 10px;
        font-family: monospace;
        text-shadow: 1px 1px 0px black;
        color: white;
        flex: 5;
        position: relative;
    }

    .timer div {
        position: absolute;
        background-color: rgba(255,255,255,0.2);
        left: 0;
        top: 0;
        width: 0;
        height: 38px;
        z-index: 2;
    }

    .timer span {
        position: absolute;
        z-index: 3;
        left: 19px;
    }
</style>