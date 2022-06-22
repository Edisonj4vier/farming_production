package com.farming_production.farming_production.dto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NewUserDTO {
    
    private String name;
    private String lastname;
    private String email;
    private String password;
    private String role;
}
