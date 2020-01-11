package month;
import java.util.ArrayList;
import java.util.List;

public class HelloWorld {
    public static void main(String[] args) {
        List<User> list = new ArrayList<>();
        for(int i=1;i<=10;i++) {
            list.add(new User(i, "user_" + i));
        }
        //list forEach

    }
    private static class User{
        public User(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        Integer id;
        String name;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

}