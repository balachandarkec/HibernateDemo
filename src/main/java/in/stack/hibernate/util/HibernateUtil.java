package in.stack.hibernate.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;



public class HibernateUtil {
	
	private static StandardServiceRegistry registry;
	private static SessionFactory sf;
	
	public static SessionFactory getSessionFactory() {
		try {
		
		if(sf==null) {
			registry=new StandardServiceRegistryBuilder().configure().build();
			MetadataSources sources= new MetadataSources(registry);
			
			Metadata metadata=sources.getMetadataBuilder().build();
			
			sf=metadata.getSessionFactoryBuilder().build();
		}
		}
		catch(Exception e) {
			e.printStackTrace();
			if(registry!=null) {
				StandardServiceRegistryBuilder.destroy(registry);
			}
		}
		
		return sf;
	}
	
	
	public static void clearRegistry() {
		if(registry!=null) {
			StandardServiceRegistryBuilder.destroy(registry);
		}
	}

}
