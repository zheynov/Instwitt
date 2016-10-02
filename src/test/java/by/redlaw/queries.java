package by.redlaw;

import by.redlaw.entity.Post;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import java.util.List;

/**
 * Created by Redlaw on 12.09.2016.
 */
public class queries {


    public static void main(String[] args) {


        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();


        Criteria criteria = session.createCriteria(Post.class)
                .addOrder(Order.asc("postDate"));

        List<Post> posts = criteria.list();

        session.getTransaction().commit();

        for (Post post : posts) {
            System.out.print(post.getPostDate() + " ");
            System.out.print(post.getUserID().getLogin() + " ");
            System.out.print(post.getText()  + " ");
            System.out.println();
        }

        session.close();
    }
}
