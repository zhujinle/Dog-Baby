import { createApp } from 'vue'
import App from './App.vue'
import router from './router/Login.js'
// import './plugins/element.js'

const app = createApp(App)
app.use(router)
app.mount('#app')
