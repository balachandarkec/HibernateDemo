import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import in.stack.hibernate.model.Product;
import in.stack.hibernate.util.HibernateUtil;

public class Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Transaction trans=null;
		
		Product p=new Product("Mango",50d,50);
		Product p1=new Product("papaya",50d,40);
		Product p2=new Product("JackFruit",50d,70);
		
		try(Session session=HibernateUtil.getSessionFactory().openSession()){
			trans=session.beginTransaction();
			session.save(p);
			session.save(p1);
			session.save(p2);
			
		 trans.commit();
		}catch(Exception e) {
			if(trans !=null) {
				trans.rollback();
			}
			e.printStackTrace();
		}
		
		
		// Fetch data from hibernate 
		
		try(Session session=HibernateUtil.getSessionFactory().openSession()){
			List <Product> products=session.createQuery("FROM Product", Product.class).list();
			
			products.forEach( product ->System.out.println(product.toString()));
			
		}catch(Exception e) {
			if(trans !=null) {
				trans.rollback();
			}
			e.printStackTrace();
		}
		
		
		

	}

}
