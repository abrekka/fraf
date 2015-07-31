package no.ica.fraf.service;

import java.util.List;

import no.ica.fraf.model.AvdelingAvregning;
import no.ica.fraf.model.AvdelingAvregningBelop;

public interface AvdelingAvregningManager {
	List<AvdelingAvregning> findByAvdelingAvregningBelop(AvdelingAvregningBelop belop);
	List<AvdelingAvregning> findByAvdnr(Integer avdnr,Integer year);
}
