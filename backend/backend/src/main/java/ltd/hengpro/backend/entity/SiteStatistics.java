package ltd.hengpro.backend.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class SiteStatistics {
    @Id
    private Integer Id;

    private Integer userNum;

    private Integer catNum;
}
