import { route } from 'quasar/wrappers';
import { useAuthStore } from 'src/stores/auth';
import {
  createMemoryHistory,
  createRouter,
  createWebHashHistory,
  createWebHistory,
} from 'vue-router';

import routes from './routes';

export default route(function ( /* { store, ssrContext } */) {
  const createHistory = process.env.SERVER
    ? createMemoryHistory
    : (process.env.VUE_ROUTER_MODE === 'history' ? createWebHistory : createWebHashHistory);

  const Router = createRouter({
    scrollBehavior: () => ({ left: 0, top: 0 }),
    routes,
    history: createHistory(process.env.VUE_ROUTER_BASE),
  });

  Router.beforeEach(async (to, from, next) => {
    const authStore = useAuthStore();
    if (to.path !== '/login' && to.path !== '/register/driver' && to.path !== '/register/manager' && !authStore.isAuth) {
      next('login');
    } else {
      next();
    }
  })

  return Router;
});
