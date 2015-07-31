/**
 * 
 */
package no.ica.fraf.gui.handlers;

import no.ica.fraf.util.GuiUtil;

import com.jgoodies.binding.beans.Model;

/**
 * Holder rede på hvilken periode som skal vises i buntvindu
 * 
 * @author abr99
 * 
 */
public class Periode extends Model {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public static final String PROPERTY_YEAR = "year";

	/**
	 * 
	 */
	public static final String PROPERTY_PERIODE = "periode";

	/**
	 * 
	 */
	public static final String PROPERTY_ALL = "all";

	/**
	 * 
	 */
	Integer year;

	/**
	 * 
	 */
	Integer periode;

	/**
	 * 
	 */
	Boolean all;

	/**
	 * 
	 */
	public Periode() {
		year = GuiUtil.getCurrentYear();
		periode = GuiUtil.getCurrentMonth();
		all = false;
	}

	/**
	 * @return år
	 */
	public Integer getYear() {
		return year;
	}

	/**
	 * @param aYear
	 */
	public void setYear(Integer aYear) {
		Integer oldYear = getYear();
		year = aYear;
		firePropertyChange(PROPERTY_YEAR, oldYear, aYear);
	}

	/**
	 * @return periode
	 */
	public Integer getPeriode() {
		return periode;
	}

	/**
	 * @param aPeriode
	 */
	public void setPeriode(Integer aPeriode) {
		Integer oldPeriode = getPeriode();
		periode = aPeriode;
		firePropertyChange(PROPERTY_PERIODE, oldPeriode, aPeriode);
	}

	/**
	 * @return true dersom alle skal hentes
	 */
	public Boolean getAll() {
		return all;
	}

	/**
	 * @param all
	 */
	public void setAll(Boolean all) {
		Boolean oldAll = getAll();
		this.all = all;
		firePropertyChange(PROPERTY_ALL, oldAll, all);
	}
}