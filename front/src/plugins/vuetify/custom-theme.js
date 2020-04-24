// vuetify 기본 제공 컬러색상 값들
import colors from "vuetify/lib/util/colors";

export default {
    primary: {
        // colors 내부 색깔 가져올수 있음.
        // 그냥 RGB값 넣어도 됨
        base: '#595959',
        darken1: colors.purple.darken2,
        // darken2: colors.purple.darken2,
        // darken3: colors.purple.darken2,
        // darken4: colors.purple.darken2,
        // lighten1: colors.purple.darken2,
        // lighten2: colors.purple.darken2,
        // lighten3: colors.purple.darken2,
        // lighten4: colors.purple.darken2,
        // lighten5: colors.purple.darken2
    },
    // 세부적으로 안바꾸고 그냥 색깔 막 바꿔도 됨
    secondary: colors.indigo,

    // 새로운 이름으로 attr 생성가능
    newColor: colors.pink.base
}