package com.zadaca.zadacaprojekt;

import com.zadaca.zadacaprojekt.dao.CarRepository;
import com.zadaca.zadacaprojekt.dao.UserRepository;
import com.zadaca.zadacaprojekt.domain.Permissions;
import com.zadaca.zadacaprojekt.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@ComponentScan(basePackages = "com.zadaca")
@SpringBootApplication(scanBasePackages = {
        "com.zadaca.zadacaprojekt.kafka"
})
public class ZadacaprojektApplication {


    @Autowired
    private UserRepository userManager;

    @Autowired
    private CarRepository carManager;


    public static void main(String[] args) {
        SpringApplication.run(ZadacaprojektApplication.class, args);

//        Properties properties = new Properties();
//
//        properties.setProperty("bootstrap.servers", "localhost:9092");
//        properties.setProperty("key.deserializer", StringDeserializer.class.getName());
//        properties.setProperty("value.deserializer", StringDeserializer.class.getName());
//
//
//        properties.setProperty("enable.auto.commit", "true");
//        properties.setProperty("group.id", "test");
//        properties.setProperty("auto.commit.interval.ms", "1000");
//        properties.setProperty("auto.offset.reset", "earliest");
//
//        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<String, String>(properties);
//        kafkaConsumer.subscribe(Arrays.asList("cars"));
//
//        List<ConsumerRecord<String, String>> consumerRecordList;
//        consumerRecordList = new ArrayList<>();
//        while (true) {
//            ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(100);
//            for (ConsumerRecord<String, String> cr : consumerRecords) {
//                ConsumerRecord<String, String> consumerRecord = new ConsumerRecord<>(cr.topic(), cr.partition(), cr.offset(), cr.key(), cr.value());
//
//                System.out.println("Received Message: " + consumerRecord.value());
//
//                consumerRecordList.add(consumerRecord);
//            }
//        }
    }



    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @PostConstruct
    private void onInit() {
        UserDetails admin = userManager.findByUsername("admin");

        String password = "1234";

        String encodedPassword = bCryptPasswordEncoder().encode(password);

        if (admin == null) {
            User adminUser = new User(null, "admin", encodedPassword, encodedPassword, Arrays.asList(Permissions.values()), null);
            userManager.save(adminUser);
        }
    }


}