package ltd.hengpro.backend.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Data
public class FileRecord implements Serializable
{

    private static final long serialVersionUID = 1L;

    @Id
    private String fileId;

    private String fileAdd;
}
