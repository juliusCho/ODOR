module.exports = {
    runtimeCompiler: true,
    devServer: {
        proxy: {
            '^/api': {
                target: 'http://localhost:8888',
                changeOrigin: true,
                ws: true
                // pathRewrite: {
                //     '^/api': ''
                // }
            }
        }
    }
}