package ltd.hengpro.backend.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class SiteStatistics {
    @Id
    private Integer Id;

    private Long userNum;

    private Long catNum;
}
