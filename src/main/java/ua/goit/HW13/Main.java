package ua.goit.HW13;

import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final String CREATE_USER_URL = "https://jsonplaceholder.typicode.com/users";
    private static final String CREATE_POST_URL = "https://jsonplaceholder.typicode.com/posts";

    public static void main(String[] args) throws IOException, InterruptedException {
        //Task1 - #1 add new user
        User user = createDefaultUser();
        final User createUser = HttpUtil.sendPost(URI.create(CREATE_USER_URL), user);
        System.out.println("Task1 #1 - add new user - "+createUser);

         //Task1 - #2 put user by ID=10
        User userPut = createDefaultUser();
        final User user2 = HttpUtil.sendPut(URI.create(String.format("%s/%d", CREATE_USER_URL, 10)),userPut);
        System.out.println("Task1 #2 - put user by ID=10 - "+user2);

        //Task1 - #3 delete user by ID=10
        HttpUtil.sendDelete(URI.create(String.format("%s/%d", CREATE_USER_URL, 10)));

        //Task1 - #4 users
        final List<User> users = HttpUtil.sendGetWithListOfUsers(URI.create(String.format("%s/", CREATE_USER_URL)));
        System.out.println("Task1 #4 - List users - "+users);

        //Task1 - #5 ?id=10
        final User user5 = HttpUtil.sendGet(URI.create(String.format("%s/?id=%d", CREATE_USER_URL, 10)));
        System.out.println("Task1 #5 - user by ID=10 - "+user5);

        //Task1 - #6 ?username="Bret"
        final User user6 = HttpUtil.sendGet(URI.create(String.format("%s/?username=%s", CREATE_USER_URL, "Bret")));
        System.out.println("Task1 #6 - user by username=Bret - "+user6);

        //Task2 - #1  get user's max post
        Integer userTask2 = 2;
       final List<Post> posts = HttpUtil.sendGetPosts(URI.create(String.format("%s/%d/posts", CREATE_USER_URL, userTask2)));
        Integer maxIdPost = 0;
        for (Post post : posts) {
            if (maxIdPost < post.getId()) {
                maxIdPost = post.getId();
            }
        }
        System.out.println("Task2 - User - "+userTask2+" maxIdPost - "+maxIdPost);

        //Task2 - #2 get comments max post
        final List<Comment> comments = HttpUtil.sendGetComments(URI.create(String.format("%s/%d/comments", CREATE_POST_URL, maxIdPost)));
        String jsonString = new Gson().toJson(comments);
        String nameFileJson = "user-"+userTask2+"-post-"+maxIdPost+"-comments.json";
        try (PrintWriter out = new PrintWriter(new FileWriter(nameFileJson))) {
            out.write(jsonString.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }


        //Task3- #1  get user todos where todo.isCompleted() == false
        List<Todos> todosIsCompleted= new ArrayList<Todos>();
        final List<Todos> todos = HttpUtil.sendGetTodos(URI.create(String.format("%s/%d/todos", CREATE_USER_URL, userTask2)));

        for (Todos todo : todos) {
            if (!todo.isCompleted()) {
                todosIsCompleted.add(todo);
            }
        }
       System.out.println("Task3 - "+todosIsCompleted);
    }

    private static User createDefaultUser() {
        User user = new User();
        user.setId(1);
        user.setUserName("Ju");
        user.setName("Juliya");
        user.setEmail("ju@ukr.net");
        user.setPhone("090-990-90-90");
        user.setWebsite("ju.com.ua");

        Address address = new Address();
        address.setCity("Kiev");
        address.setSuite("Suite");
        address.setZipcode("99999-9999");
        Geo geo = new Geo();
        geo.setLat(-99.9999);
        geo.setLng(99.9999);
        address.setGeo(geo);
        user.setAddress(address);

        Company company = new Company();
        company.setName("CompanyJu");
        company.setCatchPhrase("CatchPhrase");
        company.setBs("Bs");
        user.setCompany(company);
        return user;
    }
}
