Resources:
* https://dzone.com/articles/multi-tenancy-using-jpa-spring-and-hibernate-part
* https://stackoverflow.com/questions/21223894/manage-connection-pooling-in-multi-tenant-web-app-with-spring-hibernate-and-c3p
* http://www.mchange.com/projects/c3p0/#using_combopooleddatasource
* http://docs.jboss.org/hibernate/orm/4.1/devguide/en-US/html_single/

We can either use MultiTenantConnectionProvider or we can use MultiTenantDataSource as mentioned in the Dzone link. Creating c3p0  Data source is mentioned in the mchange link above while a poor implementation (no where close to propduction) is used here in the code base.
