package com.puppycrawl.tools.checkstyle.cli.options;

import com.puppycrawl.tools.checkstyle.cli.CommandLineOption;
import org.apache.commons.cli.CommandLine;

import java.io.File;

/**
 * @author Zeger Hoogeboom
 */
public class OutputFileOption implements CommandLineOption
{
	private String validationMessage;

	@Override
	public String getOption()
	{
		return "o";
	}

	@Override
	public boolean validate(CommandLine commandLine)
	{
		if (commandLine.hasOption("o")) {
			final String outputLocation = commandLine.getOptionValue("o");
			final File file = new File(outputLocation);
			if (file.exists() && !(file.canRead() && file.canWrite())) {
				validationMessage = String.format("Permission denied : '%s'.", outputLocation);
				return false;
			}
		}
		return true;
	}

	@Override
	public String getValidationMessage()
	{
		return validationMessage;
	}
}
