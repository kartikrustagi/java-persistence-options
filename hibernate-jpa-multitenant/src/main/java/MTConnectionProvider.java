import org.hibernate.c3p0.internal.C3P0ConnectionProvider;
import org.hibernate.engine.jdbc.connections.spi.AbstractMultiTenantConnectionProvider;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.service.spi.ServiceRegistryAwareService;
import org.hibernate.service.spi.ServiceRegistryImplementor;

import java.util.HashMap;
import java.util.Map;

public class MTConnectionProvider extends AbstractMultiTenantConnectionProvider implements ServiceRegistryAwareService {

    /*
    The second approach is a pull approach where the service implements the optional service interface org.hibernate.service.spi.ServiceRegistryAwareService
    which declares a single injectServices method. During startup, Hibernate will inject the org.hibernate.service.ServiceRegistry itself into services
    which implement this interface. The service can then use the ServiceRegistry reference to locate any additional services it needs.
    */

    private ServiceRegistryImplementor serviceRegistry = null;

    private Map<String, String> getDummyPropMap() {
        Map<String, String> prop = new HashMap<String, String>();
        prop.put("hibernate.connection.driver_class", "org.hsqldb.jdbcDriver");
        prop.put("hibernate.connection.username", "sa");
        prop.put("hibernate.connection.password", "");
        prop.put("hibernate.hbm2ddl.auto", "create");
        prop.put("hibernate.show_sql", "true");
        prop.put("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
        return prop;
    }

    protected ConnectionProvider getAnyConnectionProvider() {
        C3P0ConnectionProvider connectionProvider = new C3P0ConnectionProvider();
        connectionProvider.injectServices(serviceRegistry);
        Map<String, String> prop = getDummyPropMap();
        prop.put("hibernate.connection.url", "jdbc:hsqldb:mem:default");
        connectionProvider.configure(prop);
        return connectionProvider;
    }

    protected ConnectionProvider selectConnectionProvider(String tenantId) {
        C3P0ConnectionProvider connectionProvider = new C3P0ConnectionProvider();
        connectionProvider.injectServices(serviceRegistry);
        Map<String, String> prop = getDummyPropMap();
        prop.put("hibernate.connection.url", ("jdbc:hsqldb:mem:" + tenantId));
        connectionProvider.configure(prop);
        return connectionProvider;
    }

    public void injectServices(ServiceRegistryImplementor serviceRegistry) {
        this.serviceRegistry = serviceRegistry;

    }
}
