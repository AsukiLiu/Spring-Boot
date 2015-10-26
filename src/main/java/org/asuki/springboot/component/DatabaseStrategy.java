package org.asuki.springboot.component;

import org.asuki.springboot.qualifier.CustomQualifier;
import org.springframework.stereotype.Component;

import static org.asuki.springboot.qualifier.CustomType.DATABASE;

@Component
@CustomQualifier(DATABASE)
public class DatabaseStrategy implements Strategiable {
}
