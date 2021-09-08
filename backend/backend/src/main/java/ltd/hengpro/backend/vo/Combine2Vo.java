package ltd.hengpro.backend.vo;

import lombok.Data;

@Data
public class Combine2Vo<U,V> {
    U u;
    V v;

    public Combine2Vo(U u, V v) {
        this.u = u;
        this.v = v;
    }
}
