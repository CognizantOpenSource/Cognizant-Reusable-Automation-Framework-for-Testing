package com.cognizant.framework;

/**
 * Factory class to create the {@link ReportTheme} object as required
 * 
 * @author Cognizant
 */
public class ReportThemeFactory {
	private ReportThemeFactory() {
		// To prevent external instantiation of this class
	}

	/**
	 * Enumeration to specify the name of the report theme to be used
	 * 
	 * @author Cognizant
	 */
	public enum Theme {
		CLASSIC, MYSTIC, AUTUMN, OLIVE, CUSTOM 
	}

	/**
	 * Function to return the {@link ReportTheme} object based on the color
	 * {@link Theme} specified
	 * 
	 * @param theme
	 *            The color {@link Theme} to be used for the report
	 * @return The {@link ReportTheme} object
	 */
	public static ReportTheme getReportsTheme(Theme theme) {
		ReportTheme reportTheme = new ReportTheme();

		switch (theme) {
		case CLASSIC:
			reportTheme.setContentForeColor("#282A2A");
			reportTheme.setHeadingBackColor("#495758");
			reportTheme.setHeadingForeColor("#95A3A4");
			reportTheme.setSubHeadingBackColor("#95A3A4");
			reportTheme.setSubHeadingForeColor("#495758");
			reportTheme.setSectionBackColor("#8B9292");
			reportTheme.setSectionForeColor("#001429");
			reportTheme.setSubSectionBackColor("#EDEEF0");
			reportTheme.setContentBackColor("#000000");
			break;

		case MYSTIC:
			reportTheme.setContentForeColor("#000000");
			reportTheme.setHeadingBackColor("#4D7C7B");
			reportTheme.setHeadingForeColor("#FFFF95");
			reportTheme.setSubHeadingBackColor("#FFFF95");
			reportTheme.setSubHeadingForeColor("#4D7C7B");
			reportTheme.setSectionBackColor("#89B6B5");
			reportTheme.setSectionForeColor("#333300");
			reportTheme.setSubSectionBackColor("#FAFAC5");
			reportTheme.setContentBackColor("#000000");
			break;

		case OLIVE:
			reportTheme.setContentForeColor("#003326");
			reportTheme.setHeadingBackColor("#86816A");
			reportTheme.setHeadingForeColor("#333300");
			reportTheme.setSubHeadingBackColor("#333300");
			reportTheme.setSubHeadingForeColor("#86816A");
			reportTheme.setSectionBackColor("#A6A390");
			reportTheme.setSectionForeColor("#001F00");
			reportTheme.setSubSectionBackColor("#E8DEBA");
			reportTheme.setContentBackColor("#000000");
			break;
		case CUSTOM:
			reportTheme.setContentForeColor("#C1E1A6");
			reportTheme.setHeadingBackColor("#A9D0F5");
			reportTheme.setHeadingForeColor("#000000");
			reportTheme.setSubHeadingBackColor("#E0E6F8");
			reportTheme.setSubHeadingForeColor("#34495E");
			reportTheme.setSectionBackColor("#E0E6F8");
			reportTheme.setSectionForeColor("#333300");
			reportTheme.setSubSectionBackColor("#EDEEF0");
			reportTheme.setContentBackColor("#000000");
			break;
			
		case AUTUMN:
			reportTheme.setContentForeColor("#F0F3F4");
			reportTheme.setHeadingBackColor("#6DB33F");
			reportTheme.setHeadingForeColor("#FFFFFF");
			reportTheme.setSubHeadingBackColor("#76D7C4");
			reportTheme.setSubHeadingForeColor("#34495E");
			reportTheme.setSectionBackColor("#92E759");
			reportTheme.setSectionForeColor("#333300");
			reportTheme.setContentBackColor("#000000");
			break;

		default:
			throw new FrameworkException("Invalid report theme!");
		}

		return reportTheme;
	}
}