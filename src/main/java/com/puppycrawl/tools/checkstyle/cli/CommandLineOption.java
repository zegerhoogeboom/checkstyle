package com.puppycrawl.tools.checkstyle.cli;

import org.apache.commons.cli.CommandLine;

/**
 * @author Zeger Hoogeboom
 */
public interface CommandLineOption {
	String getOption();
	boolean validate(CommandLine commandLine);
	String getValidationMessage();
}