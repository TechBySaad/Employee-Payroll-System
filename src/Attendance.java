/**
 * Attendance.java - Tracks employee attendance, leaves, and overtime
 *
 * This file is responsible for:
 * - Marking if an employee was present or absent on a specific date
 * - Tracking overtime hours worked
 * - Managing sick leave and casual leave balances
 * - Calculating overtime pay (1.5x hourly rate)
 */

import java.time.LocalDate;  // For working with dates (YYYY-MM-DD)
import java.util.HashMap;     // For storing attendance by date
import java.util.Map;         // Interface for our HashMaps

public class Attendance {

    // We're using HashMap because: each date can have only ONE attendance status
    // Key = Date, Value = true(present) or false(absent)
    private Map<LocalDate, Boolean> attendance;

    // Separate map for overtime because: employee can be present AND work overtime
    // Key = Date, Value = hours of overtime worked
    private Map<LocalDate, Integer> overtimeHours;

    private int employeeId;      // Which employee this attendance belongs to
    private int sickLeaves;      // Remaining sick leave balance (starts at 12 days per year)
    private int casualLeaves;    // Remaining casual leave balance (starts at 15 days per year)

    // CONSTRUCTOR - Called when we create attendance tracking for a new employee
    public Attendance(int employeeId) {
        this.employeeId = employeeId;
        this.attendance = new HashMap<>();      // Empty map, ready to track attendance
        this.overtimeHours = new HashMap<>();   // Empty map, ready to track overtime
        this.sickLeaves = 12;    // Company policy: 12 sick days per year
        this.casualLeaves = 15;  // Company policy: 15 casual/vacation days per year
    }

    // Mark if employee was present or absent on a specific date
    // HashMap.put() adds or replaces the record for that date
    public void markAttendance(LocalDate date, boolean present) {
        attendance.put(date, present);  // Store in map: date -> present/absent
    }

    // Record overtime hours worked on a specific date
    // Example: addOvertime(2024-12-25, 3) means 3 extra hours on Christmas
    public void addOvertime(LocalDate date, int hours) {
        overtimeHours.put(date, hours);  // Store in map: date -> overtime hours
    }

    // Calculate total overtime hours across ALL dates
    // Stream API breakdown:
    // 1. overtimeHours.values() - gets all overtime hour values from the map
    // 2. stream() - converts to a stream for processing
    // 3. mapToInt(Integer::intValue) - converts Integer objects to primitive int
    // 4. sum() - adds all the overtime hours together
    // Example: If employee worked 2hrs on Monday + 3hrs on Tuesday = 5hrs total
    public int getTotalOvertime() {
        return overtimeHours.values().stream().mapToInt(Integer::intValue).sum();
    }

    // Calculate overtime payment based on employee's hourly rate
    // Company policy: Overtime is paid at 1.5x normal hourly rate (time and a half!)
    // Example: 5 overtime hours × $100/hr × 1.5 = $750
    public double getOvertimePay(double hourlyRate) {
        return getTotalOvertime() * hourlyRate * 1.5;
    }

    // Count how many days employee was absent
    // Stream API breakdown:
    // 1. attendance.values() - gets all present/absent values
    // 2. stream() - creates stream
    // 3. filter(present -> !present) - keeps only false/absent records
    // 4. count() - counts how many absent days
    // Example: If marked present for 20 days and absent for 2 days, returns 2
    public int getAbsentDays() {
        return (int) attendance.values().stream().filter(present -> !present).count();
    }

    // Use sick leave days (deduct from available balance)
    // Only deducts if enough balance is available
    // In real system, you'd also check if employee is actually sick
    public void useSickLeave(int days) {
        if (sickLeaves >= days) {     // Check if enough balance
            sickLeaves -= days;        // Deduct from balance
        }
        // Else: Not enough leaves - in real system, would deduct from salary
    }

    // Use casual/vacation leave days (deduct from available balance)
    public void useCasualLeave(int days) {
        if (casualLeaves >= days) {   // Check if enough balance
            casualLeaves -= days;      // Deduct from balance
        }
    }

    // GETTER METHODS - to access private properties from outside
    public int getSickLeaves() { return sickLeaves; }
    public int getCasualLeaves() { return casualLeaves; }
    public int getEmployeeId() { return employeeId; }
}