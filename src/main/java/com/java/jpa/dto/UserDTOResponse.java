package com.java.jpa.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserDTOResponse {
    private String userName;
    private List<String> street;
}
