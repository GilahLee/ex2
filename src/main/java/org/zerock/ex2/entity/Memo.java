package org.zerock.ex2.entity;

import lombok.ToString;

import javax.annotation.Generated;
import javax.persistence.*;

@Entity
@Table(name="tbl_memo")
@ToString
public class Memo {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long mno;

    @Column(length=200, nullable=false)
    private String memoText;

}