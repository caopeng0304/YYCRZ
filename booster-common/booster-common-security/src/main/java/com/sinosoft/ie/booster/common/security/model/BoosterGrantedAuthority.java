package com.sinosoft.ie.booster.common.security.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * 改造 {@link org.springframework.security.core.authority.SimpleGrantedAuthority}
 * 增加无参构造函数，满足jackson反序列化的条件
 *
 * @author haocy
 * @since 2021/11/26
 */
public class BoosterGrantedAuthority implements GrantedAuthority {

	private static final long serialVersionUID = 1L;

	private String authority;

	public BoosterGrantedAuthority() {

	}

	public BoosterGrantedAuthority(String authority) {
		Assert.hasText(authority, "A granted authority textual representation is required" );
		this.authority = authority;
	}

	@Override
	public String getAuthority() {
		return this.authority;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof BoosterGrantedAuthority) {
			return this.authority.equals(((BoosterGrantedAuthority) obj).authority);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return this.authority.hashCode();
	}

	@Override
	public String toString() {
		return this.authority;
	}

	/**
	 * Converts authorities into a List of GrantedAuthority objects.
	 *
	 * @param authorities the authorities to convert
	 * @return a List of GrantedAuthority objects
	 */
	public static List<GrantedAuthority> createAuthorityList(String... authorities) {
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>(authorities.length);
		for (String authority : authorities) {
			grantedAuthorities.add(new BoosterGrantedAuthority(authority));
		}
		return grantedAuthorities;
	}
}
