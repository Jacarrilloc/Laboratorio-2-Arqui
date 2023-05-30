package co.edu.javeriana.as.personapp.domain;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
@RequiredArgsConstructor
public class Study {
	@NonNull
	private Person person;
	@NonNull
	private Profession profession;
	private LocalDate graduationDate;
	private String universityName;
}
