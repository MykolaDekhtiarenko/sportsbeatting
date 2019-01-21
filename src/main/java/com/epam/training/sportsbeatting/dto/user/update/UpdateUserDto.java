package com.epam.training.sportsbeatting.dto.user.update;

import com.epam.training.sportsbeatting.domain.Currency;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@Builder
public class UpdateUserDto {
    @NotBlank(message = "{user.validation.name.blank}")
    @Size(min = 2, max = 20, message = "{user.validation.name.size}")
    private String name;

    @NotBlank(message = "{user.validation.accountNumber.blank}")
    @Size(min = 16, max = 16, message = "{user.validation.accountNumber.size}")
    private String accountNumber;

    @Positive(message = "{user.validation.balance.positive}")
    private Integer balance;

    //    @Pattern(regexp = "UAN|USD|HUF|EUR", message = "{user.validation.currency.pattern}")
    private Currency currency;

    @NotNull(message = "{user.validation.dateOfBirth.blank}")
    private LocalDate dateOfBirth;
}
