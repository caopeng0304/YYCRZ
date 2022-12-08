package com.sinosoft.ie.booster.common.security.model;

import lombok.Getter;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.*;

/**
 * 改造 {@link org.springframework.security.core.userdetails.User}
 * 增加自定义字段，并增加无参构造函数，满足jackson反序列化的条件
 *
 * @author haocy
 * @since 2021/11/26
 */
public class BoosterUser implements UserDetails, CredentialsContainer {

	private static final long serialVersionUID = 1L;

	private String password;

	private String username;

	private Set<GrantedAuthority> authorities;

	private boolean accountNonExpired;

	private boolean accountNonLocked;

	private boolean credentialsNonExpired;

	private boolean enabled;

	/**
	 * 用户ID
	 */
	@Getter
	private Long id;

	/**
	 * 部门ID
	 */
	@Getter
	private Long deptId;

	public BoosterUser() {

	}

	public BoosterUser(Long id, Long deptId, String username, String password, boolean enabled, boolean accountNonExpired,
					   boolean credentialsNonExpired, boolean accountNonLocked,
					   Collection<? extends GrantedAuthority> authorities) {
		this(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.id = id;
		this.deptId = deptId;
	}

	/**
	 * Construct the <code>BoosterUser</code> with the details required by
	 * {@link org.springframework.security.authentication.dao.DaoAuthenticationProvider}.
	 *
	 * @param username              the username presented to the
	 *                              <code>DaoAuthenticationProvider</code>
	 * @param password              the password that should be presented to the
	 *                              <code>DaoAuthenticationProvider</code>
	 * @param enabled               set to <code>true</code> if the user is enabled
	 * @param accountNonExpired     set to <code>true</code> if the account has not expired
	 * @param credentialsNonExpired set to <code>true</code> if the credentials have not
	 *                              expired
	 * @param accountNonLocked      set to <code>true</code> if the account is not locked
	 * @param authorities           the authorities that should be granted to the caller if they
	 *                              presented the correct username and password and the user is enabled. Not null.
	 * @throws IllegalArgumentException if a <code>null</code> value was passed either as
	 *                                  a parameter or as an element in the <code>GrantedAuthority</code> collection
	 */
	public BoosterUser(String username, String password, boolean enabled, boolean accountNonExpired,
					   boolean credentialsNonExpired, boolean accountNonLocked,
					   Collection<? extends GrantedAuthority> authorities) {
		Assert.isTrue(username != null && !"".equals(username) && password != null,
				"Cannot pass null or empty values to constructor" );
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.accountNonExpired = accountNonExpired;
		this.credentialsNonExpired = credentialsNonExpired;
		this.accountNonLocked = accountNonLocked;
		this.authorities = Collections.unmodifiableSet(sortAuthorities(authorities));
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.credentialsNonExpired;
	}

	@Override
	public void eraseCredentials() {
		this.password = null;
	}

	private static SortedSet<GrantedAuthority> sortAuthorities(Collection<? extends GrantedAuthority> authorities) {
		Assert.notNull(authorities, "Cannot pass a null GrantedAuthority collection" );
		// Ensure array iteration order is predictable (as per
		// UserDetails.getAuthorities() contract and SEC-717)
		SortedSet<GrantedAuthority> sortedAuthorities = new TreeSet<>(new BoosterUser.AuthorityComparator());
		for (GrantedAuthority grantedAuthority : authorities) {
			Assert.notNull(grantedAuthority, "GrantedAuthority list cannot contain any null elements" );
			sortedAuthorities.add(grantedAuthority);
		}
		return sortedAuthorities;
	}

	/**
	 * Returns {@code true} if the supplied object is a {@code BoosterUser} instance with the
	 * same {@code username} value.
	 * <p>
	 * In other words, the objects are equal if they have the same username, representing
	 * the same principal.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof BoosterUser) {
			return this.username.equals(((BoosterUser) obj).username);
		}
		return false;
	}

	/**
	 * Returns the hashcode of the {@code username}.
	 */
	@Override
	public int hashCode() {
		return this.username.hashCode();
	}

	@Override
	public String toString() {
		return getClass().getName() + " [" +
				"Username=" + this.username + ", " +
				"Password=[PROTECTED], " +
				"Enabled=" + this.enabled + ", " +
				"AccountNonExpired=" + this.accountNonExpired + ", " +
				"credentialsNonExpired=" + this.credentialsNonExpired + ", " +
				"AccountNonLocked=" + this.accountNonLocked + ", " +
				"Granted Authorities=" + this.authorities + "]";
	}

	private static class AuthorityComparator implements Comparator<GrantedAuthority>, Serializable {

		private static final long serialVersionUID = 1L;

		@Override
		public int compare(GrantedAuthority g1, GrantedAuthority g2) {
			// Neither should ever be null as each entry is checked before adding it to
			// the set. If the authority is null, it is a custom authority and should
			// precede others.
			if (g2.getAuthority() == null) {
				return -1;
			}
			if (g1.getAuthority() == null) {
				return 1;
			}
			return g1.getAuthority().compareTo(g2.getAuthority());
		}
	}
}
