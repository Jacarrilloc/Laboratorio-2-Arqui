package co.edu.javeriana.as.personapp.mapper;

import co.edu.javeriana.as.personapp.common.annotations.Mapper;
import co.edu.javeriana.as.personapp.domain.Person;
import co.edu.javeriana.as.personapp.model.request.PersonaRequest;
import co.edu.javeriana.as.personapp.model.response.PersonaResponse;

@Mapper
public class PersonaMapperRest {

	public PersonaResponse fromDomainToAdapterRestMaria(Person person) {
		return fromDomainToAdapterRest(person, "MariaDB");
	}

	public PersonaResponse fromDomainToAdapterRestMongo(Person person) {
		return fromDomainToAdapterRest(person, "MongoDB");
	}

	private PersonaResponse fromDomainToAdapterRest(Person person, String database) {
		return new PersonaResponse(
				String.valueOf(person.getIdentification()),
				person.getFirstName(),
				person.getLastName(),
				String.valueOf(person.getAge()),
				person.getGender().toString(),
				database,
				"OK"
		);
	}

	public Person fromAdapterToDomain(PersonaRequest request) {
		// TODO: Implement conversion from PersonaRequest to Person domain entity
		return null;
	}
}
