# club-front

## 信息

node版本为`v20.9.0`

npm版本为`v10.1.0`

使用端口为3100, 运行后[localhost:3100](localhost:3100)

由于生成函数的umijs/openapi只能生成ts，使用vue3+ts，js语法在ts都可使用，但ts语法检查更严格，能跑的报错都不是报错

如果在代码中发现`''+id`就是number转字符串

### 可能有问题的地方

- 表的数据校验
- 钱单位转化
- 社长看一个活动报名情况

### 依赖安装
```
npm install
```

### 运行
```
npm run serve
```

### Compiles and minifies for production
```
npm run build
```

### Lints and fixes files
```
npm run lint
```

### Customize configuration
See [Configuration Reference](https://cli.vuejs.org/config/).
