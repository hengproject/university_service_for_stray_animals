package ltd.hengpro.backend.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * 猫名字JavaBean --> 对应cat_name
 */
@Entity
@Data
public class CatName implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 猫id */
    @Id
    private String catId;

    /** 猫名字 */
    private String catName;

}
