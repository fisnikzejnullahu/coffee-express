<template>
  <div id="notfound">
    <div class="notfound">
      <div class="notfound-404">
        <h1>:(</h1>
      </div>
      <h2>Sorry for inconvenience</h2>
      <span>
        {{ message }}
      </span>
      <p style="margin-top: 20px">
        If you continue to have this problem please contact us!
      </p>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    message: String,
  },
};
</script>

<style>
* {
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
}

body {
  padding: 0;
  margin: 0;
}

#notfound {
  position: relative;
  height: 100vh;
}

#notfound .notfound {
  position: absolute;
  left: 50%;
  top: 50%;
  -webkit-transform: translate(-50%, -50%);
  -ms-transform: translate(-50%, -50%);
  transform: translate(-50%, -50%);
}

.notfound {
  max-width: 710px;
  width: 100%;
  padding-left: 190px;
  line-height: 1.4;
}

.notfound .notfound-404 {
  position: absolute;
  left: 0;
  top: 0;
  width: 150px;
  height: 150px;
}

.notfound .notfound-404 h1 {
  color: #00b5c3;
  font-size: 140px;
  letter-spacing: 15.5px;
  margin: 0px;
  font-weight: 900;
  position: absolute;
  top: 50%;
  -webkit-transform: translate(-50%, -50%);
  -ms-transform: translate(-50%, -50%);
  transform: translate(-50%, -50%);
}

.notfound h2 {
  font-size: 28px;
  font-weight: 700;
  text-transform: uppercase;
  letter-spacing: 2.5px;
  margin-top: 0;
}

.notfound p {
  font-size: 14px;
  font-weight: 400;
  margin-top: 0;
  margin-bottom: 15px;
}

.notfound a {
  font-size: 14px;
  text-decoration: none;
  text-transform: uppercase;
  background: #c49b63;
  border: 1px solid #c49b63;
  color: #000;
  display: inline-block;
  padding: 15px 30px;
  border-radius: 40px;
  /* color: #292929; */
  font-weight: 700;
  -webkit-box-shadow: 0px 4px 15px -5px rgba(0, 0, 0, 0.3);
  box-shadow: 0px 4px 15px -5px rgba(0, 0, 0, 0.3);
  -webkit-transition: 0.2s all;
  transition: 0.2s all;
}

.notfound a:hover {
  color: #c49b63;
  background-color: transparent;
}

@media only screen and (max-width: 480px) {
  .notfound {
    text-align: center;
  }
  .notfound .notfound-404 {
    position: relative;
    width: 100%;
    margin-bottom: 15px;
  }
  .notfound {
    padding-left: 15px;
    padding-right: 15px;
  }
}
</style>