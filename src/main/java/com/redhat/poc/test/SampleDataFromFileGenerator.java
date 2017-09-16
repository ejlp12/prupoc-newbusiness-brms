package com.redhat.poc.test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.redhat.poc.CoverageDataParser;

import prupoc.newbusiness.CoverageData;

public class SampleDataFromFileGenerator implements SampleDataGeneratorInf {
	
	private static Logger LOG = LoggerFactory.getLogger(SampleDataFromFileGenerator.class);

	
	private final String[] fileExtensions = new String[] { "txt", "ini" };
	private final String filePrefix = "[Data]";
	
	public static String INPUT_DIR = AppProperties.getString("inputDirectory");
	public static String DATA_FILE = AppProperties.getString("inputSingleFilename");;

	public List<Object> getList() {
		
		List<Object> dataList = new ArrayList<Object>();
		
		File dir = new File(INPUT_DIR);
		File[] files = dir.listFiles(new FileFilter() {

			public boolean accept(File file) {
				for (String extension : fileExtensions) {
					if (file.getName().toLowerCase().endsWith(extension)
							&& file.getName().startsWith(filePrefix)) {
						return true;
					}
				}
				return false;
			}
		});

		for (File f : files) {
			LOG.debug("\t#### file: " + f.getName());
			try {
				CoverageData data = CoverageDataParser.parse(f.getAbsolutePath());
				dataList.add(data);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return dataList;
	}

	public CoverageData getSingle() {
		
		CoverageData data = null;
		try {
			LOG.debug("\t##### File to be parse: " + INPUT_DIR + File.separator + DATA_FILE);
			data = CoverageDataParser.parse(INPUT_DIR + File.separator + DATA_FILE);
		} catch (IOException e) {
			e.printStackTrace();
			
		}
		return data;
	}

}
