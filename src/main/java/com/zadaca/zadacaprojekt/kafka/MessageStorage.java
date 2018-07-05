package com.zadaca.zadacaprojekt.kafka;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MessageStorage {

    private List<String> storage = new ArrayList<String>();

    public void put(String message){
        storage.add(message);
    }

    public String toString(){
        StringBuffer info = new StringBuffer();
        storage.forEach(msg->info.append(" (").append(msg).append(")  "));
        return info.toString();
    }

    public void clear(){
        storage.clear();
    }


}