/**
 * Created by Siclait on 7/6/16.
 */
package BlogService;

import Entity.Article;

public class ArticleServices extends ORManager<Article>{

    private static ArticleServices instance;

    private ArticleServices(){
        super(Article.class);
    }

    public static ArticleServices getInstance(){
        if(instance == null)
            instance = new ArticleServices();

        return instance;
    }

}
