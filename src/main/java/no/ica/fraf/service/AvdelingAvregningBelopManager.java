package no.ica.fraf.service;

import java.util.List;

import no.ica.fraf.model.AvdelingAvregningBelop;
import no.ica.fraf.model.Bunt;



public interface AvdelingAvregningBelopManager {
	List<AvdelingAvregningBelop> findByBunt(Bunt bunt);
	
}
