package net.highfi.bnpparibasfortis.bookstore.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import net.highfi.bnpparibasfortis.bookstore.dtos.in.shared.BaseSearchParams;
import net.highfi.bnpparibasfortis.bookstore.entities.Account;

interface CustomizedAccountRepository {
  public List<Account> search(BaseSearchParams searchParams);
}

class CustomizedAccountRepositoryImpl implements CustomizedAccountRepository {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public List<Account> search(BaseSearchParams searchParams) {
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    CriteriaQuery<Account> criteriaQuery = criteriaBuilder.createQuery(Account.class);
    Root<Account> root = criteriaQuery.from(Account.class);
    List<Predicate> criteriaList = new ArrayList<>();

    if (searchParams != null && searchParams.getSearch() != null && !searchParams.getSearch().isEmpty()) {
      criteriaList.add(criteriaBuilder.like(root.get("rowIndex"), "%" + searchParams.getSearch() + "%"));
    }

    criteriaQuery.where(criteriaBuilder.and(criteriaList.toArray(new Predicate[0])));
    criteriaQuery.orderBy(criteriaBuilder.desc(root.get("id")));

    final TypedQuery<Account> query = entityManager.createQuery(criteriaQuery);
    if (searchParams != null
        && searchParams.getLimit() != null
        && searchParams.getLimit() > 0
        && searchParams.getOffset() != null
        && searchParams.getOffset() > -1) {
      query.setFirstResult(searchParams.getOffset());
      query.setMaxResults(searchParams.getLimit());
    }

    return query.getResultList();
  }

}

@Repository("accountRepository")
public interface AccountRepository extends JpaRepository<Account, Long>, CustomizedAccountRepository {

  Optional<Account> findByUuidOrUserNameOrEmailOrPhoneNumber(String uuid, String userName, String email,
      String phoneNumber);
}
