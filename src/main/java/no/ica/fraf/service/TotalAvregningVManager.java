package no.ica.fraf.service;

import java.util.List;

import no.ica.fraf.model.TotalAvregningV;

public interface TotalAvregningVManager {
	List<TotalAvregningV> findByBuntId(Integer buntId);
}
