<template>
    <div>
        <!-- slot for parent component to activate the file changer -->
        <div @click="launchFilePicker()">
            <slot name="activator"></slot>
        </div>
        <!-- image input: style is set to hidden and assigned a ref so that it can be triggered -->
        <input type="file"
               ref="file"
               accept="image/x-png,image/gif,image/jpeg"
               :name="uploadFieldName"
               @change="onFileChange(
          $event.target.name, $event.target.files)"
               style="display:none">
        <!-- error dialog displays any potential errors -->
        <v-dialog v-model="errorDialog" max-width="300">
            <v-card>
                <v-card-text
                        style="padding-top: 20px; text-align: center; font: icon;"
                        class="subheading">
                    {{errorText}}
                </v-card-text>
                <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn style="height: 30px; margin-bottom: 10px; color: cornflowerblue;"
                            @click="errorDialog = false" flat>OK</v-btn>
                    <v-spacer></v-spacer>
                </v-card-actions>
            </v-card>
        </v-dialog>
    </div>
</template>

<script>
    export default {
        name: "image-input",
        data: ()=> ({
            errorDialog: null,
            errorText: '',
            uploadFieldName: 'file',
            maxSize: 1024
        }),
        props: {
            // Use "value" here to enable compatibility with v-model
            value: Object,
        },
        methods: {
            launchFilePicker(){
                this.$refs.file.click();
            },
            onFileChange(fieldName, file) {
                const { maxSize } = this
                let imageFile = file[0]

                //check if user actually selected a file
                if (file.length>0) {
                    let size = imageFile.size / maxSize / maxSize
                    if (!imageFile.type.match('image.*')) {
                        // check whether the upload is an image
                        this.errorDialog = true
                        this.errorText = this.$t('common.imageOnlyMsg');
                    } else if (size>1) {
                        // check whether the size is greater than the size limit
                        this.errorDialog = true
                        this.errorText = this.$t('common.maximumImgSizeExceeded');
                    } else {
                        // Append file into FormData & turn file into image URL
                        let formData = new FormData()
                        let imageURL = URL.createObjectURL(imageFile)
                        formData.append(fieldName, imageFile)
                        // Emit FormData & image URL to the parent component
                        this.$emit('input', { formData, imageURL })
                    }
                }
            }
        }
    }
</script>

<style scoped>

</style>