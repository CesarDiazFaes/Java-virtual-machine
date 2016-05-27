package tp.pr5.mv;

import java.io.*;

import org.apache.commons.cli.*;

import tp.pr5.mv.ASMFile;
import tp.pr5.mv.controllers.*;
import tp.pr5.mv.cpu.*;
import tp.pr5.mv.definedExceptions.MyException;
import tp.pr5.mv.strategies.*;
import tp.pr5.mv.views.*;

public class Main {

	public static void main(String[] args) {
		boolean asm = false;
		String mode = "batch";	//Si no se especifica modo se ejecuta en modo batch
		InStrategy inStrat = new NullInStrategy();
		OutStrategy outStrat = new NullOutStrategy();
		ProgramMV prog = new ProgramMV();

		Options options = new Options();
		options.addOption("a", "asm", true, "Fichero con el codigo en ASM del programa a ejecutar." +
							" Obligatorio en modo batch.");
		options.getOption("a").setArgName("asmfile");
		options.addOption("h", "help", false, "Muestra esta ayuda.");
		options.addOption("i", "in", true, "Entrada del programa de la maquina-p.");
		options.getOption("i").setArgName("infile");
		options.addOption("m", "mode", true, "Modo de funcionamiento (batch | interactive | window)." + 
							" Por defecto batch.");
		options.getOption("m").setArgName("mode");
		options.addOption("o", "out", true, "Fichero donde se guarda la salida del programa de la maquina-p.");
		options.getOption("o").setArgName("outfile");
		
		try {
			CommandLineParser comParser = new BasicParser();
			CommandLine comLine = comParser.parse(options, args);
			
			//Ayuda
			
			if (comLine.hasOption("h")) {
				new HelpFormatter().printHelp(Main.class.getCanonicalName() + "[-a <asmfile>] [-h] [-i <infile>]" +
				" [-m <mode>] [-o <outfile>]", options);
				System.exit(0);
			}
			
			//Modo de funcionamiento
			
			if (comLine.hasOption("m")) {
				if (comLine.getOptionValue("m").equalsIgnoreCase("interactive")) {
					System.out.println("-INTERACTIVE MODE-");
					mode = "interactive";
				}
				
				else if (comLine.getOptionValue("m").equalsIgnoreCase("batch"))
					System.out.println("-BATCH MODE-");
					
				else if (comLine.getOptionValue("m").equalsIgnoreCase("window"))
					mode = "window";
				
				else
					throw new MyException("Modo incorrecto (parametro -m|--mode)");
			}
			
			else
				System.out.println("-BATCH MODE-");
			
			//Fichero de entrada
		
			if (comLine.hasOption("i")) {
				String inFileName = comLine.getOptionValue("i");
				if (mode.equals("window"))
					inStrat = new WindowInStrategy(inFileName);
				
				else
					inStrat = new FileInStrategy(inFileName);
			}
		
			else if (mode.equals("batch"))
				inStrat = new ConsoleInStrategy();
			
			else if (mode.equals("window"))
				inStrat = new WindowInStrategy();
			
			//Fichero de salida
			
			if (comLine.hasOption("o")) {
				String outFileName = comLine.getOptionValue("o");
				if (mode.equals("window"))
					outStrat = new WindowOutStrategy(outFileName);
				
				else
					outStrat = new FileOutStrategy(outFileName);
			}
				
			else if (mode.equals("batch"))
				outStrat = new ConsoleOutStrategy();
			
			else if (mode.equals("window"))
				outStrat = new WindowOutStrategy();
			
			//Fichero ASM
			
			if (comLine.hasOption("a")) {
				FileReader fr = new FileReader(comLine.getOptionValue("a"));
				BufferedReader br = new BufferedReader(fr);
				ASMFile asmFile = new ASMFile(new BufferedReader(br));
				asmFile.loadProgramFromFile(prog);
				asmFile.close();
				asm = true;
			}
			
			else if (!mode.equals("interactive")) {
				System.err.println("\nFichero ASM no encontrado.");
				System.exit(1);
			}
		}
		
		catch (MyException ex) {
			System.err.println(ex);
			System.err.println("Use -h|--help para más detalles");
			System.exit(2);
		}
		
		catch (ParseException ex) {
			System.err.println("Argumento incorrecto");
			System.err.println("Use -h|--help para más detalles");
			System.exit(1);
		}
		
		catch (IOException ex) {
			System.err.println("Error en fichero: " + ex.getMessage());
			System.err.println("Use -h|--help para más detalles");
			System.exit(1);
		}
		
		//Fin de tratamiento de argumentos
		
		CPU cpu = new CPU(inStrat, outStrat);	//Se crea la CPU con las estrategias establecidas
		
		if (mode.equals("interactive")) {
			InteractiveController cntrl = new InteractiveController(cpu);
			Interactive view = new Interactive(cntrl);
			cpu.addCPUObs(view);
			view.prepareProgram(prog, asm);	//Volcamos el programa en la CPU o lo pedimos al usuario
			view.startCPU();
		}
		
		else if (mode.equals("batch")) {
			BatchController cntrl = new BatchController(cpu);
			Batch view = new Batch(cntrl);
			cpu.addCPUObs(view);
			view.prepareProgram(prog);	//Volcamos el programa obtenido del fichero ASM en la CPU
			view.startCPU();
		}
		
		else {
			SwingController cntrl = new SwingController(cpu);
			MVWindow view = new MVWindow(cntrl);
			cpu.addCPUObs(view);
			cpu.addMemoryObs(view);
			cpu.addStackObs(view);
			cpu.loadProgram(prog);
		}
		
		try {
			inStrat.close();
			outStrat.close();
			
		} catch (IOException ex) {
			System.err.println("Error cerrando el fichero.");
		}
	}
}
