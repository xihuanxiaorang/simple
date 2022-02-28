package top.xiaorang.simple.system.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity {
  @Id
  @GeneratedValue(generator = "ksuid")
  @GenericGenerator(
      name = "ksuid",
      strategy = "top.xiaorang.simple.system.utils.KsuidIdentifierGenerator")
  private String id;

  @CreationTimestamp private Date createdTime;

  @UpdateTimestamp private Date updatedTime;
}
