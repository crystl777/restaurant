package repository.dish;

import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface DishRepository {
}
