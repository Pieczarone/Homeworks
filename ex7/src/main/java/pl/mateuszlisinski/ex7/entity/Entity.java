package pl.mateuszlisinski.ex7.entity;

public interface Entity<Id> {

    Id getId();

    void setId(Id id);
}
