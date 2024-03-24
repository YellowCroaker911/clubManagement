const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  lintOnSave:false,   //关闭语法检查
  devServer: {
    port: 3100,
    proxy: {
      "/api": {//地址中以api开头的
        target: "http://localhost:3000", //转发地址
        ws: true, //代理 websockets
        changeOrigin: true,//允许跨域
        pathRewrite: {//对象/函数，重写目标的url路径。对象键将用作RegExp以匹配路径。
          '^/api' : ''
        }
      },
      // "/foo": {//地址中以foo开头的
      //   target: "<other_url>",//转发地址
      // },
    },
  },

  // configureWebpack: {
  //   resolve: { extensions: [".ts", ".tsx", ".js", ".json"] },
  //   module: {
  //     rules: [
  //       {
  //         test: /.tsx?$/,
  //         loader: 'ts-loader',
  //         exclude: /node_modules/,
  //         options: {
  //           appendTsSuffixTo: [/.vue$/],
  //         }
  //       }
  //     ]
  //   }
  // }
})