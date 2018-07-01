package net.corevalue.company.hierarchy.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Table(name = "top_managers")
@Entity
public class TopManager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @Column(name = "family_name")
    private String familyName;

    @NotNull
    @Column(name = "phone_number")
    private String phoneNumber;

    @NotNull
    private String role;

    @NotNull
    @Column(name = "role_description")
    private String roleDescription;
}
