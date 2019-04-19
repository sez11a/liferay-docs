package com.liferay.documentation.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

public class ConvertLinksTask  extends Task {

	public void execute() throws BuildException {

		String docDir = _docdir;

		List<String> ceFileList = DocsUtil.getMarkdownFileList(docDir, "");
		List<String> dxpFileList = DocsUtil.getMarkdownFileList(docDir, "-dxp");

		List<String> fileList = new ArrayList<String>();
		fileList.addAll(ceFileList);
		fileList.addAll(dxpFileList);

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
				String findStr = "/-/knowledge_base/";
				
				while ((line = in.readLine()) != null) {
					
					// /docs/7-2/user/-/knowledge_base/user/headerID
					// /discover/portal/-/knowledge_base/7-2/headerID
					
					if (line.contains("](/discover/portal/")) {
						
						int startIndex = line.indexOf("/discover/portal/");
						int endIndex = line.indexOf(")", startIndex);
						
						int versionIndex = line.indexOf(findStr) + findStr.length();
						String version = line.substring(versionIndex, versionIndex + 3);
						
						int headerStartIndex = line.indexOf(version) + 4;
						int headerEndIndex = line.indexOf(")", headerStartIndex);
						String headerID = line.substring(headerStartIndex, headerEndIndex);
						
						String oldLink = line.substring(startIndex, endIndex);
						
						String newLink = "/docs/" + version + "/user" + findStr +
								"user/" + headerID;
						
						line = line.replace(oldLink, newLink);
					}
					else if (line.contains("](/discover/deployment/")) {
						
						int startIndex = line.indexOf("/discover/deployment/");
						int endIndex = line.indexOf(")", startIndex);
						
						int versionIndex = line.indexOf(findStr) + findStr.length();
						String version = line.substring(versionIndex, versionIndex + 3);
						
						int headerStartIndex = line.indexOf(version) + 4;
						int headerEndIndex = line.indexOf(")", headerStartIndex);
						String headerID = line.substring(headerStartIndex, headerEndIndex);
						
						String oldLink = line.substring(startIndex, endIndex);
						
						String newLink = "/docs/" + version + "/deploy" + findStr +
								"deploy/" + headerID;
						
						line = line.replace(oldLink, newLink);
						
					}
					else if (line.contains("](/develop/tutorials/")) {
						
						int startIndex = line.indexOf("/develop/tutorials/");
						int endIndex = line.indexOf(")", startIndex);
						
						int versionIndex = line.indexOf(findStr) + findStr.length();
						String version = line.substring(versionIndex, versionIndex + 3);
						
						int headerStartIndex = line.indexOf(version) + 4;
						int headerEndIndex = line.indexOf(")", headerStartIndex);
						String headerID = line.substring(headerStartIndex, headerEndIndex);
						
						String oldLink = line.substring(startIndex, endIndex);
						
						String newLink = "/docs/" + version + "/dev/tutorials" + findStr +
								"tutorials/" + headerID;
						
						line = line.replace(oldLink, newLink);
						
					}
					else if (line.contains("](/develop/reference/")) {
						
						int startIndex = line.indexOf("/develop/tutorials/");
						int endIndex = line.indexOf(")", startIndex);
						
						int versionIndex = line.indexOf(findStr) + findStr.length();
						String version = line.substring(versionIndex, versionIndex + 3);
						
						int headerStartIndex = line.indexOf(version) + 4;
						int headerEndIndex = line.indexOf(")", headerStartIndex);
						String headerID = line.substring(headerStartIndex, headerEndIndex);
						
						String oldLink = line.substring(startIndex, endIndex);
						
						String newLink = "/docs/" + version + "/dev/reference" + findStr +
								"reference/" + headerID;
						
						line = line.replace(oldLink, newLink);
						
					}
					
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
				throw new BuildException(e.getLocalizedMessage());
			}
		}	
	}

	public void setDocdir(String docdir) {
		_docdir = docdir;
	}

	private String _docdir;
}