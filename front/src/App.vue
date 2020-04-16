<template>
  <v-app>
    <v-app-bar
      app
      color="primary"
      dark
    >
      <div class="d-flex align-center">
        <v-img
          alt="Vuetify Logo"
          class="shrink mr-2"
          contain
          src="https://cdn.vuetifyjs.com/images/logos/vuetify-logo-dark.png"
          transition="scale-transition"
          width="40"
        />

        <v-img
          alt="Vuetify Name"
          class="shrink mt-1 hidden-sm-and-down"
          contain
          min-width="100"
          src="https://cdn.vuetifyjs.com/images/logos/vuetify-name-dark.png"
          width="100"
        />

      </div>

      <v-spacer></v-spacer>

      <v-btn
              @click="switchToOdor = !switchToOdor"
              fab color="accent"
              class="mx-2"
              elevation="5"
      >
        <v-icon>mdi-account</v-icon>
      </v-btn>
    </v-app-bar>

    <v-content>
      <template v-if="switchToOdor">
        <Login/>
      </template>
      <template v-else>
        <VuetifyHelloWorld/>
      </template>
    </v-content>
  </v-app>
</template>

<script>
  import VuetifyHelloWorld from '@/views/components/VuetifyHelloWorld';
  import Login from '@/views/menu/Login';

  export default {
    name: 'App',
    components: {
      VuetifyHelloWorld,
      Login
    },
    data() {
      return {
        switchToOdor: false
      }
    }
  };
</script>
