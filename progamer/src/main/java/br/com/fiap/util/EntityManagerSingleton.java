package br.com.fiap.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerSingleton {
	
		private static EntityManagerFactory fabrica;
		
		private EntityManagerSingleton() {}
		
		public static EntityManagerFactory getInstance() {
			if(fabrica == null) {
				fabrica = Persistence.createEntityManagerFactory("progamer-persistence-unit");
			}
			
			return fabrica;
		}
	
}
