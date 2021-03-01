package com.I2I.healthCare.Config;

import java.util.Objects;
import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * SystemLoggedInUser is used to update login user.
 * 
 * @author Subash_S
 * @since 21.02
 *
 */
@Component
public class UserConfig implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {
		String name = "User001";
		if (Objects.nonNull(SecurityContextHolder.getContext())
				&& Objects.nonNull(SecurityContextHolder.getContext().getAuthentication())
				&& SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
			name = SecurityContextHolder.getContext().getAuthentication().getName();
		}
		return Optional.ofNullable(name);
	}

	public String getUserName() {
		Optional<String> currentAuditor = getCurrentAuditor();
		if (!currentAuditor.isEmpty() && currentAuditor.isPresent()) {
			return currentAuditor.get();
		} else {
			return "";
		}
	}

}