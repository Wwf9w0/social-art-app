package com.twitter.service.util;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserUtil {

    public boolean isUUID(String uuid){
        try {
            UUID.fromString(uuid);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public UUID convertStringToUUID(String uuid){
        return isUUID(uuid) ? UUID.fromString(uuid) : null;
    }
}
