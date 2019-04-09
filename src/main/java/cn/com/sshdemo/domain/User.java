package cn.com.sshdemo.domain;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author: oyc
 * @Date: 2019-04-07 16:25
 * @Description:
 */
@Table(name = "user")
@Entity
@Data
public class User implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;
    private String username;
    private String password;
}
