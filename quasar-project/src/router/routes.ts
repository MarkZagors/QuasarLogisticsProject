import { RouteRecordRaw } from 'vue-router';

const routes: RouteRecordRaw[] = [
  {
    path: '/',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      { path: '', component: () => import('pages/IndexPage.vue') },
      { path: '/drivers', component: () => import('pages/DriverPage.vue') },
      { path: '/managers', component: () => import('pages/ManagerPage.vue') },
      { path: '/trucks', component: () => import('pages/TrucksPage.vue') },
      { path: '/checkpoints', component: () => import('pages/CheckpointsPage.vue') },
      { path: '/destinations', component: () => import('pages/DestinationsPage.vue') },
      { path: '/cargos', component: () => import('pages/CargosPage.vue') },
      { path: '/login', component: () => import('pages/LoginPage.vue') },
      { path: '/register/driver', component: () => import('pages/RegisterDriverPage.vue') },
      { path: '/register/manager', component: () => import('pages/RegisterManagerPage.vue') },
      { path: '/setDestinations', component: () => import('pages/SetDestinationPage.vue') },
      { path: '/report', component: () => import('pages/ReportPage.vue') },
      { path: '/forum', component: () => import('pages/ForumPage.vue') },
      { path: '/forum/:id', component: () => import('pages/ForumInsidePage.vue') },
    ],
  },

  // Always leave this as last one,
  // but you can also remove it
  {
    path: '/:catchAll(.*)*',
    component: () => import('pages/ErrorNotFound.vue'),
  },
];

export default routes;
