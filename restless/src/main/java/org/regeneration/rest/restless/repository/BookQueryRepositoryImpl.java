package org.regeneration.rest.restless.repository;

import org.regeneration.rest.restless.model.Book;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookQueryRepositoryImpl implements BookQueryRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Book> findBooksWithSubjectAndPublishedDate(LocalDate from, LocalDate to, String title) {
        final CriteriaBuilder cb = em.getCriteriaBuilder();
        final CriteriaQuery<Book> query = cb.createQuery(Book.class);

        final Root<Book> book = query.from(Book.class);
        final Path<String> titlePath = book.get("title");
        final Path<LocalDate> publishedPath = book.get("published");

        List<Predicate> predicates = new ArrayList<>();
        if (from != null) {
            if (to == null) {
                to = LocalDate.now();
            }
            predicates.add(cb.between(publishedPath, from, to));
        }
        if (!StringUtils.isEmpty(title)) {
            predicates.add(cb.like(cb.lower(titlePath), "%"+title+"%"));
        }
        query.select(book)
                .where(cb.and(predicates.toArray(new Predicate[predicates.size()])));

        return em.createQuery(query).getResultList();
    }

}
