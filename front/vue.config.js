module.exports = {
    runtimeCompiler: true,
    devServer: {
        proxy: {
            '^/api': {  // api로 시작하는 것들...
                target: 'http://localhost:8888', //back 의 포트
                changeOrigin: true,
                ws: true
            }
        }
    }
};

// Axios 로 api 요청을 보낼 시, 받을 주소와 proxy 설정 (통신 설정)
// back 에서도 통신 설정 필요


