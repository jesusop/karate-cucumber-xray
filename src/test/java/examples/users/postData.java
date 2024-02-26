package examples.users;

import com.intuit.karate.junit5.Karate;

class postData {
    
    @Karate.Test
    Karate testUsers() {
        return Karate.run("postData").relativeTo(getClass());
    }    

}
