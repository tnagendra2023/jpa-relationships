package com.java.jpa.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDTORequest {
    private String userName;
    private List<AddressDTORequest> address;
}
