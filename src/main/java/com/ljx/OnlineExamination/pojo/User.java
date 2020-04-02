package com.ljx.OnlineExamination.pojo;


import lombok.AllArgsConstructor;
        import lombok.Data;
        import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String phone;
    private String password;
}
