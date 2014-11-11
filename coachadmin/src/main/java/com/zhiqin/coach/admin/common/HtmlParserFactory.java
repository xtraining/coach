package com.zhiqin.coach.admin.common;

public class HtmlParserFactory {

	public static MyHtmlParser getParser(int sourceFrom) {
		MyHtmlParser parser = null;
		switch (sourceFrom) {
		case 0:
			parser = new XimalayaParser();
			break;

		default:
			break;
		}
		return parser;
	}

}
