package exercise.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import exercise.dto.ProductParamsDTO;
import exercise.model.Product;

// BEGIN
@Component // Для возможности автоматической инъекции
public class ProductSpecification {
    // Генерация спецификации на основе параметров внутри DTO
    // Для удобства каждый фильтр вынесен в свой метод
    public Specification<Product> build(ProductParamsDTO params) {
        return withCategoryId(params.getCategoryId())
                .and(withPriceGt(params.getPriceGt()))
                .and(withPriceLs(params.getPriceLt()))
                .and(withRatingGt(params.getRatingGt()))
                .and(withTitleCont(params.getTitleCont()));
    }

    private Specification<Product> withCategoryId(Long category) {
        return (root, query, cb) -> category == null ? cb.conjunction() : cb.equal(root.get("category").get("id"), category);
    }

    private Specification<Product> withPriceGt(Integer price) {
        return (root, query, cb) -> price == null ? cb.conjunction() : cb.greaterThan(root.get("price"), price);
    }

    private Specification<Product> withPriceLs(Integer price) {
        return (root, query, cb) -> price == null ? cb.conjunction() : cb.lessThan(root.get("price"), price);
    }

    private Specification<Product> withRatingGt(Double rating) {
        return (root, query, cb) -> rating == null ? cb.conjunction() : cb.greaterThan(root.get("rating"), rating);
    }

    private Specification<Product> withTitleCont(String title) {
        return (root, query, cb) -> title == null ? cb.conjunction() : cb.like(root.get("title"), title);
    }
    // Остальные методы
}
// END
