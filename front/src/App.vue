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
              @click="goTo('MyPage')"
              fab color="accent"
              class="mx-2"
              elevation="5"
      >
        <v-icon>mdi-account</v-icon>
      </v-btn>
    </v-app-bar>

    <Router
            :routing="routing"
            @goTo="goTo"
            ref="router"
    />
  </v-app>
</template>

<script>
  import Router from '@/router/Router';

  export default {
    name: 'App',
    components: {
      Router
    },
    created() {
      this.routing = 'Home';
    },
    watch: {
      routing() {
        this.$router.push(this.routing).catch(() => {});
      },
      '$route'(to, from) {
        if (from.name === to.name) {
          return;
        }
        this.$refs.router.goTo(to.name);
      }
    },
    data() {
      return {
        routing: ''
      }
    },
    methods: {
      goTo(page) {
        this.routing = page;
      }
    }
  };
</script>
