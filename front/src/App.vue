<template>
  <v-app>
    <v-app-bar
        app
        elevation="0"
        style="opacity: 80%;"
    >
      <v-btn @click="goTo('Home')"
             style="font-size: 30px; font-weight: 900"
             text>ODOR
      </v-btn>

      <v-spacer/>
      <v-spacer/>
      <v-spacer/>
      <v-spacer/>
      <v-spacer/>

      <MenuWithChildren
          :switch="forumMenuSwitch"
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
          :switch="forumMenuSwitch"
          :menu="system"
          menuName="system"
          keyCol="forumKey"
          labelCol="forumName"
          @goTo="goTo"
      />

      <v-spacer/>
      <v-spacer/>
      <v-spacer/>
      <v-spacer/>

      <v-btn
          @click="changeLocale"
          small
          elevation="0"
          style="margin-right: 10px;"
      >
        <v-icon
            v-if="locale === 'KO'"
            x-small
            color="#66afd9"
            class="elevation-1"
        >
          KOR
        </v-icon>
        <v-icon
            v-else
            x-small
            color="#d9d766"
            class="elevation-1"
        >
          ENG
        </v-icon>
      </v-btn>

      <v-btn v-if="loggedInBoo"
             @click="membershipClicked"
             color="#bdd4ff"
             small
             fab
             elevation="2"
      >
        <v-icon small>
          mdi-account-cog
        </v-icon>
      </v-btn>

      <v-btn v-else
             @click="membershipClicked"
             fab
             small
             color="#d9d9d9"
             class="mx-2"
             elevation="2"
      >
        <v-icon small>mdi-account-arrow-left-outline</v-icon>
      </v-btn>

    </v-app-bar>

    <Router
        :routing="routing"
        :forumKey="forumKey"
        :categoryId="categoryId"
        @goTo="goTo"
        ref="router"
        @loggedIn="loggedIn"
        :key="routing"
    />

    <v-footer>
      <!-- -->
    </v-footer>
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
  mounted() {
    this.setSystemLocale();
    this.routing = 'Home';
    this.getForumList();
  },
  watch: {
    routing() {
      window.scrollTo(0, 0);
      ENTER_ACTION();
      this.$router.push(this.routingToBumpyCase()).catch(() => {
      });
    },
    '$route'(to, from) {
      if (from.name === to.name) {
        return;
      }
      this.$refs.router.goTo(to.name);
    },
    loggedInBoo() {
      this.sysMngrYn = this.sysMngrCheck();
      this.setSystemLocale();
      this.getForumList();
      this.getSystemList();
    },
    locale() {
      this.setSystemLocale();
      this.routing = 'Home';
    }
  },
  data() {
    return {
      locale: window.navigator.language.indexOf('ko') > -1 ? 'KO' : 'ENG',
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
      system: [],
      forumMenuSwitch: ''
    };
  },
  methods: {
    routingToBumpyCase() {
      let routing = this.routing.split('');
      routing[0] = routing[0].toUpperCase();
      return routing.join('');
    },

    // Set System Language
    async setSystemLocale() {
      let locale = this.locale;
      await axios.post(
          API.CommonController.setSystemLocale,
          null,
          {params: {locale}}
      ).then(() => {
        this.$i18n.locale = locale;
        SCRIPT_VALIDATOR.locale = locale;
        this.setMessageList();
      });
    },
    changeLocale() {
      this.locale = this.locale === 'KO' ? 'ENG' : 'KO';
    },
    // Set System Message
    async setMessageList() {
      await axios.get(
          API.CommonController.getMessageList
      ).then(res => {
        MESSAGE.setMessageList(res.data);
        this.setCodeList();
      });
    },
    // Set System Code
    async setCodeList() {
      await axios.get(
          API.CommonController.getCodeList
      ).then(res => {
        CODE.setCodeList(res.data);
      });
    },
    // get system list
    async getSystemList() {
      if (this.system.length > 0 && this.loggedInBoo && this.sysMngrYn) {
        return;
      }
      if (!this.loggedInBoo || !this.sysMngrYn) {
        this.system = [];
        return;
      }
      await axios.post(
          API.ForumMgmtController.getSystemList,
          TMP_SESSION.getLoginUser()
      ).then(res => {
        this.system = res.data.length > 0 ? res.data : [];
      });
    },
    // get forum list
    getForumList() {
      axios.post(
          API.ForumMgmtController.getForumList,
          TMP_SESSION.getLoginUser()
      )
          .then(res => {
            this.forum = res.data.length > 0 ? res.data : [];
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
      this.set2DepthMenuSwitch(page, this.categoryId);
      this.routing = page;
    },
    set2DepthMenuSwitch(page, categoryId) {
      this.forumMenuSwitch = categoryId;

      if (page === 'Home') {
        this.forumMenuSwitch = '';
      }
    },
    // ************************************************************
    // Login Icon Clicked + Session check
    // ************************************************************
    membershipClicked() {
      if (MEMBERSHIP_PAGES.some(v => v === this.routing)) return;

      if (!SCRIPT_VALIDATOR.nullCheck(TMP_SESSION.getId())) {
        this.loggedIn(false);
        this.goTo(MEMBERSHIP_PAGES[1], this.forumKey, this.categoryId);
      } else {
        this.loggedIn(true);
        this.goTo(MEMBERSHIP_PAGES[0], this.forumKey, this.categoryId);
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
