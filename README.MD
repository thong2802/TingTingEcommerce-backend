###TingTingECommerce use tech stack:
1. Backend : Springboot
    1. use Jpa
        1. test database: use com.h2database test data save in memory
           ```` 
           <dependency>
                <groupId>com.h2database</groupId>
                <artifactId>h2</artifactId>
                <scope>runtime</scope>
           </dependency> 
    2. Lists api
       1. Category
           1. Create category API
           2. get list category API
           3. update category API
       2. Product
           4. add product to category API
           5. get list product in category API
           6. update product API
       3. User
          1. get all user
          2. user signUp
          3. user signIn
       4. cart
          1. getCartItem
          2. addToCart
          3. deleteCartItem
          4. updateCartItem
       5. Doing.....
    3. Database
        1. Mysql
            1. mysq -u root -p
            2. show databases;
            3. create databases ecommerce;
            4. use ecommerce;
            5. show tables;
            6. show create table categories;

2. Fontend : Vuejs
3. Use Swagger create documment Api.
    1. https://viblo.asia/p/co-ban-ve-swagger-oOVlYjOV58W
    ````
        
    <dependency>
      <groupId>io.springfox</groupId>
      <artifactId>springfox-bean-validators</artifactId>
      <version>2.9.2</version>
    </dependency>

    <dependency>
      <groupId>io.springfox</groupId>
      <artifactId>springfox-swagger2</artifactId>
      <version>2.9.2</version>
    </dependency>

    <dependency>
      <groupId>io.springfox</groupId>
      <artifactId>springfox-swagger-ui</artifactId>
      <version>2.9.2</version>
    </dependency>
   ````
   ii.  We also have to modify our application.properties file by adding the lines
    1. ````
            spring.datasource.url=jdbc:h2:mem:testdb
            spring.datasource.driverClassName=org.h2.Driver
            spring.datasource.username=sa
            spring.datasource.password=password
            spring.jpa.database-platform=org.hibernate.dialect.H2Dialect  
       ````

    2. Test Api
        + Post : /category/create
            + ![postCategory](README_assets/testApiPostCategory.png)
            + ![postCategory](README_assets/testApiPostCategory1.png)
        + Get  : /category/list
            + ![getCategory](README_assets/testApiGetCategory1.png)
            + ![gettCategory](README_assets/testApiGetCategory2.png)