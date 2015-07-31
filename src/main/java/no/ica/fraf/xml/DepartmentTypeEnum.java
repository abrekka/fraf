package no.ica.fraf.xml;

import no.ica.fraf.model.Department;

public enum DepartmentTypeEnum {
	OWN, DAUGHTER, FRANCHISE;

	public static DepartmentTypeEnum getDepartmentTypeEnum(Department department) {

		if (department.getAvtaletype().equalsIgnoreCase("F")) {
			return FRANCHISE;
		}
		if (department.getDatasetConcorde() != null
				&& department.getDatasetConcorde().equalsIgnoreCase("100")) {
			return OWN;
		}

		return DAUGHTER;
	}
}
