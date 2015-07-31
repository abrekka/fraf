package no.ica.fraf.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import no.ica.fraf.common.ApplUserInterface;
import no.ica.fraf.common.Batchable;

public abstract class AbstractBatchable extends BaseObject implements Batchable {
	private static final SimpleDateFormat formatter = new SimpleDateFormat(	"yyyy-MM-dd");

	
	public StringBuffer getXmlFreeTextBuffer() {
		StringBuffer freeTextBuffer = new StringBuffer();
		Date fromDate = getFromDate();
		Date toDate = getToDate();
		if (fromDate != null && toDate != null) {

			freeTextBuffer = new StringBuffer("Avregningsperiode: ").append(
					formatter.format(fromDate)).append(" til ").append(
					formatter.format(toDate));
		}
		return freeTextBuffer;
	}
	
	public StringBuffer getXmlComment() {
		StringBuffer xmlComment = new StringBuffer();
		ApplUserInterface applUserInterface = getApplUserInterface();
		xmlComment.append(getInfoString()).append(
				applUserInterface.getFirstName()).append(" ").append(
				applUserInterface.getSurname()).append(" den ").append(
				getCreatedDate()).append(" fra fakturabunt ").append(
				getBatchId());
		return xmlComment;
	}
}
