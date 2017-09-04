import Vue from 'vue'
import Router from 'vue-router'
import HomePage from '@/components/HomePage'
import LoginPage from '@/components/LoginPage'
import OwnerFindPage from '@/components/OwnerFindPage'
import OwnerFormPage from '@/components/OwnerFormPage'
import OwnerShowPage from '@/components/OwnerShowPage'
import PetFormPage from '@/components/PetFormPage'
import VisitFormPage from '@/components/VisitFormPage'
import VetFindPage from '@/components/VetFindPage'
import ErrorPage from '@/components/ErrorPage'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomePage
    },
    {
      path: '/login',
      name: 'login',
      component: LoginPage
    },
    {
      path: '/owners',
      name: 'owner-find',
      component: OwnerFindPage
    },
    {
      path: '/owners/new',
      name: 'owner-new',
      component: OwnerFormPage
    },
    {
      path: '/owners/:id/edit',
      name: 'owner-edit',
      component: OwnerFormPage
    },
    {
      path: '/owners/:id',
      name: 'owner-show',
      component: OwnerShowPage
    },
    {
      path: '/owners/:id/pets/new',
      name: 'pet-new',
      component: PetFormPage
    },
    {
      path: '/owners/:id/pets/:petId/edit',
      name: 'pet-edit',
      component: PetFormPage
    },
    {
      path: '/owners/:id/pets/:petId/visits/new',
      name: 'visit-new',
      component: VisitFormPage
    },
    {
      path: '/vets',
      name: 'vet-find',
      component: VetFindPage
    },
    {
      path: '/oups',
      name: 'error',
      component: ErrorPage
    }
  ]
})
