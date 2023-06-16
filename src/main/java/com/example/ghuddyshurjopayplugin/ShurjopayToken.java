package com.example.ghuddyshurjopayplugin;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Accessors(chain = true)
public class ShurjopayToken implements Serializable {
    private static final long serialVersionUID = 1960827183789531737L;

    /** JWT token to authenticate merchant. */
    private String token;

    /** Secret Payment URL by which customer can pay. */
    @JsonProperty("execute_url")
    private String spPaymentApi;

    /** JWT token type. i.e. Bearer, Basic etc. */
    @JsonProperty("token_type")
    private String tokenType;

    /** ShurjoPay reserved status code.*/
    @JsonProperty("sp_code")
    private String spStatusCode;

    /** JWT token created time. Shurjopay provides date time in BST time zone. */
    @JsonProperty("token_create_time")
    private String tokenCreatedTime;

    /** JWT token expired time in second. */
    @JsonProperty("expires_in")
    private Integer expiredTimeInSecond;

    /** Merchant's registered store id on shurjopay system. */
    @JsonProperty("store_id")
    private String storeId;

    /** Shurjopay status message.*/
    private String message;

    @JsonIgnore
    public String getFormatted() {
        return tokenType.concat(" ").concat(token);
    }
}