/*
 * All rights Reserved, tianwei7518.
 * Copyright(C) 2014-2015
 * 2013年3月14日 下午2:33:27 
 */
package cn.slimsmart.common.document.word;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.POIXMLTextExtractor;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;

import cn.slimsmart.common.document.exception.DocumentException;
import cn.slimsmart.common.document.word.support.WordType;

public class WordTool {
	
	public static String readWord(String pathname,int wordType){
		switch (wordType) {
		case WordType.word2003:
			return ReadWordFile2003(pathname);
		case WordType.word2007:
			return ReadWordFile2007(pathname);
		default:
			throw new DocumentException("wordType not found!");
		}
	}
	
	private static String ReadWordFile2003(String filePath) {
		try {
			FileInputStream fis = new FileInputStream(new File(filePath));
			WordExtractor wordExtractor = new WordExtractor(fis);
			return wordExtractor.getText();
		} catch (FileNotFoundException e) {
			throw new DocumentException("file not found!",e);
		} catch (IOException e) {
			throw new DocumentException("read file error!",e);
		}
	}
	
	private static String ReadWordFile2007(String filePath) {
		try {
			OPCPackage opcPackage = POIXMLDocument.openPackage(filePath);
			POIXMLTextExtractor extractor = new XWPFWordExtractor(opcPackage);
			return  extractor.getText();
		} catch (Exception e) {
			throw new DocumentException("read file error!",e);
		} 
	}
}
