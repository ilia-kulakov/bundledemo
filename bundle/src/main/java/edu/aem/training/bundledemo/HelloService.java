package edu.aem.training.bundledemo;

import edu.aem.training.bundledemo.impl.HelloServiceImpl;
import org.apache.felix.scr.annotations.Property;

/**
 * A simple service interface
 */
public interface HelloService {

    /**
     * @return the name of the underlying JCR repository implementation
     */
    String getRepositoryName();

    String[] getGreeting();

    Long getCount();
}