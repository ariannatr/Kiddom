package kiddom.service;

/**
 * Created by Stathis on 6/12/2017.
 */
import kiddom.model.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("categoryService")
public interface CategoryService {

    /*-----------------------CRUD methods for categories----------------------*/

    public List<CategoriesEntity> getCategories();
    public  CategoriesEntity getCategoryByName(String name);

    public void saveCategory(CategoriesEntity category);
    public void saveCategory(List<CategoriesEntity> categories);

    public void update(CategoriesEntity category);


    /*-----------------------CRUD methods for subcategories----------------------*/

    public SubcategoriesEntity getSubCategoryByCategory(String category, String subcategory);

    public void saveSubCategory(CategoriesEntity category, List<SubcategoriesEntity> subcategory);
    public void saveSubCategory(CategoriesEntity category, SubcategoriesEntity subcategory);

    public void delete(SubcategoriesEntity subcategory);





}

