package za.co.zubair.model;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.io.IOException;
import java.util.Base64;

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

    @Lob
    private byte[] thumbnail;

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

    public String getThumbnail() {
        return Base64.getEncoder().encodeToString(this.thumbnail);
    }

    public void setThumbnail(MultipartFile file) throws IOException {
        this.thumbnail = file.getBytes();
    }

    public Item() {
    }

    public Item(String serialNumber, TYPE type, String description, byte[] thumbnail) {
        this.serialNumber = serialNumber;
        this.type = type;
        this.description = description;
        this.thumbnail = thumbnail;
    }
}
