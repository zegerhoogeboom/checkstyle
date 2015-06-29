package com.puppycrawl.tools.checkstyle.cli.options;

import com.puppycrawl.tools.checkstyle.Main;
import com.puppycrawl.tools.checkstyle.cli.CommandLineOption;
import org.apache.commons.cli.CommandLine;

/**
 * @author Zeger Hoogeboom
 */
public class VersionOption implements CommandLineOption
{
	private String validationMessage;

	@Override
	public String getOption()
	{
		return "v";
	}

	@Override
	public boolean validate(CommandLine commandLine)
	{
		if (commandLine.hasOption("v")) {
			validationMessage = "Checkstyle version: " + Main.class.getPackage().getImplementationVersion();
			return true;
		}
		return false;
	}

	@Override
	public String getValidationMessage()
	{
		return validationMessage;
	}
}
