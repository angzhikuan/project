package com.edu.springbootproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User1 {

  private long userId;
  private String userName;
  private String userPwd;
//  private String userRealname;
//  private String userImg;


}
