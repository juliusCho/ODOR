module.exports = {
  "runtimeCompiler": true,
  "devServer": {
    "proxy": {
      "^/odor": {
        "target": "http://localhost:8888",
        "changeOrigin": true,
        "ws": true
      }
    }
  },
  "transpileDependencies": [
    "vuetify"
  ]
}