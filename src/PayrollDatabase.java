/**
 * PayrollDatabase.java - Handles saving and loading data to/from files
 *
 * This file is responsible for PERSISTENCE - keeping our data even after program closes
 *
 * WHY DO WE NEED THIS?
 * Without this, all employees would disappear when program exits!
 * With this, we save employees to files and load them back when program restarts
 *
 * ALL METHODS ARE STATIC because:
 * - We don't need multiple database objects
 * - We can call methods directly like PayrollDatabase.saveEmployees(list)
 * - It acts like a utility class (no state to maintain)
 */

import java.io.*;      // For file operations (ObjectOutputStream, File, etc.)
import java.util.*;    // For ArrayList

public class PayrollDatabase {

    // ===== FILE PATHS (CONSTANTS) =====
    // These are the actual files where we store our data
    // 'static final' makes them CONSTANTS - value never changes
    private static final String EMPLOYEE_FILE = "employees.dat";  // Binary file for employee data
    private static final String PAYSLIP_FILE = "payslips.dat";    // Text file for pay slip history

    // .dat extension = "data file" (could be anything, just convention)

    /**
     * Saves ALL employees to a binary file
     *
     * HOW IT WORKS:
     * 1. Creates a file output stream to write to "employees.dat"
     * 2. Wraps it in ObjectOutputStream (allows writing Java objects)
     * 3. Writes the entire ArrayList as ONE object
     * 4. If error occurs, prints error message (doesn't crash program)
     *
     * WHY USE TRY-WITH-RESOURCES?
     * - Automatically closes the file (no need for finally block)
     * - Even if exception occurs, file gets closed
     *
     * @param employees - The ArrayList of employees to save
     */
    public static void saveEmployees(ArrayList<Employee> employees) {
        // try-with-resources: creates streams and auto-closes them
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(EMPLOYEE_FILE))) {

            // Write the entire ArrayList to file as one object
            // This works because Employee implements Serializable
            oos.writeObject(employees);

            System.out.println("✅ Employees saved to database");

        } catch (IOException e) {
            // If anything goes wrong (disk full, no permissions, etc.)
            System.out.println("❌ Error saving employees: " + e.getMessage());
        }
    }

    /**
     * Loads ALL employees from the binary file
     *
     * HOW IT WORKS:
     * 1. Checks if file exists (if not, returns empty list)
     * 2. Opens file for reading
     * 3. Reads the object from file
     * 4. Casts it back to ArrayList<Employee>
     * 5. Returns the loaded list
     *
     * @SuppressWarnings("unchecked") - Tells compiler "I know this cast is risky but I'm sure it's safe"
     *
     * @return ArrayList<Employee> - Loaded employees OR empty list if file doesn't exist
     */
    @SuppressWarnings("unchecked")  // Suppresses warning about casting Object to ArrayList<Employee>
    public static ArrayList<Employee> loadEmployees() {

        // Create File object to check if file exists
        File file = new File(EMPLOYEE_FILE);

        // If no saved file exists yet (first time running program)
        if (!file.exists()) {
            return new ArrayList<>();  // Return empty list (no employees loaded)
        }

        // File exists, so try to load it
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {

            // Read the object from file and cast it to ArrayList<Employee>
            // The @SuppressWarnings tells compiler we're sure about the cast
            return (ArrayList<Employee>) ois.readObject();

        } catch (IOException | ClassNotFoundException e) {
            // IOException - File read error
            // ClassNotFoundException - Employee class definition changed
            System.out.println("❌ Error loading employees: " + e.getMessage());
            return new ArrayList<>();  // Return empty list on error
        }
    }

    /**
     * Saves a SINGLE pay slip to a text file (appends to file)
     *
     * HOW IT WORKS:
     * 1. Opens file in APPEND mode (true parameter means add to end, don't overwrite)
     * 2. Wraps in BufferedWriter for efficiency
     * 3. Wraps in PrintWriter for easy println() method
     * 4. Writes the pay slip using its toString() method
     *
     * WHY TEXT FILE FOR PAYSLIPS BUT BINARY FOR EMPLOYEES?
     * - Employees: Need to reconstruct exact objects (binary is better)
     * - Pay slips: Just need to read as history (text is human-readable)
     *
     * @param slip - The PaySlip object to save
     */
    public static void savePaySlip(PaySlip slip) {
        // FileWriter with true = APPEND mode (adds to end of file)
        // Without true, it would overwrite the file each time
        try (FileWriter fw = new FileWriter(PAYSLIP_FILE, true);
             BufferedWriter bw = new BufferedWriter(fw);    // Adds buffering for performance
             PrintWriter out = new PrintWriter(bw)) {      // Gives us println() method

            // Write the slip (automatically calls slip.toString())
            out.println(slip);

        } catch (IOException e) {
            System.out.println("❌ Error saving payslip: " + e.getMessage());
        }
    }
}

// ===== HOW FILE STREAMS WORK =====
//
// SAVING (ObjectOutputStream):
// ArrayList → ObjectOutputStream → FileOutputStream → employees.dat
//
// LOADING (ObjectInputStream):
// employees.dat → FileInputStream → ObjectInputStream → ArrayList
//
// WHY MULTIPLE LAYERS?
// FileOutputStream: Writes BYTES to file
// ObjectOutputStream: Writes JAVA OBJECTS as bytes (handles object graph)
//
// BufferedWriter: Collects data in memory before writing (faster, fewer disk operations)
// PrintWriter: Gives convenient methods like println()