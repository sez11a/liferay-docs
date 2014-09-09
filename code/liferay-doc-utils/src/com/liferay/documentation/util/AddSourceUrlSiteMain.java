package com.liferay.documentation.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.tools.ant.Task;

public class AddSourceUrlSiteMain extends Task {

	public static void main(String[] args) throws Exception {

		if (args == null || args.length < 4) {
			throw new IllegalArgumentException(
				"Requires 4 arguments: docDir repoOwner repoName repoBranch");
		}

		String docDir = args[0];

		String repoOwner = args[1];
		String repoName = args[2];
		String repoBranch = args[3];

		File tmpArticlesDir = new File("../" + docDir + "/articles");
		File docSetDir = new File("../" + docDir);
		System.out.println(
			"Adding source URL metadata for files in " +
					tmpArticlesDir.getPath() + " ...");

		if (!tmpArticlesDir.exists() || !tmpArticlesDir.isDirectory()) {
			throw new Exception(
				"FAILURE - bad articles directory " + tmpArticlesDir);
		}

		File articlesDir = new File("articles");

		List<File> docSetDirFolders = new ArrayList<File>();
		File articlesDirContents[] = articlesDir.listFiles();
		for(int i=0; i < articlesDirContents.length; i++) {
			if(articlesDirContents[i].isDirectory()) {
				docSetDirFolders.add(articlesDirContents[i]);
			}
		}

		docSetDirFolders.add(docSetDir);
		
		File docSetDirFoldersArray[] = docSetDirFolders.toArray(
			new File[docSetDirFolders.size()]);
		
		List<String> fileList = new ArrayList<String>();

		for(int i=0; i < docSetDirFoldersArray.length; i++) {
			
			File files[] = docSetDirFoldersArray[i].listFiles(
				new FilenameFilter() {
					String filePatternArg =
						"([^\\\\\\[\\]\\|:;%<>]+).markdown";
					Pattern fileNamePattern = Pattern.compile(filePatternArg);

					public boolean accept(File file, String name) {
						return (fileNamePattern.matcher(name).matches());
					}
				}
			);
			
			for (int j = 0; j < files.length; j++) {
				fileList.add(files[j].getPath());
			}
			
		}

		if (fileList.isEmpty()) {
			throw new Exception(
				"FAILURE - no markdown files found in " + articlesDir);
		}

		for (int i = 0; i < fileList.size(); i++) {
			String filename = fileList.get(i);
			File inFile = new File(filename);
			File outFile = new File(filename);
			String outFileTmp = outFile + ".tmp";

			try {
				LineNumberReader in =
					new LineNumberReader(new FileReader(inFile));
				BufferedWriter out =
					new BufferedWriter(new FileWriter(outFileTmp));

				String line;
				if ((line = in.readLine()) != null) {

					if (line.startsWith("# ")) {
						
						line = line.trim();

						String newHeadingLine = handleHeaderLine(line,
							filename, repoOwner, repoName, repoBranch);
						if (newHeadingLine != null) {
							line = newHeadingLine;
						}
					}

					out.append(line);
					out.append("\n");
				}


				while ((line = in.readLine()) != null) {
					out.append(line);
					out.append("\n");
				}

				in.close();
				
				out.flush();
				out.close();

				// Replace original file with modified temp file

				FileUtils.copyFile(
					new File(outFileTmp),
					new File(filename));

				FileUtils.forceDelete(new File(outFileTmp));
			} catch (IOException e) {
				throw new Exception(e.getLocalizedMessage());
			}
		}

	}

	private static String handleHeaderLine(String line, String filename,
			String repoOwner, String repoName, String repoBranch)
		throws Exception {

		StringBuilder sb = new StringBuilder();

		// Check if the header contains a source URL

		if (sourceUrlPattern.matcher(line).matches()) {
			return line;
		}

		line = line.trim();

		if (!line.endsWith(")")) {
			throw new Exception(
				"FAILURE - Header line must end with parenthesis. file: " +
					filename + ", line: " + line);
		}

		File file = new File(filename);
		String path = file.getAbsolutePath();
		path = path.replace('\\', '/');

		int repoNameIndex = path.lastIndexOf(repoName);

		if (repoNameIndex == -1) {
			throw new Exception(
				"FAILURE - Could not find repo root directory " +
				repoName + " in the file's path. file: " +
				filename);
		}

		int y = line.lastIndexOf(")");
		sb.append(line.substring(0, y));

		if (line.contains("=")){
			// Add a space after existing metadata assignment
			sb.append(" ");
		}

		sb.append("sourceUrl=https://github.com/");
		sb.append(repoOwner);
		sb.append("/");
		sb.append(repoName);
		sb.append("/blob/");
		sb.append(repoBranch);

		String repoFilePath = path.substring(repoNameIndex + repoName.length());
		sb.append(repoFilePath);
		sb.append(")");

		return sb.toString();
	}

	private static Pattern sourceUrlPattern;

	static {
		String patternArg = "(#)+([^\\\\\\[\\]\\|%<>]*)" +
				Pattern.quote("[") + Pattern.quote("]") +
				Pattern.quote("(") + "([^\\\\\\[\\]\\|:;%]+)" + "sourceUrl" +
				Pattern.quote("=") + "([^\\\\\\[\\]\\|:;%]+)" +
				Pattern.quote(")") + "([ \\t\\n\\x0B\\f\\r]*?)";

		sourceUrlPattern = Pattern.compile(patternArg);
	}

}
