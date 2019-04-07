package cn.com.sshdemo.domain;

/**
 * @Author: oyc
 * @Date: 2019-04-07 16:25
 * @Description:
 */
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Table(name="user")
@Entity
@Data
public class User {

    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private Integer id;
    private String username;
    private String password;
}
