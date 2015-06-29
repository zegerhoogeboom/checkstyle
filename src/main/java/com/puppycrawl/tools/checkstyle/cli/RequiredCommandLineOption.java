package com.puppycrawl.tools.checkstyle.cli;

/**
 * @author Zeger Hoogeboom
 */
public interface RequiredCommandLineOption extends CommandLineOption
{
	String getMissingOptionMessage();
}
