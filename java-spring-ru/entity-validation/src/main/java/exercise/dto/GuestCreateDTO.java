package exercise.dto;

import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;

// BEGIN
@Setter
@Getter
public class GuestCreateDTO {
    @Id
    private long id;

    @NotNull
    private String name;

    @Email
    private String email;

    @Pattern(regexp = "^\\+[0-9]{11,13}$")
    private String phoneNumber;

    @Size(min = 4, max = 4)
    private String clubCard;

    @FutureOrPresent
    private LocalDate cardValidUntil;

    @CreatedDate
    private LocalDate createdAt;
}
// END
