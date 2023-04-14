package etsii.tfg.DungeonRaiders.user;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import etsii.tfg.DungeonRaiders.model.BaseEntity;
import etsii.tfg.DungeonRaiders.validation.BasicInfo;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(unique = true)
    @NotEmpty(groups = BasicInfo.class)
    @Length(min = 3, max = 20, message = "El nombre de usuario debe tener un tamaño entre 3 y 20 caracteres")
    private String username;

    @Size(min = 5, message = "La contraseña debe tener un tamaño superior a 5 caracteres")
    private String password;

    public void setDecryptedPassword(String newPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(newPassword);
        setPassword(encodedPassword);
    }

}
