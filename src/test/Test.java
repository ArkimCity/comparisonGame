package test;

import java.util.ArrayList;

import javax.persistence.EntityManager;

import org.hibernate.mapping.Array;

import probono.model.util.PublicCommon;

public class Test {

	public static void main(String[] args) {
		EntityManager em = PublicCommon.getEntityManger();
		ArrayList a = (ArrayList)em.createNativeQuery("select * from probono_project").getResultList();
		
		System.out.println(a);
		em.close();
	}

}
