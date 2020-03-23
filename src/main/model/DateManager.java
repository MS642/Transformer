package model;

import java.time.LocalDate;

public class DateManager {
    private LocalDate dueDate;
    private LocalDate finishedDate;

    public DateManager(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    //EFFECTS: returns the due Date
    public LocalDate getDueDate() {
        return dueDate;
    }

    //EFFECTS: returns the finished date
    public LocalDate getFinishedDate() {
        return finishedDate;
    }

    //MODIFIES: this
    //EFFECTS: changes the due date
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    //MODIFIES: this
    //EFFECTS: changes the finished date
    public void setFinishedDate(LocalDate finishedDate) {
        this.finishedDate = finishedDate;
    }
}
