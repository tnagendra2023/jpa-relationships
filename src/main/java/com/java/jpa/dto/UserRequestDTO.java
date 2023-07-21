package com.java.jpa.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserRequestDTO {
    private String userName;
    private List<AddressRequestDTO> address;
}
