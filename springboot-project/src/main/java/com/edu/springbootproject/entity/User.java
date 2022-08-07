package com.edu.springbootproject.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

  private String userId;
  private String userName;
  private String userPwd;
//  private String userRealname;
//  private String userImg;
}
