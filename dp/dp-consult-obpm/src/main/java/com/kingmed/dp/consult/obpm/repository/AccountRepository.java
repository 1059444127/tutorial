package com.kingmed.dp.consult.obpm.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.kingmed.dp.consult.obpm.domain.Account;

public interface AccountRepository extends JpaRepository<Account, String>{

	Account findById(String id);
	
}
