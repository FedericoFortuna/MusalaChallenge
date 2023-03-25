package com.musala.challenge.dtos;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.musala.challenge.exceptions.NullFieldException;
import com.musala.challenge.exceptions.RegexCodeMedicationException;
import com.musala.challenge.exceptions.RegexIdMedicationException;
import com.musala.challenge.utils.RegexMedications;
import lombok.*;

import java.util.Arrays;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Medication {

    private String id;

    private String code;

    private Double weight;

    private byte[] image;
    @JsonIgnore
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

    public void validate() {
        if (this.getId() == null || this.getCode() == null || this.getWeight() == null) {
            throw new NullFieldException();
        }

        Pattern regexId = Pattern.compile(RegexMedications.REGEX_ID);
        Matcher matcherId = regexId.matcher(this.getId());
        if(!matcherId.matches()){
            throw new RegexIdMedicationException();
        }

        Pattern regexCode = Pattern.compile(RegexMedications.REGEX_CODE);
        Matcher matcherCode = regexCode.matcher(this.getCode());
        if(!matcherCode.matches()){
            throw new RegexCodeMedicationException();
        }

    }

    public void droneLink(Drone drone) {
        this.drone = com.musala.challenge.dtos.Drone.builder()
                .number(drone.getNumber())
                .build();
    }
}
