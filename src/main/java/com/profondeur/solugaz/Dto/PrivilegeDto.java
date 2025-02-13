package com.profondeur.solugaz.Dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.profondeur.solugaz.Model.Privilege;
import com.profondeur.solugaz.Model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PrivilegeDto {
    private Long id;

    private String name;
    //@JsonIgnore
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<Role> roles;
    public static PrivilegeDto fromEntity(Privilege privilege) {
        if(privilege==null) {
            return PrivilegeDto.builder()
                    .id(privilege.getId())
                    .name(privilege.getName())
                    .roles(privilege.getRoles().stream().toList())
                    .build();
        }
        return null;
    }

    public static Privilege toEntity(PrivilegeDto privilegeDto) {
        if(privilegeDto==null) {
            return null;
        }
        Privilege privilege = new Privilege();
        privilege.setId(privilegeDto.getId());
        privilege.setName(privilegeDto.getName());
        privilege.setRoles(privilegeDto.getRoles()
                .stream().toList());
        return privilege;
    }
}
