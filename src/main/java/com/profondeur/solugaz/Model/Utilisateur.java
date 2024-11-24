package com.profondeur.solugaz.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Date;

@Table(name = "utilisateur")
@Entity @EqualsAndHashCode(callSuper=true)
@Data @NoArgsConstructor @AllArgsConstructor
public class Utilisateur extends AbstractEntity{
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(length = 50,name = "email")
    private String email;

    @Column(name="motdepasse")
    private String motDePasse;

    @Column(name="datedenaissance")
    private Date dateDeNaissance;

    @Column(length = 15)
    private String phone;

    @Column(name="imageurl")
    private String imageUrl;

    private Double Longitude;
    private Double latitude;
    private Double altitude;

    private boolean enabled;
    private boolean isLocked;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;

    @ManyToOne
    private Localisation localisation;
}
