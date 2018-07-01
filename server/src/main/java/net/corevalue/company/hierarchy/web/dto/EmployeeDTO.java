package net.corevalue.company.hierarchy.web.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

    private Long id;

    @NotNull
    private String firstName;

    @NotNull
    private String familyName;

    @NotNull
    private String phoneNumber;

    @NotNull
    private String position;
}
