package etsii.tfg.DungeonRaiders.user;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

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
    @Length(min = 3, max = 20)
    private String username;

    @NotEmpty
    private String password;

}
