package no.ica.fraf.gui.model;

import javax.swing.JButton;

import no.ica.fraf.enums.MoveEnum;

/**
 * Hjelpeklasse for paginering
 * 
 * @author abr99
 * 
 */
public class PagingUtil {
	/**
	 * 
	 */
	public static final int FIRST_PAGE = 1;

	/**
	 * 
	 */
	public static final int PREV_PAGE = 2;

	/**
	 * 
	 */
	public static final int NEXT_PAGE = 3;

	/**
	 * 
	 */
	public static final int LAST_PAGE = 4;

	/**
	 * 
	 */
	public static final int NO_MOVE = 5;

	/**
	 * 
	 */
	private int currentIndex = -1;

	/**
	 * 
	 */
	private int count = -1;

	/**
	 * 
	 */
	private int currentPage = -1;

	/**
	 * 
	 */
	private int currentFetchSize = -1;

	/**
	 * 
	 */
	private MoveEnum currentMovement;

	/**
	 * 
	 */
	private JButton buttonLast;

	/**
	 * 
	 */
	private JButton buttonNext;

	/**
	 * 
	 */
	private JButton buttonPrev;

	/**
	 * 
	 */
	private JButton buttonFirst;

	/**
	 * 
	 */
	private int lastPage;

	/**
	 * @param fetchSize
	 * @param buttonFirst
	 * @param buttonNext
	 * @param buttonPrev
	 * @param buttonLast
	 */
	public PagingUtil(int fetchSize, JButton buttonFirst, JButton buttonNext,
			JButton buttonPrev, JButton buttonLast) {
		currentFetchSize = fetchSize;
		this.buttonLast = buttonLast;
		this.buttonNext = buttonNext;
		this.buttonPrev = buttonPrev;
		this.buttonFirst = buttonFirst;
	}

	/**
	 * @param fetchSize
	 */
	public PagingUtil(int fetchSize) {
		this(fetchSize, null, null, null, null);
	}

	/**
	 * @param count
	 */
	public void setCount(int count) {
		this.count = count;

		int mod = count % currentFetchSize;
		int div = count / currentFetchSize;

		if ((mod) == 0) {
			lastPage = div;
		} else if ((div) == 0) {
			lastPage = 1;
		} else {
			lastPage = (div) + 1;
		}
	}

	/**
	 * @param currentIndex
	 */
	public void setCurrentIndex(int currentIndex) {
		this.currentIndex = currentIndex;
	}

	/**
	 * @return antall
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @param movement
	 */
	public void movement(MoveEnum movement) {
		currentMovement = movement;
		int mod = count % currentFetchSize;
		int div = count / currentFetchSize;

		switch (movement) {
		case FIRST_PAGE:
			currentIndex = 0;
			if (count == 0) {
				currentPage = 0;
			} else {
				currentPage = 1;
			}
			// enableMoveButtons();
			break;
		case PREV_PAGE:
			currentIndex = currentIndex - currentFetchSize;
			currentPage--;
			// enableMoveButtons();
			break;
		case NEXT_PAGE:
			currentIndex = currentIndex + currentFetchSize;
			currentPage++;
			// enableMoveButtons();
			break;
		case LAST_PAGE:
			currentIndex = (count / currentFetchSize) * currentFetchSize;

			if ((mod) == 0) {
				currentPage = div;
			} else if ((div) == 0) {
				currentPage = 1;
			} else {
				currentPage = (div) + 1;
			}
			// currentPage = count / currentFetchSize;
			// enableMoveButtons();
			break;
		}
	}

	/**
	 * @return gjeldende indeks
	 */
	public int getCurrentIndex() {
		return currentIndex;
	}

	/**
	 * @return sidestørrelse
	 */
	public int getCurrentFetchSize() {
		return currentFetchSize;
	}

	/**
	 * @return sidetekst
	 */
	public String getPagingText() {
		if (count != -1) {
			String lastIndexStr;
			String firstIndex;

			if (count == 0) {
				firstIndex = "0";
			} else {
				firstIndex = String.valueOf(currentIndex + 1);
			}
			if ((currentPage * currentFetchSize) > count) {
				lastIndexStr = String.valueOf(count);
			} else {
				lastIndexStr = String.valueOf(currentPage * currentFetchSize);
			}

			StringBuffer infoString = new StringBuffer(firstIndex)
					.append(" - ").append(lastIndexStr).append(" av ").append(
							count);

			return infoString.toString();
		}
		return "";

	}

	/**
	 * 
	 */
	public void enableMoveButtons() {
		int lastPage;
		int mod = count % currentFetchSize;
		int div = count / currentFetchSize;

		if ((mod) == 0) {
			lastPage = div;
		} else if ((div) == 0) {
			lastPage = 1;
		} else {
			lastPage = (div) + 1;
		}
		if (currentPage == lastPage && (currentPage == 1 || currentPage == 0)) {
			buttonFirst.setEnabled(false);
			buttonPrev.setEnabled(false);
			buttonLast.setEnabled(false);
			buttonNext.setEnabled(false);
		} else if (currentPage == 1) {
			buttonFirst.setEnabled(false);
			buttonPrev.setEnabled(false);
			buttonLast.setEnabled(true);
			buttonNext.setEnabled(true);
		} else if (currentPage == lastPage) {
			buttonFirst.setEnabled(true);
			buttonPrev.setEnabled(true);
			buttonLast.setEnabled(false);
			buttonNext.setEnabled(false);
		} else {
			buttonFirst.setEnabled(true);
			buttonPrev.setEnabled(true);
			buttonLast.setEnabled(true);
			buttonNext.setEnabled(true);
		}
	}

	/**
	 * @return Returns the currentPage.
	 */
	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * @return Returns the currentMovement.
	 */
	public MoveEnum getCurrentMovement() {
		return currentMovement;
	}

	/**
	 * @param currentMovement
	 *            The currentMovement to set.
	 */
	public void setCurrentMovement(MoveEnum currentMovement) {
		this.currentMovement = currentMovement;
	}

	/**
	 * @return Returns the lastPage.
	 */
	public int getLastPage() {
		return lastPage;
	}

}
