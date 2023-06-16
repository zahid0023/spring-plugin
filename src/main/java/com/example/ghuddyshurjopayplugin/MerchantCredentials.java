package com.example.ghuddyshurjopayplugin;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

@Data
@Slf4j
@Configuration
public class MerchantCredentials {
    private String userName;
    private String userPassword;


    MerchantCredentials(){
        this.userName = "sp_sandbox";
        this.userPassword = "pyyk97hu&6u6";
    }
}
