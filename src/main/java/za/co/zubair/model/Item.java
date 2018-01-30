package za.co.zubair.model;

import javax.persistence.*;
import java.sql.Blob;

@Entity
public class Item {
    public enum TYPE {
        CPU, GPU, MEMORY, MOTHERBOARD, VISUAL, AUDIO, STORAGE, PERIPHERAL
    }

    @Id
    private String serialNumber;

    @Enumerated(EnumType.STRING)
    private TYPE type;

    private String description;

    private Blob thumbnail;

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public TYPE getType() {
        return type;
    }

    public void setType(TYPE type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Blob getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Blob thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Item() {
    }

    public Item(String serialNumber, TYPE type, String description, Blob thumbnail) {
        this.serialNumber = serialNumber;
        this.type = type;
        this.description = description;
        this.thumbnail = thumbnail;
    }
}
