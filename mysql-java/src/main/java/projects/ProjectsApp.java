package projects;



import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import projects.entity.Project;
import projects.exception.DbException;
import projects.service.ProjectService;

public class ProjectsApp {
		private Scanner scanner = new Scanner(System.in);
		private ProjectService projectService = new ProjectService();
		
		//@formatter:off
		private List<String> operations =Arrays.asList("1) Add a Project");
		
		//@formatter:on
		
		/*
		 * Entry point for java application.
		 * @param args Unused.
		 */
		public static void main(String[] args) {
			new ProjectsApp().processUserSelection();
		}
			/*
			 * This method prints the operations, gets a user menu selection, and performs the requested operation.
			 * It repeats until the user request that the application terminate.
			 */

		
		private void processUserSelection() {
			boolean done = false;

			while(!done) {
				try {
					int selection = getUserSelection();

					switch(selection) {
					case -1:
						done = exitMenu();
						break;

					case 1:
						createProject();
						break;

					default:
						System.out.println("\n" + selection + " is not a valid selection. Try Again.");
						break;
					}
				} 
				catch(Exception e){
					System.out.println("\nError: " + e + " Try Again.");
				}
			}
		}
		/*
		 * Gather user input for a project row then call the project service to create the row.
		 */
		private void createProject() {
			Integer project_id = getIntInput("Enter the project ID.");
			String projectName = getStringInput("Enter the project name.");
			BigDecimal estimatedHours = getDecimalInput("Enter the estimated hours.");
			BigDecimal actualHours = getDecimalInput("Enther the actual hours.");
			Integer difficulty = getIntInput("Ener the project difficulty (1-5)");
			String notes = getStringInput("Enter the project notes.");
			
			Project project = new Project();
			
			project.setProjectId(project_id);
			project.setProjectName(projectName);
			project.setEstimatedHours(estimatedHours);
			project.setActualHours(actualHours);
			project.setDifficulty(difficulty);
			project.setNotes(notes);

			Project dbProject = projectService.addProject(project);
			System.out.println("You have successfully created project: " + dbProject);
		}
			/*
			 * Gets the user's input from the console and convert it to a BigDecimal.
			 * @param prompt the prompt to display on the console
			 * @return A BigDecimal value if successful
			 * @throws Dbe=Exception Thrown if an error occurs converting the number to a BigDecimal.
			 */
			private BigDecimal getDecimalInput(String prompt) {
				String input = getStringInput(prompt);
				if(Objects.isNull(input)) {
					return null;
				}

				try {
					return new BigDecimal(input).setScale(2);
				} catch(NumberFormatException e) {
					throw new DbException(input + " is not a valid decimal number.");
				}
		}
		private boolean exitMenu() {
			System.out.println("Exiting the menu.");
			return true;
			
			/*
			 * This method prints the available menu selection. It then gets the user's menu selection from
			 * the console and converst it to an int.
			 * 
			 * @return The menu selection as an int or -1 if nothing is selected.
			 */
		}
		private int getUserSelection() {
			printOperations();

			Integer input = getIntInput("Enter a menu selection");
			return Objects.isNull(input) ? -1 : input;
		}

		private Integer getIntInput(String prompt) {
			String input = getStringInput(prompt);
			
			if(Objects.isNull(input)) {
				return null;
			}

			try {
				return Integer.valueOf(input);
			} catch(NumberFormatException e) {
				throw new DbException(input + " is not a valid number.");
			}
		}

		private String getStringInput(String prompt) {
			System.out.print(prompt + ": ");
			String input = scanner.nextLine();
			
			
			return input.isBlank()? null: input.trim();
		}
	
		private void printOperations() {
			System.out.println("\nThese are the available selections. Press the enter key to quit:");

			//With Lamda expression
			operations.forEach(line->System.out.println(" " + line));
		
	    //Collection.getDescriptor();
		
	}

}
