package com.dft.bigcommerce.model.customer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Customer {

    public Integer id;
    public Authentication authentication;
    public String company;
    public Integer customerGroupId;
    public String email;
    public String firstName;
    public String lastName;
    public String notes;
    public String phone;
    public String registrationIpAddress;
    public String taxExemptCategory;
    public String dateCreated;
    public String dateModified;
    public Boolean acceptsProductReviewAbandonedCartEmails;
    public Integer originChannelId;
    public Object channelIds;
    public Consent consent;
}
