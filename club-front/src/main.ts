
import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import httpRequest from '@/api/httpRequest'

import '@/assets/icon/iconfont.css'
import '@/assets/base.scss'

const app = createApp(App)

app.config.globalProperties.$http = httpRequest;
app.use(ElementPlus);
app.use(store).use(router)
app.mount("#app")

