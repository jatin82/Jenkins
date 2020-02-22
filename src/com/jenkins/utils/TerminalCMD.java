package com.jenkins.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TerminalCMD {
	
	private final static String JOINER = " & ";
	
	private List<String> commands;
	
	public TerminalCMD(boolean isLinux) {
		this.commands = new ArrayList<>();
		if(isLinux) {
			this.commands.add("sh");
			this.commands.add("-c");
		}else {
			this.commands.add("sh");
			this.commands.add("-c");
		}
	}
	
	public String runBulk(List<String> commands,FileLogger logger) throws IOException, InterruptedException {
		ProcessBuilder builder = getDefualtBuilder();
		builder.command().add(concatAllCommands(commands));
		String res = run(builder,logger);
		return res;
	}
	
	public String runInSequence(List<String> commands,FileLogger logger) throws IOException, InterruptedException {
		StringBuilder res = new StringBuilder();
		for(String command : commands) {
			ProcessBuilder builder = getDefualtBuilder();
			builder.command().add(command);
			String out = run(builder,logger);
			res.append(out);
		}
		return res.toString();
	}
	
	private String concatAllCommands(List<String> commands) {
		return commands.stream().collect(Collectors.joining(JOINER));
	}
	
	
	public String run(String command,FileLogger logger) throws IOException, InterruptedException {
		ProcessBuilder builder = getDefualtBuilder();
		builder.command().add(command);
		return run(builder,logger);
	}
	
	
	public String run(ProcessBuilder builder,FileLogger logger) throws IOException, InterruptedException {
		Process process = builder.start();
		String res = run(process,logger);
		process.destroy();
		return res;
	}
	
	public String run(Process process, FileLogger logger) throws IOException, InterruptedException {
        BufferedReader r = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        StringBuilder sb = new StringBuilder();
        while (true) {
            line = r.readLine();
            if (line == null) { break; }
            line = line + "\n";
            logger.log(line);
            sb.append(line);
        }
        return sb.toString();
	}
	
	private ProcessBuilder getDefualtBuilder() {
		ProcessBuilder builder = new ProcessBuilder();
		builder.redirectErrorStream(true);
		builder.command().add(this.commands.get(0));
		builder.command().add(this.commands.get(1));
		return builder;
	}

}
