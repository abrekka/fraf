package no.ica.elfa.gui.model;

import java.beans.PropertyChangeListener;
import java.math.BigDecimal;

import no.ica.elfa.model.ArticleCommission;
import no.ica.elfa.model.SupplierAccount;

import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.beans.Model;

/**
 * Integrasjonsklasse fra database mot GUI for artikkelkommisjon
 * 
 * @author abr99
 * 
 */
public class ArticleCommissionModel extends Model {
	private static final long serialVersionUID = 1L;

	public final static String PROPERTY_SUPPLIER_ACCOUNT = "supplierAccount";

	public final static String PROPERTY_ARTICLE_NO = "articleNo";

	public final static String PROPERTY_COMMISSION_TYPE = "commissionType";

	public final static String PROPERTY_ARTICLE_NAME = "articleName";

	public final static String PROPERTY_CODE = "code";

	public final static String PROPERTY_COMMISSION_PERCENTAGE = "commissionPercentage";

	private ArticleCommission articleCommission;

	/**
	 * @param aArticleCommission
	 */
	public ArticleCommissionModel(final ArticleCommission aArticleCommission) {
		articleCommission = aArticleCommission;
	}

	/**
	 * @return leverandørkonto
	 */
	public final SupplierAccount getSupplierAccount() {
		return articleCommission.getSupplierAccount();
	}

	/**
	 * @param supplierAccount
	 */
	public final void setSupplierAccount(final SupplierAccount supplierAccount) {
		SupplierAccount oldSupplier = getSupplierAccount();
		articleCommission.setSupplierAccount(supplierAccount);
		firePropertyChange(PROPERTY_SUPPLIER_ACCOUNT, oldSupplier,
				supplierAccount);
	}

	/**
	 * @return artikkelnummer
	 */
	public final BigDecimal getArticleNo() {
		return articleCommission.getArticleNo();
	}

	/**
	 * @param articleNo
	 */
	public final void setArticleNo(final BigDecimal articleNo) {
		BigDecimal oldNo = getArticleNo();
		articleCommission.setArticleNo(articleNo);
		firePropertyChange(PROPERTY_ARTICLE_NO, oldNo, articleNo);
	}

	/**
	 * @return kommisjonstype
	 */
	public final String getCommissionType() {
		return articleCommission.getCommissionType();
	}

	/**
	 * @param commissionType
	 */
	public final void setCommissionType(final String commissionType) {
		String oldType = getCommissionType();
		articleCommission.setCommissionType(commissionType);
		firePropertyChange(PROPERTY_COMMISSION_TYPE, oldType, commissionType);
	}

	/**
	 * @return artikkelnavn
	 */
	public final String getArticleName() {
		return articleCommission.getArticleName();
	}

	/**
	 * @param articleName
	 */
	public final void setArticleName(final String articleName) {
		String oldName = getArticleName();
		articleCommission.setArticleName(articleName);
		firePropertyChange(PROPERTY_ARTICLE_NAME, oldName, articleName);
	}

	/**
	 * @return kode
	 */
	public final String getCode() {
		return articleCommission.getCode();
	}

	/**
	 * @param code
	 */
	public final void setCode(final String code) {
		String oldCode = getCode();
		articleCommission.setCode(code);
		firePropertyChange(PROPERTY_CODE, oldCode, code);
	}

	/**
	 * @return kommisjonsprosent
	 */
	public final BigDecimal getCommissionPercentage() {
		return articleCommission.getCommissionPercentage();
	}

	/**
	 * @param commissionPercentage
	 */
	public final void setCommissionPercentage(final BigDecimal commissionPercentage) {
		BigDecimal oldCommission = getCommissionPercentage();
		articleCommission.setCommissionPercentage(commissionPercentage);
		firePropertyChange(PROPERTY_COMMISSION_PERCENTAGE, oldCommission,
				commissionPercentage);
	}

	/**
	 * @return artikkelkommisjon
	 */
	public final ArticleCommission getArticleCommission() {
		return articleCommission;
	}

	/**
	 * Legger til lytter for endringer i buffering
	 * 
	 * @param listener
	 * @param presentationModel
	 */
	public final void addBufferChangeListener(final PropertyChangeListener listener,
			PresentationModel presentationModel) {
		presentationModel.getBufferedModel(PROPERTY_ARTICLE_NAME)
				.addValueChangeListener(listener);
		presentationModel.getBufferedModel(PROPERTY_ARTICLE_NO)
				.addValueChangeListener(listener);
		presentationModel.getBufferedModel(PROPERTY_CODE)
				.addValueChangeListener(listener);
		presentationModel.getBufferedModel(PROPERTY_COMMISSION_PERCENTAGE)
				.addValueChangeListener(listener);
		presentationModel.getBufferedModel(PROPERTY_COMMISSION_TYPE)
				.addValueChangeListener(listener);
		presentationModel.getBufferedModel(PROPERTY_SUPPLIER_ACCOUNT)
				.addValueChangeListener(listener);
	}

}
