package com.dss.entity.token;

import com.dss.entity.user.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "DSS_TOKEN")
@NoArgsConstructor
@AllArgsConstructor
public class UsersToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TOKEN_ID")
    private Long tokenId;

    @Column(name = "ACCESS_TOKEN", length = 1024)
    private String accessToken;

    @Column(name = "REFRESH_TOKEN", length = 1024)
    private String refreshToken;

    @ManyToOne
    private Users user;
}
