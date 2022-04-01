package com.dso.java.bdd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dso.java.bdd.entity.VaccineEntity;

/**
 * 
 * @author fargonmer
 * The default class for interface methods is public
 * All methods in the interface do not have any implementations But abstract can have
 * there cant be any other variables other than static or final. Abstract doesnot have these restrictions.
 * It can implement multiple interface but it can extends only one abstract class 
 *
 */
public interface VaccineRepository extends JpaRepository<VaccineEntity, Integer> {

}
