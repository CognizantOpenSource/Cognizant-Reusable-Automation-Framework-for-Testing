/*
 *  © [2020] Cognizant. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.cognizant.framework;

public class WhitelistingPath {

	public static String cleanStringForFilePath(String path) {

		if (path == null)
			return null;
		String cleanString = "";
		for (int i = 0; i < path.length(); ++i) {
			cleanString += cleanCharForFilePath(path.charAt(i));
		}
		cleanString = cleanString.replace("..", "%");
		return cleanString;
	}

	private static char cleanCharForFilePath(char aChar) {

		// 0 - 9
		for (int i = 48; i < 58; ++i) {
			if (aChar == i)
				return (char) i;
		}

		// 'A' - 'Z'
		for (int i = 65; i < 91; ++i) {
			if (aChar == i)
				return (char) i;
		}

		// 'a' - 'z'
		for (int i = 97; i < 123; ++i) {
			if (aChar == i)
				return (char) i;
		}

		// other valid characters
		switch (aChar) {
		case '/':
			return '/';
		case '.':
			return '.';
		case '-':
			return '-';
		case '_':
			return '_';
		case ' ':
			return ' ';
		case '\\':
			return '/';
		case ':':
			return ':';
		}
		return '%';
	}

	public static String cleanStringForPackage(String path) {

		if (path == null)
			return null;
		String cleanString = "";
		for (int i = 0; i < path.length(); ++i) {
			cleanString += cleanCharForPackage(path.charAt(i));
		}
		cleanString = cleanString.replace("..", "%");
		return cleanString;
	}

	private static char cleanCharForPackage(char aChar) {

		// 0 - 9
		for (int i = 48; i < 58; ++i) {
			if (aChar == i)
				return (char) i;
		}

		// 'A' - 'Z'
		for (int i = 65; i < 91; ++i) {
			if (aChar == i)
				return (char) i;
		}

		// 'a' - 'z'
		for (int i = 97; i < 123; ++i) {
			if (aChar == i)
				return (char) i;
		}

		// other valid characters
		switch (aChar) {

		case '.':
			return '.';
		case '-':
			return '-';
		case '_':
			return '_';
		}
		return '%';
	}

}
