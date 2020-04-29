<template>
  <v-app>
    <v-app-bar
            app
            dark
    >
      <v-btn @click="goTo('Home')"
             style="font-size: 30px;" text>ODOR</v-btn>

      <v-spacer/>
      <v-spacer/>
      <v-spacer/>
      <v-spacer/>
      <v-spacer/>

      <MenuWithChildren
              :menu="forum"
              menuName="forum"
              keyCol="forumKey"
              labelCol="forumName"
              @goTo="goTo"
      />

      <v-spacer/>

      <v-btn @click="goTo('Perfume')" small text>PERFUME</v-btn>

      <v-spacer/>

      <v-btn @click="goTo('About')" small text>ABOUT</v-btn>

      <v-spacer/>

      <MenuWithChildren
              :menu="system"
              menuName="system"
              keyCol="forumKey"
              labelCol="forumName"
              @goTo="goTo"
      />

      <v-spacer/>
      <v-spacer/>
      <v-spacer/>

      <v-col cols="1" style="margin-top: 30px;">
        <v-combobox
                v-model="locale"
                :items="localeList"
                label="Choose your language"
        />
      </v-col>

      <v-btn  v-if="loggedInBoo"
              @click="membershipClicked"
              fab color="secondary"
              class="mx-2"
              elevation="5"
      >
        <v-icon>
          mdi-account-cog
        </v-icon>
      </v-btn>

      <v-btn  v-else
              @click="membershipClicked"
              fab color="#d6d6d6"
              class="mx-2"
              elevation="5"
      >
        <v-icon>mdi-account-arrow-left-outline</v-icon>
      </v-btn>

    </v-app-bar>

    <Router
            :routing="routing"
            @goTo="goTo"
            ref="router"
            @loggedIn="loggedIn"
    />
  </v-app>
</template>

<script>
  import Router from '@/router/Router';
  import MenuWithChildren from '@/views/components/MenuWithChildren';

  export default {
    name: 'App',
    components: {
      Router,
      MenuWithChildren
    },
    created() {
      this.setSystemLocale();
      this.routing = 'Home';
      this.getForumList();
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
      },
      loggedInBoo() {
        this.sysMngrYn = this.sysMngrCheck();
        this.getSystemList();
      },
      locale() {
        this.setSystemLocale();
      }
    },
    data() {
      return {
        locale: 'ENG',
        localeList: [
          'ENG',
          'KO'
        ],
        routing: '',
        loggedInBoo: false,
        sysMngrYn: false,
        forumKey: 0,
        categoryId: '',
        forum: [],
        system: []
      }
    },
    methods: {
      // Set System Language
      async setSystemLocale() {
        let locale = this.locale;
        await axios.post(
                API.CommonController.setSystemLocale,
                null,
                {params:{locale}}
        ).then(() => {
          this.setMessageList(locale);
        });
      },
      // Set System Message
      async setMessageList(locale) {
        await axios.post(
                API.CommonController.getMessageList
        ).then(res => {
          MESSAGE.setMessageList(res.data);
          this.setCodeList(locale);
        });
      },
      // Set System Code
      async setCodeList(locale) {
        await axios.post(
                API.CommonController.getCodeList
        ).then(res => {
          CODE.setCodeList(res.data);
        });
      },
      // get system list
      async getSystemList() {
        if (!this.loggedInBoo || !this.sysMngrYn) {
          return;
        }
        if (this.system.length > 0) {
          return;
        }
        await axios.post(
                API.ForumMgmtController.getSystemList,
                TMP_SESSION.getLoginUser()
        ).then(res => {
          if (res.data.length > 0) {
            this.system = res.data;
          }
        });
      },
      // get forum list
      getForumList() {
        if (this.forum.length > 0) {
          return;
        }
        axios.get(API.ForumMgmtController.getForumList)
                .then(res => {
                  if (res.data.length > 0) {
                    this.forum = res.data;
                  }
                });
      },
      // routing
      goTo(page, fKey, cateId) {
        if (SCRIPT_VALIDATOR.nullCheck(fKey)) {
          this.forumKey = fKey;
        }
        if (SCRIPT_VALIDATOR.nullCheck(cateId)) {
          this.categoryId = cateId;
        }
        this.routing = page;
      },
      // ************************************************************
      // Login Icon Clicked + Session check
      // ************************************************************
      membershipClicked() {
        let goTo = true;
        for (let i in MEMBERSHIP_PAGES) {
          if (this.routing === MEMBERSHIP_PAGES[i]) {
            goTo = false;
          }
        }
        if (!goTo) return;

        if (!SCRIPT_VALIDATOR.nullCheck(TMP_SESSION.getId())) {
          this.loggedIn(false);
          this.goTo(MEMBERSHIP_PAGES[1]);
        } else {
          this.loggedIn(true);
          this.goTo(MEMBERSHIP_PAGES[0]);
        }
      },
      loggedIn(boo) {
        this.loggedInBoo = boo;
      },
      sysMngrCheck() {
        if (!SCRIPT_VALIDATOR.nullCheck(TMP_SESSION.getLoginUser()?.sysMngrYn)) {
          return false;
        }
        return TMP_SESSION.getLoginUser().sysMngrYn;
      }
      // ************************************************************
      // Login Icon Clicked + Session check
      // ************************************************************
    }
  };
</script>
