package com.puppycrawl.tools.checkstyle.cli.options;

import com.puppycrawl.tools.checkstyle.cli.RequiredCommandLineOption;
import org.apache.commons.cli.CommandLine;

import java.io.File;

/**
 * @author Zeger Hoogeboom
 */
public class ConfigurationFileOption implements RequiredCommandLineOption
{

	private String validationMessage = "";
	@Override
	public String getOption()
	{
		return "c";
	}

	@Override
	public boolean validate(CommandLine commandLine)
	{
		final String configLocation = commandLine.getOptionValue("c");
		final File configFile =  new File(configLocation);
		if (!configFile.exists()) {
			this.validationMessage = String.format("unable to find '%s'.", configLocation);
			return false;
		}
		return true;
	}

	@Override
	public String getValidationMessage()
	{
		return validationMessage;
	}

	@Override
	public String getMissingOptionMessage()
	{
		return "Must specify a config XML file.";
	}
}
