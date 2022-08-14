package top.xiaorang.simple.system.entity.role;

import lombok.Getter;
import lombok.Setter;
import top.xiaorang.simple.system.entity.BaseEntity;

import javax.persistence.Entity;
import java.io.Serializable;

@Getter
@Setter
@Entity
public class SysRole extends BaseEntity implements Serializable {
    private String name;
    private String title;
}
