package org.asuki.springboot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {
    @Cacheable(value = "userCache")
    public String getUserByName(String userName) {
        log.info("getUserByName was called");
        return userName;
    }

    @CachePut(value = "userCache")
    public String updateUser(String userName) {
        log.info("updateUser was called");
        return userName;
    }
}
