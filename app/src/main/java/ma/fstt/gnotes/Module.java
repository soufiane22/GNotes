package ma.fstt.gnotes;

public class Module {
    String label;
    double note;

    public Module(String label, double note) {
        this.label = label;
        this.note = note;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }
}
