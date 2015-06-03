package com.codecoop.interact.web.auth;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.codecoop.interact.core.domain.FacilityStaff;
import com.codecoop.interact.core.domain.Staff;
import com.codecoop.interact.core.service.StaffService;
import com.codecoop.interact.core.util.Constants;
import com.codecoop.interact.core.util.CoreDateUtils;

public class InteractUserDetailsService implements UserDetailsService
{
	private Logger logger = Logger.getLogger(InteractUserDetailsService.class);

	private ThreadLocal<User> currentUser = new ThreadLocal<User>();
	private ThreadLocal<String> currentPassword = new ThreadLocal<String>();

	public User getCurrentUser()
	{
		return currentUser.get();
	}

	public void setCurrentUser(User user)
	{
		currentUser.set(user);
	}

	public String getPassword()
	{
		return currentPassword.get();
	}

	public void setPassword(String password)
	{
		currentPassword.set(password);
	}

	@Autowired
	StaffService staffService;

	//	private String userRole="ROLE_ADMIN";
	private Date relevingDate=null;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		Staff staff = staffService.findStaffByUserName(username);
		List<GrantedAuthority> authorities=null;
		if(staff != null){
			if(username.equalsIgnoreCase("guest")){
				authorities = buildUserAuthority(Constants.GUEST_ROLE);  
			}else{
			ArrayList<FacilityStaff> facilityStaffList = new ArrayList<FacilityStaff>(staff.getFacilityStaffs());
			FacilityStaff facilityStaff = facilityStaffList.get(0);
			authorities = buildUserAuthority(facilityStaff.getStaffRoles().getRoleName());
			relevingDate = facilityStaff.getRelievingDate();
			}
			return buildUserForAuthentication(staffService.findStaffByUserName(username), authorities);
		}
		throw new UsernameNotFoundException("Invalid User");
	}

	// Converts com.codecoop.interact.core.domain users to
	// org.springframework.security.core.userdetails.User

	public User buildUserForAuthentication(Staff user, 
			List<GrantedAuthority> authorities) {
		
		Date today = CoreDateUtils.parseDate(CoreDateUtils.parseStringDate(new Date(), "yyyy-MM-dd"),"yyyy-MM-dd");
		if(relevingDate !=null){
		 relevingDate = CoreDateUtils.parseDate(CoreDateUtils.parseStringDate(relevingDate, "yyyy-MM-dd"),"yyyy-MM-dd");
		}
		User userDb = new User(user.getUsername(), user.getPasswd(), 
				relevingDate == null || relevingDate.compareTo(today) >0 ? true : false, true, true, true, authorities);

		currentUser.set(userDb);
		return userDb;
	}

	public List<GrantedAuthority> buildUserAuthority(String userRoles) {
		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
		// Build user's authorities
		setAuths.add(new SimpleGrantedAuthority(userRoles));
		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);
		return Result;
	}

}
