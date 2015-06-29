package com.puppycrawl.tools.checkstyle.cli;

import com.puppycrawl.tools.checkstyle.cli.options.ConfigurationFileOption;
import com.puppycrawl.tools.checkstyle.cli.options.FormatOption;
import com.puppycrawl.tools.checkstyle.cli.options.OutputFileOption;
import com.puppycrawl.tools.checkstyle.cli.options.PropertiesFileOption;
import org.apache.commons.cli.CommandLine;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Zeger Hoogeboom
 */
public class CommandLineValidator {
	private List<String> validationMessages = new ArrayList<>();
	private CommandLine commandLine;
	private List<CommandLineOption> options;
	private List<File> files;

	public CommandLineValidator(CommandLine commandLine, List<CommandLineOption> options, List<File> files)
	{
		this.commandLine = commandLine;
		this.options = options;
		this.files = files;
	}

	public static CommandLineValidator withDefaults(CommandLine commandLine, List<File> files)
	{
		return new CommandLineValidator(commandLine,
				Arrays.asList(new ConfigurationFileOption(), new FormatOption(), new OutputFileOption(), new PropertiesFileOption()), files);
	}

	public List<String> validate()
	{
		if (validateRequiredParams() == false) return validationMessages;
		validateOptions();
		if (files.isEmpty()) {
			addMessage("Must specify files to process, found 0.");
		}
		return validationMessages;
	}

	private boolean validateRequiredParams()
	{
		for (CommandLineOption requiredParam : options) {
			if (requiredParam instanceof RequiredCommandLineOption && ! commandLine.hasOption(requiredParam.getOption())) {
				addMessage(((RequiredCommandLineOption) requiredParam).getMissingOptionMessage());
				return false;
			}
		}
		return true;
	}

	private void validateOptions()
	{
		for (CommandLineOption option : options) {
			if (option.validate(commandLine) == false) {
				addMessage(option.getValidationMessage());
			}
		}
	}

	private void addMessage(String message)
	{
		if (message != null && "".equals(message) == false) this.validationMessages.add(message);
	}
}
