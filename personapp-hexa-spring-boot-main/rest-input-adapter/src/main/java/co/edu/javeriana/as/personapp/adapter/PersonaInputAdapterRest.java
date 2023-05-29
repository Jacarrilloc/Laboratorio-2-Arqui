package co.edu.javeriana.as.personapp.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import co.edu.javeriana.as.personapp.application.usecase.PersonUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import co.edu.javeriana.as.personapp.application.port.in.PersonInputPort;
import co.edu.javeriana.as.personapp.application.port.out.PersonOutputPort;
import co.edu.javeriana.as.personapp.common.annotations.Adapter;
import co.edu.javeriana.as.personapp.common.exceptions.InvalidOptionException;
import co.edu.javeriana.as.personapp.common.setup.DatabaseOption;
import co.edu.javeriana.as.personapp.domain.Person;
import co.edu.javeriana.as.personapp.mapper.PersonaMapperRest;
import co.edu.javeriana.as.personapp.model.request.PersonaRequest;
import co.edu.javeriana.as.personapp.model.response.PersonaResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Adapter
@Component
public class PersonaInputAdapterRest {

	private final PersonInputPort personInputPort;
	private final PersonaMapperRest personaMapperRest;

	@Autowired
	public PersonaInputAdapterRest(@Qualifier("personOutputAdapterMaria") PersonOutputPort personOutputPortMaria,
								   @Qualifier("personOutputAdapterMongo") PersonOutputPort personOutputPortMongo,
								   PersonaMapperRest personaMapperRest) {
		this.personInputPort = new PersonUseCase(personOutputPortMaria);
		this.personOutputPortMaria = personOutputPortMaria;
		this.personOutputPortMongo = personOutputPortMongo;
		this.personaMapperRest = personaMapperRest;
	}

	private PersonOutputPort personOutputPortMaria;
	private PersonOutputPort personOutputPortMongo;

	private String setPersonOutputPortInjection(String dbOption) throws InvalidOptionException {
		if (dbOption.equalsIgnoreCase(DatabaseOption.MARIA.toString())) {
			return DatabaseOption.MARIA.toString();
		} else if (dbOption.equalsIgnoreCase(DatabaseOption.MONGO.toString())) {
			return DatabaseOption.MONGO.toString();
		} else {
			throw new InvalidOptionException("Invalid database option: " + dbOption);
		}
	}

	public List<PersonaResponse> historial(String database) {
		log.info("Into historial PersonaEntity in Input Adapter");
		try {
			setPersonOutputPortInjection(database);
			if (database.equalsIgnoreCase(DatabaseOption.MARIA.toString())) {
				return personInputPort.findAll().stream().map(personaMapperRest::fromDomainToAdapterRestMaria)
						.collect(Collectors.toList());
			} else {
				return personInputPort.findAll().stream().map(personaMapperRest::fromDomainToAdapterRestMongo)
						.collect(Collectors.toList());
			}
		} catch (InvalidOptionException e) {
			log.warn(e.getMessage());
			return new ArrayList<PersonaResponse>();
		}
	}

	public PersonaResponse crearPersona(PersonaRequest request) {
		try {
			setPersonOutputPortInjection(request.getDatabase());
			Person person = personInputPort.create(personaMapperRest.fromAdapterToDomain(request));
			return personaMapperRest.fromDomainToAdapterRestMaria(person);
		} catch (InvalidOptionException e) {
			log.warn(e.getMessage());
			//return new PersonaResponse("", "", "", "", "", "", "");
		}
		return null;
	}

}
