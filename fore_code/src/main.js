import Vue from 'vue'
import App from './App.vue'
import router from './router/index'
import ElementUI from 'element-ui';
import './assets/css/global.css'
import'./plugins/element.js'

Vue.config.productionTip = false

new Vue({
	router,
  render: h => h(App),
}).$mount('#app')
