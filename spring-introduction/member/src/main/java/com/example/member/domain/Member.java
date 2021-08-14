package com.example.member.domain;

import javax.persistence.*;

@Entity // jpa가 관리하는 엔티티가 됨
public class Member {
    @Id // pk 매핑
    /* strategy = GenerationType.IDENTITY
    쿼리에 id값을 직접 넣는 것이 아니라, db에 다른 필드 값을 넣으면 자동으로 해당 id 값 생성 */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @Column(name="username")    // db의 컬럼명(username)과 해당 필드이름(name)이 다를 때 매핑
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
