module.exports = {
  "runtimeCompiler": true,
  "devServer": {
    "proxy": {
      "^/rest": {
        "target": "http://localhost:8888",
        "changeOrigin": true,
        "ws": true
      },
      "^/api": {
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