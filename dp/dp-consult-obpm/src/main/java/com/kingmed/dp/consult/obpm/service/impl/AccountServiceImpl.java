package com.kingmed.dp.consult.obpm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.kingmed.dp.consult.obpm.domain.Account;
import com.kingmed.dp.consult.obpm.repository.AccountRepository;
import com.kingmed.dp.consult.obpm.service.AccountService;

@Component("accountService")
@Transactional
public class AccountServiceImpl implements AccountService {
	@Autowired
	private AccountRepository accountRepository;

	@Override
	public Account findById(String id) {
		return this.accountRepository.findById(id);
	}
	
}
