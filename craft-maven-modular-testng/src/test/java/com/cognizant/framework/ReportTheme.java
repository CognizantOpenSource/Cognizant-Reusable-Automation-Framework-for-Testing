package com.cognizant.framework;

/**
 * Class to represent the colour theme to be used for the reports
 * 
 * @author Cognizant
 */
public class ReportTheme {
	private String headingBackColor, headingForeColor;
	private String sectionBackColor, sectionForeColor;
	private String contentBackColor, contentForeColor;
	private String subHeadingBackColor, subHeadingForeColor;
	private String subSectionBackColor;

	/**
	 * Function to get the heading background color (also used as the
	 * sub-heading/sub-section text color)
	 * 
	 * @return The heading background color (also used as the
	 *         sub-heading/sub-section text color)
	 */
	public String getHeadingBackColor() {
		return headingBackColor;
	}

	/**
	 * Function to set the heading background color (also used as the
	 * sub-heading/sub-section text color)
	 * 
	 * @param headingBackColor
	 *            The heading background color (also used as the
	 *            sub-heading/sub-section text color)
	 */
	public void setHeadingBackColor(String headingBackColor) {
		this.headingBackColor = headingBackColor;
	}

	/**
	 * Function to get the heading text color (also used as the
	 * sub-heading/sub-section background color)
	 * 
	 * @return The heading text color (also used as the sub-heading/sub-section
	 *         background color)
	 */
	public String getHeadingForeColor() {
		return headingForeColor;
	}

	/**
	 * Function to set the heading text color (also used as the
	 * sub-heading/sub-section background color)
	 * 
	 * @param headingForeColor
	 *            The heading text color (also used as the
	 *            sub-heading/sub-section background color)
	 */
	public void setHeadingForeColor(String headingForeColor) {
		this.headingForeColor = headingForeColor;
	}

	/**
	 * Function to get the section background color
	 * 
	 * @return The section background color
	 */
	public String getSectionBackColor() {
		return sectionBackColor;
	}

	/**
	 * Function to set the section background color
	 * 
	 * @param sectionBackColor
	 *            The section background color
	 */
	public void setSectionBackColor(String sectionBackColor) {
		this.sectionBackColor = sectionBackColor;
	}

	/**
	 * Function to get the section text color
	 * 
	 * @return The section text color
	 */
	public String getSectionForeColor() {
		return sectionForeColor;
	}

	/**
	 * Function to set the section text color
	 * 
	 * @param sectionForeColor
	 *            The section text color
	 */
	public void setSectionForeColor(String sectionForeColor) {
		this.sectionForeColor = sectionForeColor;
	}

	/**
	 * Function to get the content background color
	 * 
	 * @return The content background color
	 */
	public String getContentBackColor() {
		return contentBackColor;
	}

	/**
	 * Function to set the content background color
	 * 
	 * @param contentBackColor
	 *            The content background color
	 */
	public void setContentBackColor(String contentBackColor) {
		this.contentBackColor = contentBackColor;
	}

	/**
	 * Function to get the content text color (also used as the overall
	 * background color)
	 * 
	 * @return The content text color (also used as the overall background
	 *         color)
	 */
	public String getContentForeColor() {
		return contentForeColor;
	}

	/**
	 * Function to set the content text color (also used as the overall
	 * background color)
	 * 
	 * @param contentForeColor
	 *            The content text color (also used as the overall background
	 *            color)
	 */
	public void setContentForeColor(String contentForeColor) {
		this.contentForeColor = contentForeColor;
	}

	public String getsubHeadingBackColor() {
		return subHeadingBackColor;
	}

	public void setSubHeadingBackColor(String subHeadingBackColor) {
		this.subHeadingBackColor = subHeadingBackColor;
	}

	public String getsubHeadingForeColor() {
		return subHeadingForeColor;
	}

	public void setSubHeadingForeColor(String subHeadingForeColor) {
		this.subHeadingForeColor = subHeadingForeColor;
	}

	public String getsubSectionBackColor() {
		return subSectionBackColor;
	}

	public void setSubSectionBackColor(String subSectionBackColor) {
		this.subSectionBackColor = subSectionBackColor;
	}
}