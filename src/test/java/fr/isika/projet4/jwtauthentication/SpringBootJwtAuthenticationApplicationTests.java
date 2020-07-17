//package fr.isika.projet4.jwtauthentication;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//
//import java.util.HashSet;
//import java.util.Set;
//
//import org.hibernate.Session;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import fr.isika.projet4.jwtauthentication.model.FavoriteArticle;
//import fr.isika.projet4.jwtauthentication.model.User;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class SpringBootJwtAuthenticationApplicationTests {
//	
//	private Session session;
//
////	@Test
////	public void contextLoads() {
////	}
//	
//	@Test
//    public void ManyToManyRelationship() {
//		
//		User user1 = new User("Noah");
//		User user2 = new User("Axel");
//		Set<User> users = new HashSet<User>();
//		users.add(user1);
//		users.add(user2);
//		
////		FavoriteArticle article1 = new FavoriteArticle("Cloning Pokemon");
////		FavoriteArticle article2 = new FavoriteArticle("Study about Yo-kai's DNA");
////		FavoriteArticle article3 = new FavoriteArticle("SonGoku's cells migration");
//		
//		Set<FavoriteArticle> favoriteArticles = new HashSet<FavoriteArticle>();
////		favoriteArticles.add(article1);
////		favoriteArticles.add(article2);
////		favoriteArticles.add(article3);
//		
//		
//		
//		 assertEquals(0, user1.getFavoriteArticles().size());
//		 user1.setFavoriteArticles(favoriteArticles);
//         session.persist(user1);
//
//         assertNotNull(user1);
//		
//    }
// 
////    @Test
////    public void givenSession_whenRead_thenReturnsMtoMdata() {
////        @SuppressWarnings("unchecked")
////        List<Employee> employeeList = session.createQuery("FROM Employee")
////          .list();
//// 
////        assertNotNull(employeeList);
//// 
////        for(Employee employee : employeeList) {
////            assertNotNull(employee.getProjects());
////        }
////    }
//
//}
