package com.atlanticyu.loganalysisback.model.entity;

import javax.persistence.*;

@Entity   //告诉JPA这是一个实体类（和数据表映射）
//@table可指定表，可省略，默认为类名字小写
public class Role {
    @Id  //主键
    @GeneratedValue  //(strategy = GenerationType.IDENTITY)  可表示自增主键
    private Long id;
    //@Column  //（name=   ）省略默认列名就是属性名
    private String rolename;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
}
