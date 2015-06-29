package com.puppycrawl.tools.checkstyle.cli.options;

import com.puppycrawl.tools.checkstyle.cli.CommandLineOption;
import org.apache.commons.cli.CommandLine;

/**
 * @author Zeger Hoogeboom
 */
public class FormatOption implements CommandLineOption
{
	private String validationMessage;

	@Override
	public String getOption()
	{
		return "f";
	}

	@Override
	public boolean validate(CommandLine commandLine)
	{
		if (commandLine.hasOption(getOption())) {
			final String format = commandLine.getOptionValue(getOption());
			if (!"plain".equals(format) && !"xml".equals(format)) {
				validationMessage = String.format("Invalid output format."
						+ " Found '%s' but expected 'plain' or 'xml'.", format);
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
