<template>
  <div v-if="menu.length > 0">
    <v-menu
        :offset-y="true"
        transition="scroll-y-transition"
        open-on-hover
    >
      <template v-slot:activator="{ on }">
        <v-btn v-on="on" small text>{{ menuName }}</v-btn>
      </template>
      <v-list
          style="max-height: 600px;"
      >
        <v-list-item
            v-for="sub in menu"
            :key="sub[keyCol]"
            link
            @click="goTo(sub)"
            :disabled="sub.disabled"
        >
          <v-list-item-title
              v-text="sub[labelCol]"
          />
        </v-list-item>
      </v-list>
    </v-menu>
  </div>
</template>

<script>
export default {
  name: 'MenuWithChildren',
  props: {
    menuName: {
      type: String,
      default: '',
      required: true
    },
    menu: {
      type: Array,
      default: () => {
        return [];
      },
      required: true
    },
    keyCol: {
      type: String,
      default: '',
      required: true
    },
    labelCol: {
      type: String,
      default: '',
      required: true
    },
    switch: {
      type: String,
      default: '',
      required: true
    }
  },
  data() {
    return {
      clickedMenu: 0
    }
  },
  methods: {
    goTo(menu) {
      if (
          this.clickedMenu === menu[this.keyCol] &&
          this.switch !== '' &&
          this.switch === menu.categoryId
      ) {
        return;
      }
      this.clickedMenu = menu[this.keyCol];

      if (menu.categoryId === 'SYSTEM') {
        this.handleSystem(menu);
      } else {
        this.handleForum(menu);
      }
    },
    handleSystem(menu) {
      this.$emit('goTo', menu.forumName);
    },
    handleForum(menu) {
      this.$emit('goTo',
          this.constructForumTypeCode(menu),
          menu.forumKey,
          menu.categoryId);
    },
    constructForumTypeCode(menu) {
      let forumTypeCode = menu?.forumTypeCode.toLowerCase();
      return forumTypeCode ||
          forumTypeCode.substring(0, 1).toUpperCase()
          + forumTypeCode.substring(1, forumTypeCode.length);
    }
  }
}
</script>
