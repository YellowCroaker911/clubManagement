module.exports = {
    openApi: [
        {
            requestLibPath: "import request from '@/api/httpRequest'", // 想怎么引入封装请求方法
            schemaPath: 'http://localhost:3000/v3/api-docs/default', // openAPI规范地址
            projectName: 'backend-api', // 生成到哪个目录内
            apiPrefix: '"/api"',// 需不需要增加前缀
            serversPath: './src/api', // 生成代码到哪个目录
        }
    ]
}