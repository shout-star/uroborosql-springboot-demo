<template>
  <div id="app">
    <header class="mdc-toolbar mdc-toolbar--fixed">
      <div class="mdc-toolbar__row">
        <section class="mdc-toolbar__section mdc-toolbar__section--align-start">
          <a href="#" class="material-icons mdc-toolbar__icon--menu">menu</a>
          <i class="material-icons mdc-toolbar__icon">pets</i>
          <router-link to="/">
            <img id="logo" src="./assets/logo.png">
          </router-link>
        </section>
        <section class="mdc-toolbar__section mdc-toolbar__section--align-end" role="toolbar">
          <router-link to="/" class="mdc-toolbar__icon">
            <span class="material-icons" aria-label="Home" alt="Home" v-tooltip="'Home'">home</span>
            <span class="menu-text">Home</span>
          </router-link>
          <router-link to="/owners" class="mdc-toolbar__icon">
            <span class="material-icons" aria-label="Find owners" alt="Find owners"
                  v-tooltip="'Find owners'">search</span>
            <span class="menu-text">Find owners</span>
          </router-link>
          <router-link to="/vets" class="mdc-toolbar__icon">
            <span class="material-icons" aria-label="Veterinarians" alt="Veterinarians"
                  v-tooltip="'Veterinarians'">list</span>
            <span class="menu-text">Veterinarians</span>
          </router-link>
          <router-link to="/oups" class="mdc-toolbar__icon">
            <span class="material-icons" aria-label="Error" alt="Error" v-tooltip="'Error'">warning</span>
            <span class="menu-text">Error</span>
          </router-link>
        </section>
      </div>
      <aside class="mdc-temporary-drawer mdc-typography">
        <nav class="mdc-temporary-drawer__drawer">
          <header class="mdc-temporary-drawer__header">
            <div class="mdc-temporary-drawer__header-content">
              Menu
            </div>
          </header>
          <nav id="icon-with-text-demo" class="mdc-temporary-drawer__content mdc-list">
            <router-link class="mdc-list-item" to="/">
              <i class="material-icons mdc-list-item__start-detail" aria-hidden="true">home</i>Home
            </router-link>
            <router-link class="mdc-list-item" to="/owners">
              <i class="material-icons mdc-list-item__start-detail" aria-hidden="true">search</i>Find owners
            </router-link>
            <router-link class="mdc-list-item" to="/vets">
              <i class="material-icons mdc-list-item__start-detail" aria-hidden="true">list</i>Veterinarians
            </router-link>
            <router-link class="mdc-list-item" to="/oups">
              <i class="material-icons mdc-list-item__start-detail" aria-hidden="true">warning</i>Error
            </router-link>
          </nav>
        </nav>
      </aside>
      <pre-loader ref="preLoader"></pre-loader>
    </header>
    <main class="mdc-toolbar-fixed-adjust">
      <router-view></router-view>
    </main>
    <footer>
      <sql-log-frame ref="sqlLogFrame"></sql-log-frame>
    </footer>
  </div>
</template>

<script>
  import { drawer } from 'material-components-web'
  import PreLoader from './components/parts/PreLoader.vue'
  import SqlLogFrame from './components/parts/SqlLogFrame.vue'

  export default {
    components: {
      PreLoader,
      SqlLogFrame
    },
    name: 'app',
    mounted () {
      this.$refs.preLoader.show()
      setTimeout(() => (this.$refs.preLoader.hide()), 500)
      let mdcDrawer = new drawer.MDCTemporaryDrawer(document.querySelector('.mdc-temporary-drawer'))
      this.$el.querySelector('.mdc-toolbar__icon--menu').addEventListener('click', () => (mdcDrawer.open = true))
    }
  }
</script>

<style>
  #app {
    font-family: "游ゴシック", YuGothic, "ヒラギノ角ゴ Pro", "Hiragino Kaku Gothic Pro", "メイリオ", "Meiryo", sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    color: #2c3e50;
    margin-top: 60px;
  }

  #logo {
    height: 50px;
  }

  main {
    margin-left: auto;
    margin-right: auto;
    width: 90%;
  }

  .mdc-toolbar__icon--menu {
    display: none;
  }

  .mdc-temporary-drawer__header {
    background-color: var(--mdc-theme-primary, #3f51b5);
    height: 80px;
  }

  /* for tablet */
  @media only screen and (min-width: 601px) and (max-width: 992px) {
    .menu-text {
      display: none;
    }
  }

  /* for mobile */
  @media only screen and (max-width: 600px) {
    .menu-text {
      display: none;
    }
  }

  @media only screen and (max-width: 500px) {
    .mdc-toolbar__section--align-end {
      display: none;
    }

    .mdc-toolbar__icon--menu {
      display: inline-block;
    }
  }

</style>
