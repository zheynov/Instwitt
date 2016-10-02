package by.redlaw.dao.post.impl;

import by.redlaw.dao.post.PostDao;
import by.redlaw.entity.Post;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.List;
import java.util.Locale;

/**
 * Created by Redlaw on 08.09.2016.
 */

@Repository("postDaoImpl")
@Transactional
public class PostDaoImpl implements PostDao {

    private final Logger LOGGER = Logger.getLogger(getClass());

    @Autowired
    private MessageSource messageSource;

    @Resource(name = "sessionFactory")
    public SessionFactory sessionFactory;

    public Post createPost(Post post) {
        sessionFactory.getCurrentSession().save(post);
        LOGGER.info(messageSource.getMessage("dao.post.save", new Object[]{post}, Locale.ENGLISH));
        return post;
    }

    public Post getPostById(Long postId) {
        Post post = sessionFactory.getCurrentSession().get(Post.class, postId);
        LOGGER.info(messageSource.getMessage("dao.post.getById", new Object[]{postId}, Locale.ENGLISH));
        return post;
    }

    public void updatePost(Post post) {
        sessionFactory.getCurrentSession().update(post);
        LOGGER.info(messageSource.getMessage("dao.post.update", new Object[]{post}, Locale.ENGLISH));
    }

    public void deletePost(Post post) {
        Post mergedPost = (Post) sessionFactory.getCurrentSession().merge(post);
        sessionFactory.getCurrentSession().delete(mergedPost);
        LOGGER.info(messageSource.getMessage("dao.post.delete", new Object[]{post}, Locale.ENGLISH));
    }

    // Descending orderr
    @Override
    public List<Post> getAllThePosts() {

        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Post.class);
        criteria.addOrder(Order.desc("postDate"));

        List result = criteria.list();
        LOGGER.info(messageSource.getMessage("dao.post.getAll", new Object[]{result}, Locale.ENGLISH));

        return result;
    }
}

