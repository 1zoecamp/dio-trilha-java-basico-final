package me.dio.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.dio.domain.model.User;

@Repository //na prática não precisa, só de estender JpaRepository o Spring já entende
public interface UserRepository extends JpaRepository<User, Long>{
	
	//String Jpa já faz essa verificação
	boolean existsByAccountNumber(String accountNumber);
}
