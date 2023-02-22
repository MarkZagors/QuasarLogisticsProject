import { defineStore } from 'pinia'

export const useAuthStore = defineStore('isAuth', {
  state: () => ({
    isAuth: false,
    authID: -1,
    authType: "null",
  }),
  getters: {
    getAuth: (state) => state.isAuth,
  },
  actions: {
    Authenticate() {
      this.isAuth = true;
    },
  },
})