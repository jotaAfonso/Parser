package main;

import java.nio.file.Path;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

import main.converter.PathConverter;
import main.validators.FileParameterValidator;

@Parameters(separators = "=")
public class MainCLIParameters {
	
	@Parameter(names = {"-h", "--help"}, help = true, description = "Displays help information.")
	private boolean help;
	
	@Parameter(names = {"-i", "--inputFile"},
			required = true,
			validateWith = FileParameterValidator.class,
			converter = PathConverter.class,
			description = "Path to the source file.")
	private Path inputFile;
	
	@Parameter(names = {"-o", "--outputFile"},
			validateWith = FileParameterValidator.class,
			converter = PathConverter.class,
			description = "Path to the destination file. If not specified it will have the same path as the input file.")
	private Path outputFile;
	
	@Parameter(names = {"-fsm", "--fsm"}, description = "Displays the generated automaton as a fsm.")
	private boolean visual;

	public boolean isHelp() {
		return help;
	}
	
	public Path getInputPath() {
		return inputFile;
	}
	
	public Path getOutputPath() {
		return outputFile;
	}
	
	public boolean isVisual() {
		return visual;
	}
}
