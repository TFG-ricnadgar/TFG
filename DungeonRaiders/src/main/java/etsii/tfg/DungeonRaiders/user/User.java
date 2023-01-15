package etsii.tfg.DungeonRaiders.user;

import javax.validation.constraints.NotEmpty;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import etsii.tfg.DungeonRaiders.model.BaseEntity;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(unique = true)
    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

}
