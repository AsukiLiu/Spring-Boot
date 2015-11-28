package org.asuki.springboot.component;

import org.asuki.springboot.qualifier.CustomQualifier;
import org.springframework.stereotype.Component;

import static org.asuki.springboot.qualifier.CustomType.DATABASE;
import static org.asuki.springboot.qualifier.CustomType.FILE;

public interface Strategiable {
    @Component
    @CustomQualifier(FILE)
    class FileStrategy implements Strategiable {
    }

    @Component
    @CustomQualifier(DATABASE)
    class DatabaseStrategy implements Strategiable {
    }
}
