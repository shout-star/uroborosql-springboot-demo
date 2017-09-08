# uroboroSQL PetClinic Sample Application

<img src="https://future-architect.github.io/uroborosql-doc//images/logo.png" style="max-width: 600px;" alt="uroboroSQL" />

This is [uroboroSQL](https://github.com/future-architect/uroborosql) and Spring boot sample application.  
We developed uroboroSQL as a sample to use in web application.

This sample was inspired by [Spring PetClinic](https://github.com/spring-projects/spring-petclinic) and made.

In order to have you learn how to use uroboroSQL, some functions are added.

## Running locally

### From source

```
$ git clone https://github.com/shout-star/uroborosql-springboot-demo
$ cd uroborosql-springboot-demo
$ ./mvnw spring-boot:run
```

### From releases

```
$ java -jar uroborosql-petclinic-0.2.0.jar
```

You can then access petclinic here: http://localhost:8090/

![PetClinic](https://github.com/shout-star/uroborosql-springboot-demo/blob/master/doc/petclinic_top.jpg)

### Development running

```
$ cd client
  
# Running web server at port 8091.
$ npm run dev
  
$ cd ..
  
# Running rest server at port 8090.
$ ./mvnw spring-boot:run
```
You can then access petclinic here: http://localhost:8091/

## Database configuration

In its default configuration, Petclinic uses an H2 database.

### Prepare database schema and initial data

#### Auto

If `petclinic.db-auto-init=true` is set,
`setup/schema.sql` and `setup/data.sql` are executed at startup.

#### Manual

```
$ cd uroborosql-springboot-demo
$ ./mvnw -PREPL
  
uroborosql > update setup/schema
uroborosql > update setup/data
```


## Architecture

### Requirement
-	Java 1.8 or later.

### Backend
- [uroboroSQL](https://github.com/future-architect/uroborosql)
- [SpringBoot](https://github.com/spring-projects/spring-boot)
- [H2 Database](http://www.h2database.com/)

### Frontend

#### JavaScript Libraries
- [Vue.js](https://github.com/vuejs/vue)
- [vue-router2](https://github.com/vuejs/vue-router/)
- [VeeValidate](https://github.com/baianat/vee-validate)
- [v-tooltip](https://github.com/Akryum/v-tooltip)
- [vuejs-datepicker](https://github.com/charliekassel/vuejs-datepicker)
- [axios](https://github.com/mzabriskie/axios)

#### CSS
- [Material Components for the web](https://github.com/material-components/material-components-web)

## License

Released under the [MIT License](https://github.com/shout-star/uroborosql-springboot-demo/blob/master/LICENSE).

Copyright (c) 2017 KENICHI HOSHI.