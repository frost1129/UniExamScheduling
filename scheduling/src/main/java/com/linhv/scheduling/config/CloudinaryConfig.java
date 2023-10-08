package com.linhv.scheduling.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary(){
        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "dbh8vdpi7",
                    "api_key", "969248372785547",
                    "api_secret", "wdTFP7kuj5kCBP0Q92HLPwOmBME",
                    "secure", true
        ));
        return cloudinary;
    }
}
