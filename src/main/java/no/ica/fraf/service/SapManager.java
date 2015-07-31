package no.ica.fraf.service;

import java.util.List;

public interface SapManager<E> {
	void saveBatch(List<E> dataList);
}
