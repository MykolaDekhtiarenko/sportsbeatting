package com.epam.training.sportsbeatting.dto.user.create;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@Builder
public class CreateUserDto {

    @NotBlank(message="{user.validation.name.blank}")
    @Size(min = 2, max = 20, message = "{user.validation.name.size}")
    private String name;

    @NotBlank(message = "{user.validation.email.blank}")
    @Email(message = "{user.validation.email.pattern}")
    private String email;

    @NotBlank(message = "{user.validation.password.blank}")
    @Size(min = 6, max = 20, message = "{user.validation.password.size}")
    private String password;

    @NotBlank(message = "{user.validation.accountNumber.blank}")
    @Size(min = 16, max = 16, message = "{user.validation.accountNumber.size}")
    private String accountNumber;

    @NotNull(message = "{user.validation.dateOfBirth.blank}")
    private LocalDate dateOfBirth;
}
