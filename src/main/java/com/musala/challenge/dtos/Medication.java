package com.musala.challenge.dtos;


import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.Arrays;
import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Medication {
    @Pattern(regexp = "^[a-zA-Z0-9_-]+$", message = "Only letters, numbers, '-', and '_' are allowed")
    private Long id;
    @Pattern(regexp = "^[A-Z0-9_]+$", message = "Only upper case letters, underscore, and numbers are allowed")
    private String code;

    private String weight;

    private byte[] image;

    private Drone drone;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Medication that = (Medication) o;
        return Objects.equals(id, that.id) && Objects.equals(weight, that.weight) && Objects.equals(code, that.code) && Arrays.equals(image, that.image);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, weight, code);
        result = 31 * result + Arrays.hashCode(image);
        return result;
    }
}
