package com.cognizant.framework;

/**
 * Class to encapsulate the cell formatting settings for the Excel spreadsheet
 * @author Cognizant
 */
class ExcelCellFormatting {
	private String fontName;
	/**
	 * Function to get the name of the font to be used in the cell
	 * @return The font name
	 */
	String getFontName() {
		return fontName;
	}
	/**
	 * Function to set the name of the font to be used in the cell
	 * @param fontName The font name
	 */
	void setFontName(String fontName) {
		this.fontName = fontName;
	}
	
	private short fontSize;
	/**
	 * Function to get the font size to be used in the cell
	 * @return The font size
	 */
	short getFontSize() {
		return fontSize;
	}
	/**
	 * Function to set the font size to be used in the cell
	 * @param fontSize The font size
	 */
	void setFontSize(short fontSize) {
		this.fontSize = fontSize;
	}
	
	private short backColorIndex;
	/**
	 * Function to get the index of the background color for the cell
	 * @return The background color index
	 */
    short getBackColorIndex() {
    	return backColorIndex;
    }
    /**
	 * Function to set the index of the background color for the cell
	 * @param backColorIndex The background color index
	 */
    void setBackColorIndex(short backColorIndex) {
    	if(backColorIndex < 0x8 || backColorIndex > 0x40) {
			throw new FrameworkException("Valid indexes for the Excel custom palette are from 0x8 to 0x40 (inclusive)!");
		}
    	
    	this.backColorIndex = backColorIndex;
    }
    
    private short foreColorIndex;
    /**
	 * Function to get the index of the foreground color (i.e., font color) for the cell
	 * @return The foreground color (font color) index
	 */
    short getForeColorIndex() {
    	return foreColorIndex;
    }
    
    /**
     * Function to set the index of the foreground color (i.e., font color) for the cell
     * @param foreColorIndex The foreground color (font color) index
     */
    void setForeColorIndex(short foreColorIndex) {
    	if(foreColorIndex < 0x8 || foreColorIndex > 0x40) {
			throw new FrameworkException("Valid indexes for the Excel custom palette are from 0x8 to 0x40 (inclusive)!");
		}
    	
    	this.foreColorIndex = foreColorIndex;
    }
	
    /**
     * Boolean variable to control whether the cell contents are in bold
     */
    boolean bold = false;
    /**
     * Boolean variable to control whether the cell contents are in italics
     */
    boolean italics = false;
    /**
     * Boolean variable to control whether the cell contents are centred
     */
    boolean centred = false;
}