package co.edu.javeriana.as.personapp.domain;

public enum Gender {
	MALE("Masculino"),
	FEMALE("Femenino"),
	OTHER("Otro");

	private final String label;

	Gender(String label) {
		this.label = label;
	}
	public String getLabel() {
		return label;
	}
}
