package org.example.entity;

import lombok.*;
import org.example.enums.GeneralStatus;
import org.example.enums.Role;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AuthUser {

    private Integer id;
    private String name;
    private String username;
    private String password;

    private Role role;

    private GeneralStatus status;

    private Boolean visible = Boolean.FALSE;


}
