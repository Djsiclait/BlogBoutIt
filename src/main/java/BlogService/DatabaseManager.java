/**
 * Created by Siclait on 30/05/2016.
 */
package BlogService;

import Entity.*;

import Entity.Comment;
import Entity.User;

import java.util.*;

public class DatabaseManager {

    private DatabaseManager(){

    }

    public static void BootUP(){

        List<User> Users = UserServices.getInstance().FindAll();

        if(Users.size() == 0) {
            System.out.println("Creating Admin ...");

            UserServices.getInstance().Create(new User("admin", "Administrador", "admin", true, true));
            UserServices.getInstance().Create(new User("Djsiclait", "Djidjelly Siclait", "1234", true, true));
            UserServices.getInstance().Create(new User("Wardo", "Eduardo Veras", "1234", true, true));
            UserServices.getInstance().Create(new User("guest", "guest", "", false, false));

            ArticleServices.getInstance().Create(new Article("Original Post", "This is the websites first original post", UserServices.getInstance().Find("admin")));

            System.out.println("Admin Created Successfully!");
        }
        else
            System.out.println("Database already configured");

    }

    public static void PrintData(){

        List<User> Users = UserServices.getInstance().FindAll();

        for (User user:
             Users) {
            System.out.println("Username:" + user.getUsername() + " Password:" + user.getPassword());
        }

    }

    // Basic Query Functions
    /*
     * Article Entity Query
     */
    public static Integer CreateArticle(String title, String body, User author){

        Article article = new Article(title, body, author);

        ArticleServices.getInstance().Create(article);

        // Using body as a pseudo-key given it is a unique attribute
        List<Article> articles = GetAllArticles();

        for (Article art:
             articles) {
            if(art.getBody().equals(body))
                article = art;
        }

        return article.getId();
    }

    public static Article FetchArticle(Integer id){

        return ArticleServices.getInstance().Find(id);
    }

    public static void LikeArticle(int id){

        Article article = ArticleServices.getInstance().Find(id);

        article.setLikes(article.getLikes() + 1);

        ArticleServices.getInstance().Edit(article);

    }

    public static void DislikeArticle(int id){

        Article article = ArticleServices.getInstance().Find(id);

        article.setDislikes(article.getDislikes() + 1);

        ArticleServices.getInstance().Edit(article);

    }

    public static void DeleteArticle(int id){

        Article article = ArticleServices.getInstance().Find(id);

        ArticleServices.getInstance().Delete(article);
    }

    public static void EditArticle(int id, String title, String body){

        Article article = ArticleServices.getInstance().Find(id);

        article.setTitle(title);
        article.setBody(body);
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date date = new java.sql.Date(utilDate.getTime());
        article.setModified(date);

        ArticleServices.getInstance().Edit(article);
    }

    // TODO: Modify this function to be able to search by Tag
    /*public static ArrayList<Article> SearchArchivesBy(Article article, String category){

        return (ArrayList<Article>) ArticleQuery(article, category);
    }*/

    public static List<Article> GetAllArticles(){

        return ArticleServices.getInstance().FindAll();
    }

    public static ArrayList<Article> GetArticlesOnPage(int pageNum){
        ArrayList<Article> sample = new ArrayList<>();
        List<Article> archive = ArticleServices.getInstance().FindAll();
        int count = 1;

        Collections.sort(archive, new Comparator<Article>() {
            @Override
            public int compare(Article o1, Article o2) {
                return o2.getModified().compareTo(o1.getModified());
            }
        });

        if(pageNum <= Math.ceil(archive.size()/2))
        {
            for (Article article:
                 archive) {
                if(count >= (pageNum - 1) * 2  && count <= pageNum * 2)
                    sample.add(article);

                count++;
            }
        }
        else
            return null;

        return sample;
    }

    public static ArrayList<Article> SearchArticlesByTag(String tag){
        ArrayList<Article> sample = new ArrayList<>();
        List<Article> archive = ArticleServices.getInstance().FindAll();

        Collections.sort(archive, new Comparator<Article>() {
            @Override
            public int compare(Article o1, Article o2) {
                return o2.getModified().compareTo(o1.getModified());
            }
        });

        for (Article article:
                archive) {
            for (Tag t:
                 article.getTags()) {
                if(t.getTag().equals(tag)) {
                    sample.add(article);
                    break;
                }
            }
        }

        return sample;
    }

    /*
     * User Entity Queries
     */
    public static void CreateUser(String username, String name, String password, boolean admin, boolean author){

        User user = new User(username, name, password, admin, author);

        UserServices.getInstance().Create(user);
    }

    public static boolean isUsernameTaken(String username) {

        User user = UserServices.getInstance().Find(username);

        if(user == null) // User does not exist
            return false;
        else
            return true;
    }

    public static void DeleteUser(String username){

        User user = UserServices.getInstance().Find(username);

        UserServices.getInstance().Delete(user);
    }

    // TODO: Discuss if function is really necessary
    public static void MakeAdmin(String username){

        User user = UserServices.getInstance().Find(username);

        if(!user.isAdmin()) {
            System.out.println("Making new admin ...");
            user.setAdmin(true);
            UserServices.getInstance().Edit(user);
            System.out.println("New admin added!");
        }
        else
            System.out.println("This user is already an admin");

    }

    public static void EditUser(String username, String newName, String newPassword){

        boolean different = false;

        User user = UserServices.getInstance().Find(username);

        if(!user.getName().equals(newName)) {
            user.setName(newName);
            different = true;
        }

        if(!user.getPassword().equals(newPassword)) {
            user.setPassword(newPassword);
            different = true;
        }

        if(different) {
            UserServices.getInstance().Edit(user);System.out.println("This user is already an admin");
            System.out.println("User edited");
        }
        else
            System.out.println("No change is needed");

    }

    public static boolean isAdmin(String username){

        User user = UserServices.getInstance().Find(username);

        return user.isAdmin();
    }

    public static boolean CheckCredentials(String username, String password){

        User user = UserServices.getInstance().Find(username);

        if(user == null) // user doesn't exist
            return false;
        else if (!user.getPassword().equals(password)) // wrong password
            return false;
        else
            return true;

    }

    public static User FetchUser(String username){

        return UserServices.getInstance().Find(username);

    }

    /*
     * Comment Entity Query
    */
    public static void CreateComment(String comment, User author, Article article){

        Comment com = new Comment(comment, author, article);

        CommentServices.getInstance().Create(com);

        List<Comment> comments = CommentServices.getInstance().FindAll();

        for (Comment c:
             comments) {
            if(c.getArticle().getId() == article.getId())
                article.getComments().add(c);
        }

        ArticleServices.getInstance().Edit(article);

    }

    public static void DeleteComment(int id){

        Comment comment = CommentServices.getInstance().Find(id);

        CommentServices.getInstance().Delete(comment);
    }

    public static ArrayList<Comment> GetArticleComments(Article article){

        ArrayList<Comment> list = new ArrayList<>();

        List<Comment> comments = GetAllComments();

        for (Comment comment:
             comments) {
            if(comment.getArticle().getId() == article.getId())
              list.add(comment);
        }

        return list;
    }

    public static List<Comment> GetAllComments(){

        return CommentServices.getInstance().FindAll();
    }

    /*
     * Tag Entity Query
    */

    // TAG ARTICLE CROSS TABLE
    // TODO: Discuss if really necessary
    public static Integer CreateTag(String tag){

        Tag tg = new Tag(tag);

        TagServices.getInstance().Create(tg);

        List<Tag> tags = TagServices.getInstance().FindAll();

        for (Tag t:
                tags) {
            if(t.getTag().equals(tag))
                tg = t;
        }

        return tg.getId();
    }

    public static void ProcessTagsOnArticle(ArrayList<Tag> tags, Article article){

        List<Tag> tg = TagServices.getInstance().FindAll();

        // Do not try to understand this mess
        // It works that's all i care
        for (Tag tag:
             tags) {

            boolean newTag = true;

            for (Tag t:
                 tg) {
                if(tag.getTag().equals(t.getTag())) {
                    tag = t;
                    System.out.println("\n\n" + tag.getId() + " " + tag.getTag() + "\n\n");
                    newTag = false;
                    break;
                }
            }

            if(newTag)
                tag.setId(CreateTag(tag.getTag()));

        }

        for (Tag tag:
                tags) {
                article.getTags().add(tag);
        }

        ArticleServices.getInstance().Edit(article);

    }

    public static Set<Tag> GetAllArticleTags(int article){

        Article art = ArticleServices.getInstance().Find(article);

        return art.getTags();
    }

}
