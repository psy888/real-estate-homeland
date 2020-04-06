package com.psy.realestatehomeland;

import com.psy.realestatehomeland.model.Photo;
import com.psy.realestatehomeland.model.Property;
import com.psy.realestatehomeland.model.lib.PropertyType;
import com.psy.realestatehomeland.model.user.AppRole;
import com.psy.realestatehomeland.repository.AppRoleRepository;
import com.psy.realestatehomeland.repository.PropertyRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@AllArgsConstructor
public class RealEstateHomelandApplication {

    private PropertyRepository repository;
    private AppRoleRepository appRoleRepository;

    public static void main(String[] args) {
        SpringApplication.run(RealEstateHomelandApplication.class, args);
    }


    @Bean
    public CommandLineRunner runner() {
        return args -> {

            AppRole admin = new AppRole();
            admin.setRoleName("ADMINISTRATOR");
            AppRole agent = new AppRole();
            agent.setRoleName("AGENT");
            AppRole client = new AppRole();
            client.setRoleName("CLIENT");

            appRoleRepository.save(admin);
            appRoleRepository.save(agent);
            appRoleRepository.save(client);

//            for (int i = 0; i < 6; i++) {
//
//                Property p = new Property();
//                p.setPropertyType((i%2==0)?"commercial":"living");
//                p.setBedroomCnt(2);
//                p.setBathroomCnt(2);
//                p.setArea(105 + i);
//                p.setAddress("Address St. " + (int)(Math.random() * 100 + 1) + i);
//                p.setCity(((i%2==0)?"London":"Paris"));
//                p.setPrice(125000.0 + 1000*i);
//                p.setCurrencyCode("USD");
//                p.setIsPromoted((i<1)?Boolean.TRUE:Boolean.FALSE);
//                p.setIsFeatured((i>3)?Boolean.TRUE:Boolean.FALSE);
//                Photo photo = new Photo();
//                p.setMainPhoto(photo);
//                repository.save(p);
//            }

        };
    }
}
