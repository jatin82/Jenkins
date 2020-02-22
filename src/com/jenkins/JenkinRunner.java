package com.jenkins;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.jenkins.utils.FileLogger;
import com.jenkins.utils.TerminalCMD;

public class JenkinRunner {
	
	public static void main(String[] args) throws IOException {
		try {
			String configFilePath = args[0];
			boolean isLinux = Boolean.valueOf(args[1]);
			List<String> commands = getCommands(configFilePath); 
			TerminalCMD terminal = new TerminalCMD(isLinux);
			FileLogger logger = new FileLogger();
			terminal.runInSequence(commands,logger);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		/*Runtime runtime = Runtime.getRuntime();
		Process process = runtime.exec(new String[]{"sh","-c","cd /Users/b0216204/Downloads/hrapp/HRM & pwd"});
		BufferedReader r = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        StringBuilder sb = new StringBuilder();
        while (true) {
            line = r.readLine();
            if (line == null) { break; }
            sb.append(line+"\n");
        }
        System.out.println(sb.toString());
		*/
		
	}
	
	private static List<String> getCommands(String path,boolean dummy) throws IOException {
		List<String> commands = new ArrayList<>();
		
		commands.add("rm -rf /Users/b0216204/Downloads/cmdtest");
		commands.add("git clone https://github.com/jatin82/HRM.git /Users/b0216204/Downloads/cmdtest");
		commands.add("/Users/b0216204/work/software/gradle-6.1.1/bin/gradle clean build -p /Users/b0216204/Downloads/cmdtest");
		
//		commands.add("ls");
//		commands.add("ls -a");
//		commands.add("ls -lt");
//		commands.add("history");
//		
//		commands.add("java -version");
//		commands.add("ls -lt");
		
		
		return commands;
	}
	
	private static List<String> getCommands(String path) throws IOException {
		File file = new File(path);
		List<String> commands = new ArrayList<>();
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line;
		while((line=reader.readLine())!=null) {
			commands.add(line);
		}
		reader.close();
		return commands;
	}

}
