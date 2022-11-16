/**
 * @author Glen Mark T Anduiza
 * @version 1.0
 * @since 10/31/2022
 */

package com.dss.security;

import com.dss.entity.user.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * This class implements the UserDetails interface which provides core user information.
 * Implementations are not used directly by Spring Security for security purposes.
 * They simply store user information which is later encapsulated into Authentication objects.
 */

public class DssUserDetailsImpl implements UserDetails {

    private Users user;

    public DssUserDetailsImpl(Users user) {
        super();
        this.user = user;
    }

    /** Returns the authorities granted to the user. Cannot return null.
     * @return the authorities, sorted by natural key (never null)
     * @see #getAuthorities()
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(user.getUserRoles().get(0).getUserRole()));
    }

    /** Returns the password used to authenticate the user.
     * @return the password
     * @see #getPassword()
     */
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    /** Returns the username used to authenticate the user. Cannot return null.
     * @return the username (never null)
     * @see #getUsername()
     */
    @Override
    public String getUsername() {
        return user.getEmail();
    }

    /** Indicates whether the user's account has expired. An expired account cannot be authenticated.
     * In this project, I set the return value to true.
     * @return true if the user's account is valid (ie non-expired), false if no longer valid (ie expired)
     * @see #isAccountNonExpired()
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /** Indicates whether the user is locked or unlocked. A locked user cannot be authenticated.
     * In this project, I set the return value to true.
     * @return true if the user is not locked, false otherwise
     * @see #isAccountNonLocked()
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /** Indicates whether the user's credentials (password) has expired. Expired credentials prevent authentication.
     * In this project, I set the return value to true.
     * @return true if the user's credentials are valid (ie non-expired), false if no longer valid (ie expired)
     * @see #isCredentialsNonExpired()
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /** Indicates whether the user is enabled or disabled. A disabled user cannot be authenticated.
     * In this project, I set the return value to true.
     * @return true if the user is enabled, false otherwise
     * @see #isEnabled()
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
