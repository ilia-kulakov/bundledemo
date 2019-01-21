package edu.aem.training.bundledemo.impl;

import javax.jcr.Repository;

import org.apache.felix.scr.annotations.*;
import org.apache.sling.commons.osgi.PropertiesUtil;
import org.apache.sling.jcr.api.SlingRepository;

import edu.aem.training.bundledemo.HelloService;
import org.osgi.service.component.ComponentContext;

import java.util.Dictionary;

/**
 * One implementation of the {@link HelloService}. Note that
 * the repository is injected, not retrieved.
 */
@Service(HelloService.class)
@Component(immediate=true, metatype = true)
public class HelloServiceImpl implements HelloService {

    public static final String DEFAULT_GREETING = "Hi from Java class";

    public static final String PN_GREETING = "aem.training.greeting";

    @Property(name=PN_GREETING, label = "Greeting",  value = "Hello")
    private static String[] greeting;

    public static final String PN_COUNT = "aem.training.count";

    @Property(name=PN_COUNT, label = "Count",  value = "10000000")
    private static Long count;

    @Reference
    private SlingRepository repository;

    public String getRepositoryName() {
        return repository.getDescriptor(Repository.REP_NAME_DESC);
    }

    @Activate
    @Modified
    protected void onChangeProperties(ComponentContext context) {
        Dictionary properties = context.getProperties();
        greeting = PropertiesUtil.toStringArray(
                properties.get(PN_GREETING), new String[] { DEFAULT_GREETING } );
        count = PropertiesUtil.toLong(
                properties.get(PN_COUNT), 77);
    }

    public String[] getGreeting() {
        return greeting;
    }

    public Long getCount() {
        return count;
    }
}
