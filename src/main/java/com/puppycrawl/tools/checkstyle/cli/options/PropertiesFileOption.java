package com.puppycrawl.tools.checkstyle.cli.options;

import com.puppycrawl.tools.checkstyle.cli.CommandLineOption;
import org.apache.commons.cli.CommandLine;

import java.io.File;

/**
 * @author Zeger Hoogeboom
 */
public class PropertiesFileOption implements CommandLineOption
{
	private String validationMessage;

	@Override
	public String getOption()
	{
		return "p";
	}

	@Override
	public boolean validate(CommandLine commandLine)
	{
		if (commandLine.hasOption(getOption())) {
			final String propertiesLocation = commandLine.getOptionValue(getOption());
			final File file = new File(propertiesLocation);
			if (!file.exists()) {
				validationMessage = String.format("Could not find file '%s'.", propertiesLocation);
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
